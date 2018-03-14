/**
 * @项目名称 RealestateRegister
 * @Title BaseDaoImpl.java 
 * @Package com.ljdy.realestateRegister.dao.impl 
 * @Description (基础数据库操作实现类) 
 * @Author 李金阳 lijy@luojiadeyi.com
 * @Date 2016年6月3日 下午5:43:55 
 * @Version V1.0 
 * @版权 珞珈德毅科技股份有限公司所有
 * @修改历史  
 *     1. [2016年6月3日]创建文件 by 李金阳
 */
package com.ljdy.BHF.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;

import com.ljdy.BHF.dao.BaseDao;

/** 
 * @ClassName BaseDaoImpl 
 * @Description (基础数据库操作实现类) 
 * @Author 李金阳 lijy@luojiadeyi.com
 * @Date 2016年6月3日 下午5:43:55 
 * @修改历史  
 *     1. [2016年6月3日]创建文件 by 李金阳
 */
@Repository("baseDao")
@Lazy(true)
@SuppressWarnings("all")
public class BaseDaoImpl<T> implements BaseDao<T> {
    
    /** 
     * @Description (Hibernate中sessionFactory的实例对象)  
     * @Date 2016年6月3日 下午5:57:11 
    */ 
    @Resource
    private SessionFactory sessionFactory;

     /**
     * @Title getCurrentSession 
     * @Description (获取当前线程中的session对象) 
     * @return
     * @Return Session 返回类型 
     * @Throws 
     * @Date  2016年6月3日
     * @修改历史  
     *     1. [2016年6月3日]创建文件 by 李金阳
     */
    private Session getCurrentSession() {
      return sessionFactory.getCurrentSession();
    	
    }

     /** 
      * @Title save 
      * @Description (保存一个对象) 
      * @param o
      * @return 
      * @see com.ljdy.realestateRegister.dao.BaseDao#save(java.lang.Object)
      * @Date  2016年6月3日
      * @修改历史  
      *     1. [2016年6月3日]创建文件 by 李金阳 
     **/
    public Serializable save(T o) {
        return this.getCurrentSession().save(o);
    }

     /** 
      * @Title delete 
      * @Description (删除一个对象) 
      * @param o 
      * @see com.ljdy.realestateRegister.dao.BaseDao#delete(java.lang.Object)
      * @Date  2016年6月3日
      * @修改历史  
      *     1. [2016年6月3日]创建文件 by 李金阳 
     **/
    public void delete(T o) {
        this.getCurrentSession().delete(o);
    }

     /** 
      * @Title update 
      * @Description (更新一个对象) 
      * @param o 
      * @see com.ljdy.realestateRegister.dao.BaseDao#update(java.lang.Object)
      * @Date  2016年6月3日
      * @修改历史  
      *     1. [2016年6月3日]创建文件 by 李金阳 
     **/
    public void update(T o) {
        this.getCurrentSession().update(o);
    }

     /** 
      * @Title saveOrUpdate 
      * @Description (保存或更新一个对象) 
      * @param o 
      * @see com.ljdy.realestateRegister.dao.BaseDao#saveOrUpdate(java.lang.Object)
      * @Date  2016年6月3日
      * @修改历史  
      *     1. [2016年6月3日]创建文件 by 李金阳 
     **/
    public void saveOrUpdate(T o) {
        this.getCurrentSession().saveOrUpdate(o);
    }

     /** 
      * @Title find 
      * @Description (查询集合) 
      * @param hql
      * @return 
      * @see com.ljdy.realestateRegister.dao.BaseDao#find(java.lang.String)
      * @Date  2016年6月3日
      * @修改历史  
      *     1. [2016年6月3日]创建文件 by 李金阳 
     **/
    public List<T> find(String hql) {
        return this.getCurrentSession().createQuery(hql).list();
    }

