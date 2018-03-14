package com.ljdy.BHF.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ljdy.BHF.utils.Json;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ljdy.BHF.model.User;
import com.ljdy.BHF.service.LoginLogService;
import com.ljdy.BHF.service.UserService;
import com.ljdy.BHF.utils.Md5Util;

import java.io.IOException;
import java.util.*;

/**
 * 
 * @ClassName UserController 
 * @Description (用户操作controller) 
 * @Author 顾冲 guchong@luojiadeyi.com    
 * @Date 2017年9月22日 上午11:44:08 
 * @修改历史  
 *     1. [2017年9月22日]创建文件 by 顾冲
 */
@Controller()
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private LoginLogService loginLogService;
    
    /**
     * @Title login 
     * @Description (用户登陆) 
     * @param req
     * @param loginName
     * @param pwd
     * @return
     * @Return String 返回类型 
     * @Throws 
     * @Date  2017年9月22日
     * @修改历史  
     *     1. [2017年9月22日]创建文件 by 顾冲
     */
    @RequestMapping("/login")
    public @ResponseBody String login(HttpServletRequest req,String loginName, String pwd){
        if(pwd != null && pwd.trim() !=""){
            pwd = Md5Util.Md5(pwd);
        }
        User loginUser = userService.login(loginName, pwd);
        if(loginUser!= null){
            req.getSession().setAttribute("loginUser", loginUser);
            //写入用户登陆日志
            loginLogService.add(loginUser, req);
            
            if("1".equals(loginUser.getRoleName()) || "2".equals(loginUser.getRoleName())){
                //国家级用户或者省级用户登陆 ，进入引导界面
                return "welcome";
            }else{
                //管理员登陆 ，进入用户管理界面
                return "userManager";
            }
        }
        return "loginFailed";
    }
    /**
     * @Title exitLogin 
     * @Description (退出登陆) 
     * @return
     * @Return String 返回类型 
     * @Throws 
     * @Date  2017年9月22日
     * @修改历史  
     *     1. [2017年9月22日]创建文件 by 顾冲
     */
    @RequestMapping("/exitLogin")
    public String exitLogin(HttpServletRequest req){
        req.getSession().removeAttribute("loginUser");
        return "login";
    }
    /**
     * @Title toWelcome 
     * @Description (进入引导页面) 
     * @return
     * @Return String 返回类型 
     * @Throws 
     * @Date  2017年10月9日
     * @修改历史  
     *     1. [2017年10月9日]创建文件 by 顾冲
     */
    @RequestMapping("/toWelcome")
    public String toWelcome(ModelMap model){
        model.put("welJspTag", "1");//引导页面标识
        return "welcome";
    }

    /**
     * 加载用户数据
     * @param request
     * @return
     */
    @RequestMapping("/loadUser")
    public void loadUser(HttpServletRequest request, HttpServletResponse response) throws  Exception{
        User user = (User) request.getSession().getAttribute("loginUser");
        String province = "国家".equals(user.getProvince())?null:user.getProvince();

        Map<String,Object> result = new HashMap<String,Object>();

        //分页
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int startRow = pageIndex*pageSize;
        int endRow = startRow+pageSize;

        List<Object> userList = userService.findUserByProvince(province);

        List<Object> data = new ArrayList<Object>();
        for(int i = startRow;i<userList.size()&&i<endRow;i++){
            data.add(userList.get(i));
        }
        result.put("data",data);
        result.put("total",userList.size());

        response.getWriter().write(Json.transToJson(result));
    }

    /**
     * 添加用户
     * @param request
     */
    @RequestMapping("/add")
    public @ResponseBody String add(HttpServletRequest request) throws IOException {
        String json = request.getParameter("data");
        if(json!=null && !json.equals("")){
            json = json.replace("[","").replace("]","");
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(json, User.class);
            String pwd = user.getPwd();
            String id = user.getId();
            if(id==null || id.equals("")){
                user.setPwd(Md5Util.Md5(pwd));
                user.setCreateTime(new Date());
                user.setStatus(1);
                userService.addUser(user);
            }else{
                String oldPwd = userService.getOldPwdById(id);
                if(!pwd.equals(oldPwd)){
                    user.setPwd(Md5Util.Md5(pwd));
                }
                userService.updateUser(user);
            }
        }
        return "success";
    }

    /**
     * 获取用户详细信息
     * @param request
     */
    @RequestMapping("/userDetail")
    public void userDetail(HttpServletRequest request,HttpServletResponse response) throws Exception{
        String id = request.getParameter("id");
        Object obj = userService.getUserByID(id);
        response.getWriter().write(Json.transToJson(obj));
    }

    /**
     * 删除用户信息
     * @param request
     */
    @RequestMapping("/delete")
    public @ResponseBody String delete(HttpServletRequest request){
        String id = request.getParameter("id");
        if(id!=null && !id.equals("")){
            String[] idArr = id.split(",");
            loginLogService.deleteByUserId(idArr);
            userService.deleteUser(idArr);
        }
        return "success";
    }

    /**
     * 验证账号是否已存在
     * @return
     */
    @RequestMapping("/isLoginNameExist")
    public @ResponseBody boolean isLoginNameExist(HttpServletRequest request){
        String loginName = request.getParameter("loginName");
        String id = request.getParameter("id");
        int count = userService.countUserByLoginName(loginName);
        if(id==null || id==""){
            return count!=0;
        }else{
            return count!=1;
        }
    }
}
