package com.ljdy.BHF.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljdy.BHF.dao.BaseDao;
import com.ljdy.BHF.model.User;
import com.ljdy.BHF.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private BaseDao<Object> baseDao;

    @Override
    public User login(String loginName, String pwd) {
        String hql = "from User u where loginName=? and pwd=? and status=1";
        Object[] param = {loginName,pwd};
        return (User) baseDao.get(hql, param);
    }

    /**
     * 新增用户
     * @param user
     * 修改历史：
	 *		[2017年9月28日]创建文件	by 徐成
     */
	@Override
	public void addUser(User user) {
		baseDao.save(user);
	}

    /**
     * 根据省份获取用户列表
     * @param province
     * @return
     */
    @Override
    public List<Object> findUserByProvince(String province) {
        StringBuffer hql = new StringBuffer("from User u where u.status = 1");
        List<Object> param = new ArrayList<Object>();
        if(province!=null){
            hql.append(" and u.province = ?");
            param.add(province);
        }
        hql.append(" order by u.createTime desc");
        return baseDao.find(hql.toString(),param);
    }

    /**
     * 根据ID获取用户信息
     * @param id
     * @return
     */
    @Override
    public Object getUserByID(String id) {
        String hql = "from User u where u.id = ?";
        Object[] param = {id};
        return baseDao.get(hql,param);
    }

    /**
     * 更新用户信息
     * @param user
     */
    @Override
    public void updateUser(User user) {
        baseDao.update(user);
    }

    /**
     * 根据ID批量删除用户(非物理删除)
     * @param idArr
     */
    @Override
    public void deleteUser(String[] idArr) {
        StringBuffer hql = new StringBuffer("delete from User u where u.id in(");
        List<Object> param = new ArrayList<Object>();
        for (int i=0;i<idArr.length;i++){
            if(i<idArr.length-1){
                hql.append(" ?,");
            }else{
                hql.append(" ?");
            }
            param.add(idArr[i]);
        }
        hql.append(" )");
        baseDao.executeHql(hql.toString(),param);
    }

    @Override
    public String getOldPwdById(String id) {
        String hql = "select u.pwd from User u where u.id = ?";
        Object[] param = {id};
        return baseDao.get(hql,param).toString();
    }

    @Override
    public int countUserByLoginName(String loginName){
        String sql = "from User u where u.loginName =?";
        Object[] param = {loginName};
        /*Object obj = baseDao.get(sql,param);*/
        List<Object> list = baseDao.find(sql,param);
        if(list != null && list.size()>0){
            return list.size();
        }
        return 0;
    }
}
