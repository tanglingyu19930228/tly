/**
 * @项目名称 RealestateRegister
 * @Title BaseDao.java 
 * @Package com.ljdy.realestateRegister.dao 
 * @Description (基础数据库操作接口) 
 * @Author 李金阳 lijy@luojiadeyi.com
 * @Date 2016年6月3日 下午5:30:26 
 * @Version V1.0 
 * @版权 珞珈德毅科技股份有限公司所有
 * @修改历史  
 *     1. [2016年6月3日]创建文件 by 李金阳
 */
package com.ljdy.BHF.dao;

import java.io.Serializable;
import java.util.List;

/** 
 * @ClassName BaseDao 
 * @Description (基础数据库操作接口) 
 * @Author 李金阳 lijy@luojiadeyi.com
 * @Date 2016年6月3日 下午5:30:26 
 * @修改历史  
 *     1. [2016年6月3日]创建文件 by 李金阳
 */
public interface BaseDao<T> {

     /**
     * @Title save 
     * @Description (保存一个对象) 
     * @param o
     * @return
     * @Return Serializable 返回类型 
     * @Throws 
     * @Date  2016年6月3日
     * @修改历史  
     *     1. [2016年6月3日]创建文件 by 李金阳
     */
    public Serializable save(T o);
    
     /**
     * @Title delete 
     * @Description (删除一个对象) 
     * @param o
     * @Return void 返回类型 
     * @Throws 
     * @Date  2016年6月3日
     * @修改历史  
     *     1. [2016年6月3日]创建文件 by 李金阳
     */
    public void delete(T o);	
    
     /**
     * @Title update 
     * @Description (更新一个对象) 
     * @param o
     * @Return void 返回类型 
     * @Throws 
     * @Date  2016年6月3日
     * @修改历史  
     *     1. [2016年6月3日]创建文件 by 李金阳
     */
    public void update(T o);
    
     /**
     * @Title saveOrUpdate 
     * @Description (保存或更新一个对象) 
     * @param o
     * @Return void 返回类型 
     * @Throws 
     * @Date  2016年6月3日
     * @修改历史  
     *     1. [2016年6月3日]创建文件 by 李金阳
     */
    public void saveOrUpdate(T o);
    
     /**
     * @Title find 
     * @Description (查询集合) 
     * @param hql
     * @return
     * @Return List<T> 返回类型 
     * @Throws 
     * @Date  2016年6月3日
     * @修改历史  
     *     1. [2016年6月3日]创建文件 by 李金阳
     */
    public List<T> find(String hql);
    
     /**
     * @Title find 
     * @Description (查询集合：带数组类型参数) 
     * @param hql
     * @param param
     * @return
     * @Return List<T> 返回类型 
     * @Throws 
     * @Date  2016年6月3日
     * @修改历史  
     *     1. [2016年6月3日]创建文件 by 李金阳
     */
    public List<T> find(String hql, Object[] param);
    
     /**
     * @Title find 
     * @Description (查询集合：带集合类型参数) 
     * @param hql
     * @param param
     * @return
     * @Return List<T> 返回类型 
     * @Throws 
     * @Date  2016年6月3日
     * @修改历史  
     *     1. [2016年6月3日]创建文件 by 李金阳
     */
    public List<T> find(String hql, List<Object> param);
    
     /**
     * @Title find 
     * @Description (查询集合：数组类型参数，带分页) 
     * @param hql
     * @param param
     * @param page
     * @param rows
     * @return
     * @Return List<T> 返回类型 
     * @Throws 
     * @Date  2016年6月3日
     * @修改历史  
     *     1. [2016年6月3日]创建文件 by 李金阳
     */
    public List<T> find(String hql, Object[] param, Integer page, Integer rows);
    
     /**
     * @Title find 
     * @Description (查询集合：集合类型参数，带分页) 
     * @param hql
     * @param param
     * @param page
     * @param rows
     * @return
     * @Return List<T> 返回类型 
     * @Throws 
     * @Date  2016年6月3日
     * @修改历史  
     *     1. [2016年6月3日]创建文件 by 李金阳
     */
    public List<T> find(String hql, List<Object> param, Integer page,
            Integer rows);
    
     /**
     * @Title get 
     * @Description (获得一个对象：根据主键值) 
     * @param c
     * @param id
     * @return
     * @Return T 返回类型 
     * @Throws 
     * @Date  2016年6月3日
     * @修改历史  
     *     1. [2016年6月3日]创建文件 by 李金阳
     */
    public T get(Class<T> c, Serializable id);
    
     /**
     * @Title get 
     * @Description (获得一个对象：带数组参数) 
     * @param hql
     * @param param
     * @return
     * @Return T 返回类型 
     * @Throws 
     * @Date  2016年6月3日
     * @修改历史  
     *     1. [2016年6月3日]创建文件 by 李金阳
     */
    public T get(String hql, Object[] param);
    
     /**
     * @Title get 
     * @Description (获得一个对象：带集合参数) 
     * @param hql
     * @param param
     * @return
     * @Return T 返回类型 
     * @Throws 
     * @Date  2016年6月3日
     * @修改历史  
     *     1. [2016年6月3日]创建文件 by 李金阳
     */
    public T get(String hql, List<Object> param);
    
