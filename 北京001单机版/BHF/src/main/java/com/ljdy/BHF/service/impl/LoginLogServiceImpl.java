package com.ljdy.BHF.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import com.ljdy.BHF.dao.BaseDao;
import com.ljdy.BHF.model.LoginLog;
import com.ljdy.BHF.model.User;
import com.ljdy.BHF.service.LoginLogService;
import com.ljdy.BHF.utils.IpAddrUtil;
/**
 * @ClassName LoginLogServiceImpl 
 * @Description (登陆日志Service实现类 ) 
 * @Author 顾冲 guchong@luojiadeyi.com    
 * @Date 2017年9月25日 上午9:09:01 
 * @修改历史  
 *     1. [2017年9月25日]创建文件 by 顾冲
 */
@Service("loginLogService")
public class LoginLogServiceImpl implements LoginLogService{
    @Resource
    private BaseDao<Object> baseDao;

	@Override
    public void add(User user, HttpServletRequest request) {
        LoginLog loginLog = new LoginLog();
        loginLog.setUser(user);
        loginLog.setLoginIp(IpAddrUtil.getIpAddr(request));
        loginLog.setLoginTime(new Date());
        baseDao.save(loginLog);
    }

    @Override
    public void deleteByUserId(String[] userIdArr) {
        StringBuffer sql = new StringBuffer("delete from LoginLog l where l.user.id in(");
        List<Object> param = new ArrayList<Object>();
        for (int i=0;i<userIdArr.length;i++){
            if(i<userIdArr.length-1){
                sql.append(" ?,");
            }else{
                sql.append(" ?");
            }
            param.add(userIdArr[i]);
        }
        sql.append(" )");
        baseDao.executeHql(sql.toString(),param);
    }
}