     /** 
      * @Title find 
      * @Description (查询集合：带数组类型参数) 
      * @param hql
      * @param param
      * @return 
      * @see com.ljdy.realestateRegister.dao.BaseDao#find(java.lang.String, java.lang.Object[])
      * @Date  2016年6月3日
      * @修改历史  
      *     1. [2016年6月3日]创建文件 by 李金阳 
     **/
    public List<T> find(String hql, Object[] param) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return q.list();
    }

     /** 
      * @Title find 
      * @Description (查询集合：带集合类型参数) 
      * @param hql
      * @param param
      * @return 
      * @see com.ljdy.realestateRegister.dao.BaseDao#find(java.lang.String, java.util.List)
      * @Date  2016年6月3日
      * @修改历史  
      *     1. [2016年6月3日]创建文件 by 李金阳 
     **/
    public List<T> find(String hql, List<Object> param) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.size() > 0) {
            for (int i = 0; i < param.size(); i++) {
                q.setParameter(i, param.get(i));
            }
        }
        return q.list();
    }

     /** 
      * @Title find 
      * @Description (查询集合：数组类型参数，带分页) 
      * @param hql
      * @param param
      * @param page
      * @param rows
      * @return 
      * @see com.ljdy.realestateRegister.dao.BaseDao#find(java.lang.String, java.lang.Object[], java.lang.Integer, java.lang.Integer)
      * @Date  2016年6月3日
      * @修改历史  
      *     1. [2016年6月3日]创建文件 by 李金阳 
     **/
    public List<T> find(String hql, Object[] param, Integer page, Integer rows) {
        if (page == null || page < 1) {
            page = 1;
        }
        if (rows == null || rows < 1) {
            rows = 10;
        }
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

     /** 
      * @Title find 
      * @Description (查询集合：集合类型参数，带分页) 
      * @param hql
      * @param param
      * @param page
      * @param rows
      * @return 
      * @see com.ljdy.realestateRegister.dao.BaseDao#find(java.lang.String, java.util.List, java.lang.Integer, java.lang.Integer)
      * @Date  2016年6月3日
      * @修改历史  
      *     1. [2016年6月3日]创建文件 by 李金阳 
     **/
    public List<T> find(String hql, List<Object> param, Integer page,
            Integer rows) {
        if (page == null || page < 1) {
            page = 1;
        }
        if (rows == null || rows < 1) {
            rows = 10;
        }
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.size() > 0) {
            for (int i = 0; i < param.size(); i++) {
                q.setParameter(i, param.get(i));
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

     /** 
      * @Title get 
      * @Description (获得一个对象：根据主键值) 
      * @param c
      * @param id
      * @return 
      * @see com.ljdy.realestateRegister.dao.BaseDao#get(java.lang.Class, java.io.Serializable)
      * @Date  2016年6月3日
      * @修改历史  
      *     1. [2016年6月3日]创建文件 by 李金阳 
     **/
    public T get(Class<T> c, Serializable id) {
        return (T) this.getCurrentSession().get(c, id);
    }

     /** 
      * @Title get 
      * @Description (获得一个对象：带数组参数) 
      * @param hql
      * @param param
      * @return 
      * @see com.ljdy.realestateRegister.dao.BaseDao#get(java.lang.String, java.lang.Object[])
      * @Date  2016年6月3日
      * @修改历史  
      *     1. [2016年6月3日]创建文件 by 李金阳 
     **/
    public T get(String hql, Object[] param) {
        List<T> l = this.find(hql, param);
        if (l != null && l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }

     /** 
      * @Title get 
      * @Description (获得一个对象：带集合参数) 
      * @param hql
      * @param param
      * @return 
      * @see com.ljdy.realestateRegister.dao.BaseDao#get(java.lang.String, java.util.List)
      * @Date  2016年6月3日
      * @修改历史  
      *     1. [2016年6月3日]创建文件 by 李金阳 
     **/
    public T get(String hql, List<Object> param) {
        List<T> l = this.find(hql, param);
        if (l != null && l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }
    
     /** 
      * @Title count 
      * @Description (查询记录总数) 
      * @param hql
      * @return 
      * @see com.ljdy.realestateRegister.dao.BaseDao#count(java.lang.String)
      * @Date  2016年6月3日
      * @修改历史  
      *     1. [2016年6月3日]创建文件 by 李金阳 
     **/
    public Long count(String hql) {
        return (Long) this.getCurrentSession().createQuery(hql).uniqueResult();
    }

     /** 
      * @Title count 
      * @Description (查询记录总数：带数组参数) 
      * @param hql
      * @param param
      * @return 
      * @see com.ljdy.realestateRegister.dao.BaseDao#count(java.lang.String, java.lang.Object[])
      * @Date  2016年6月3日
      * @修改历史  
      *     1. [2016年6月3日]创建文件 by 李金阳 
     **/
    public Long count(String hql, Object[] param) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return (Long) q.uniqueResult();
    }

     /** 
      * @Title count 
      * @Description (查询记录总数：带集合参数) 
      * @param hql
      * @param param
      * @return 
      * @see com.ljdy.realestateRegister.dao.BaseDao#count(java.lang.String, java.util.List)
      * @Date  2016年6月3日
      * @修改历史  
      *     1. [2016年6月3日]创建文件 by 李金阳 
     **/
    public Long count(String hql, List<Object> param) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.size() > 0) {
            for (int i = 0; i < param.size(); i++) {
                q.setParameter(i, param.get(i));
            }
        }
        return (Long) q.uniqueResult();
    }

     /** 
      * @Title executeHql 
      * @Description (执行HQL语句) 
      * @param hql
      * @return 
      * @see com.ljdy.realestateRegister.dao.BaseDao#executeHql(java.lang.String)
      * @Date  2016年6月3日
      * @修改历史  
      *     1. [2016年6月3日]创建文件 by 李金阳 
     **/
    public Integer executeHql(String hql) {
        return this.getCurrentSession().createQuery(hql).executeUpdate();
    }

     /** 
      * @Title executeHql 
      * @Description (执行HQL语句：带数组参数) 
      * @param hql
      * @param param
      * @return 
      * @see com.ljdy.realestateRegister.dao.BaseDao#executeHql(java.lang.String, java.lang.Object[])
      * @Date  2016年6月3日
      * @修改历史  
      *     1. [2016年6月3日]创建文件 by 李金阳 
     **/
    public Integer executeHql(String hql, Object[] param) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return q.executeUpdate();
    }

     /** 
      * @Title executeHql 
      * @Description (执行HQL语句：带集合参数) 
      * @param hql
      * @param param
      * @return 
      * @see com.ljdy.realestateRegister.dao.BaseDao#executeHql(java.lang.String, java.util.List)
      * @Date  2016年6月3日
      * @修改历史  
      *     1. [2016年6月3日]创建文件 by 李金阳 
     **/
    public Integer executeHql(String hql, List<Object> param) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.size() > 0) {
            for (int i = 0; i < param.size(); i++) {
                q.setParameter(i, param.get(i));
            }
        }
        return q.executeUpdate();
    }

     /** 
      * @Title getUniqueResult 
      * @Description (查询唯一结果) 
      * @param hql
      * @return 
      * @see com.ljdy.realestateRegister.dao.BaseDao#getUniqueResult(java.lang.String)
      * @Date  2016年9月12日
      * @修改历史  
      *     1. [2016年9月12日]创建文件 by 李金阳 
     **/
    @Override
    public Object getUniqueResult(String hql) {
        return this.getCurrentSession().createQuery(hql).uniqueResult();
    }

     /** 
      * @Title getUniqueResult 
      * @Description (查询唯一结果：带数组参数) 
      * @param hql
      * @param param
      * @return 
      * @see com.ljdy.realestateRegister.dao.BaseDao#getUniqueResult(java.lang.String, java.lang.Object[])
      * @Date  2016年9月12日
      * @修改历史  
      *     1. [2016年9月12日]创建文件 by 李金阳 
     **/
    @Override
    public Object getUniqueResult(String hql, Object[] param) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return q.uniqueResult();
    }

     /** 
      * @Title getUniqueResult 
      * @Description (查询唯一结果:带集合参数) 
      * @param hql
      * @param param
      * @return 
      * @see com.ljdy.realestateRegister.dao.BaseDao#getUniqueResult(java.lang.String, java.util.List)
      * @Date  2016年9月12日
      * @修改历史  
      *     1. [2016年9月12日]创建文件 by 李金阳 
     **/
    @Override
    public Object getUniqueResult(String hql, List<Object> param) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.size() > 0) {
            for (int i = 0; i < param.size(); i++) {
                q.setParameter(i, param.get(i));
            }
        }
        return q.uniqueResult();
    }

     /** 
      * @Title flush 
      * @Description (将内存中的数据，立即同步到数据库)  
      * @see com.ljdy.realestateRegister.dao.BaseDao#flush()
      * @Date  2016年10月26日
      * @修改历史  
      *     1. [2016年10月26日]创建文件 by 李金阳 
     **/
    @Override
    public void flush() {
        this.getCurrentSession().flush();
    }
    
     /**
     * @Title batchUpdate 
     * @Description (批量更新) 
     * @Return void 返回类型 
     * @Throws 
     * @Date  2017年2月22日
     * @修改历史  
     *     1. [2017年2月22日]创建文件 by 徐成
     */
    @Override
    public void batchUpdate(List<T> list){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
			for(int i=0;i<list.size();i++){
			    session.update(list.get(i));
			    if((i+1)%50==0 || i==list.size()-1){
			        session.flush();
			        session.clear();
			        //tx = session.beginTransaction();
			    }
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			tx.commit();
			session.clear();
			session.close();
		}
    }
    
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
//    list jbxx zqdl jb
    @Override
    public void batchSave(List<T> list){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
			for(int i=0;i<list.size();i++){
			    session.save(list.get(i));
			    if((i+1)%50==0 || i==list.size()-1){
			        session.flush();
			        session.clear();
			        //tx = session.beginTransaction();
			    }
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			tx.commit();
			session.clear();
			session.close();
		}
    }
    
    
    @Override
    public Object executeSql(String sql) {
        return this.getCurrentSession().createSQLQuery(sql).executeUpdate();
    }
    
    /**
      * @Title updateBySql 
      * @Description (sql语句修改) 
      * @param sql
      * @param obj
      * @return 
      * @see com.ljdy.BHF.dao.BaseDao#updateBySql(java.lang.String, java.lang.Object[])
      * @Date  2017年11月20日
      * @修改历史  
      *     1. [2017年11月20日]创建文件 by 顾冲 
     *
     */
    @Override
    public int updateBySql(String sql, Object [] obj) {
        SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
        if(obj != null && obj.length > 0){
            for (int i = 0; i < obj.length; i++) {
                query.setParameter(i, obj[i]);
            }
        }
        return query.executeUpdate();
    }


    
    /**
      * @Title queryBySql 
      * @Description (sql语句查询) 
      * @param sql
      * @param param
      * @return 
      * @see com.ljdy.BHF.dao.BaseDao#queryBySql(java.lang.String, java.lang.Object[])
      * @Date  2017年12月4日
      * @修改历史  
      *     1. [2017年12月4日]创建文件 by 顾冲 
     *
     */
    @Override
    public List<Object> queryBySql(String sql, List<Object> param) {
        SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
        if(param != null && param.size() > 0){
            for (int i = 0; i < param.size(); i++) {
                query.setParameter(i, param.get(i));
            }
        }
        return query.list();
    }

    @Override
    public int updateBySql(String sql, List<Object> param) {
        SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
        if(param != null && param.size() > 0){
            for (int i = 0; i < param.size(); i++) {
                query.setParameter(i, param.get(i));
            }
        }
        return query.executeUpdate();
    }
}