     /**
     * @Title count 
     * @Description (查询记录总数) 
     * @param hql
     * @return
     * @Return Long 返回类型 
     * @Throws 
     * @Date  2016年6月3日
     * @修改历史  
     *     1. [2016年6月3日]创建文件 by 李金阳
     */
    public Long count(String hql);
    
     /**
     * @Title count 
     * @Description (查询记录总数：带数组参数) 
     * @param hql
     * @param param
     * @return
     * @Return Long 返回类型 
     * @Throws 
     * @Date  2016年6月3日
     * @修改历史  
     *     1. [2016年6月3日]创建文件 by 李金阳
     */
    public Long count(String hql, Object[] param);
    
     /**
     * @Title count 
     * @Description (查询记录总数：带集合参数) 
     * @param hql
     * @param param
     * @return
     * @Return Long 返回类型 
     * @Throws 
     * @Date  2016年6月3日
     * @修改历史  
     *     1. [2016年6月3日]创建文件 by 李金阳
     */
    public Long count(String hql, List<Object> param);
    
     /**
     * @Title executeHql 
     * @Description (执行HQL语句) 
     * @param hql
     * @return
     * @Return Integer 返回类型 
     * @Throws 
     * @Date  2016年6月3日
     * @修改历史  
     *     1. [2016年6月3日]创建文件 by 李金阳
     */
    public Integer executeHql(String hql);
    
     /**
     * @Title executeHql 
     * @Description (执行HQL语句：带数组参数) 
     * @param hql
     * @param param
     * @return
     * @Return Integer 返回类型 
     * @Throws 
     * @Date  2016年6月3日
     * @修改历史  
     *     1. [2016年6月3日]创建文件 by 李金阳
     */
    public Integer executeHql(String hql, Object[] param);
    
     /**
     * @Title executeHql 
     * @Description (执行HQL语句：带集合参数) 
     * @param hql
     * @param param
     * @return
     * @Return Integer 返回类型 
     * @Throws 
     * @Date  2016年6月3日
     * @修改历史  
     *     1. [2016年6月3日]创建文件 by 李金阳
     */
    public Integer executeHql(String hql, List<Object> param);
    
     /**
     * @Title getUniqueResult 
     * @Description (查询唯一结果) 
     * @param hql
     * @return
     * @Return Object 返回类型 
     * @Throws 
     * @Date  2016年9月12日
     * @修改历史  
     *     1. [2016年9月12日]创建文件 by 李金阳
     */
    public Object getUniqueResult(String hql);
    
     /**
     * @Title getUniqueResult 
     * @Description (查询唯一结果:带数组参数) 
     * @param hql
     * @param param
     * @return
     * @Return Object 返回类型 
     * @Throws 
     * @Date  2016年9月12日
     * @修改历史  
     *     1. [2016年9月12日]创建文件 by 李金阳
     */
    public Object getUniqueResult(String hql, Object[] param);
    
     /**
     * @Title getUniqueResult 
     * @Description (查询唯一结果:带集合参数) 
     * @param hql
     * @param param
     * @return
     * @Return Object 返回类型 
     * @Throws 
     * @Date  2016年9月12日
     * @修改历史  
     *     1. [2016年9月12日]创建文件 by 李金阳
     */
    public Object getUniqueResult(String hql, List<Object> param);
    
     /**
     * @Title flush 
     * @Description (将内存中的数据，立即同步到数据库) 
     * @Return void 返回类型 
     * @Throws 
     * @Date  2016年10月26日
     * @修改历史  
     *     1. [2016年10月26日]创建文件 by 李金阳
     */
    public void flush();
    
     /**
     * @Title batchUpdate 
     * @Description (批量更新) 
     * @param list
     * @Return void 返回类型 
     * @Throws 
     * @Date  2017年2月22日
     * @修改历史  
     *     1. [2017年2月22日]创建文件 by 徐成
     */
    public void batchUpdate(List<T> list);
    
     /**
     * @Title batchSave 
     * @Description (批量保存) 
     * @param list
     * @Return void 返回类型 
     * @Throws 
     * @Date  2017年2月22日
     * @修改历史  
     *     1. [2017年2月22日]创建文件 by 徐成
     */
    public void batchSave(List<T> list);
    
    public Object executeSql(String sql);
    
    /**
     * @Title updateBySql 
     * @Description (sql语句修改) 
     * @param sql
     * @param obj
     * @return
     * @Return int 返回类型 
     * @Throws 
     * @Date  2017年11月20日
     * @修改历史  
     *     1. [2017年11月20日]创建文件 by 顾冲
     */
    public int updateBySql(String sql, Object [] obj);
    
    public int updateBySql(String sql, List<Object> param);


    
    /**
     * @Title queryBySql 
     * @Description (sql语句查询) 
     * @param sql
     * @param param
     * @return
     * @Return List<Object> 返回类型 
     * @Throws 
     * @Date  2017年12月4日
     * @修改历史  
     *     1. [2017年12月4日]创建文件 by 顾冲
     */
    public List<Object> queryBySql(String sql, List<Object> param);
    
}
