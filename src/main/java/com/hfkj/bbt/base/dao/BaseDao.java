package com.hfkj.bbt.base.dao;


import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.base.page.PageInfo;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.*;


/**
 * Created by Administrator on 2017/6/14.
 */
public class BaseDao<Entity> extends HibernateDaoSupport {

    @Autowired
    private void setSF(SessionFactory sf) {
        super.setSessionFactory(sf);
    }


    /**
     * 删除现有实例，同步持久层。
     *
     * @param entity 要删除的实例
     */
    public void delete(Entity entity) {
        getHibernateTemplate().delete(entity);
    }

    /**
     * 删除集合内全部持久化类实例，同步持久层。
     *
     * @param entities 要删除的实例集合
     */
    public void delete(Collection<Entity> entities) {
        getHibernateTemplate().deleteAll(entities);
    }

    /**
     * 根据主键删除现有持久化类的实例，同步持久层。
     *
     * @param klass 类
     * @param id    主键
     */
    public void delete(Class<Entity> klass, Serializable id) {
        getHibernateTemplate().delete(load(klass, id));
    }

    /**
     * 根据HQL，执行update操作。
     *
     * @param hql HQL语句
     * @return int 执行结果
     */
    protected int executeUpdate(String hql) {
        return getHibernateTemplate().bulkUpdate(hql);
    }

    /**
     * 根据HQL和参数（参数根据次序设置），执行update操作。
     *
     * @param hql    HQL语句
     * @param params 查询参数
     * @return int 执行结果
     */
    protected int executeUpdate(String hql, Object[] params) {
        return getHibernateTemplate().bulkUpdate(hql, params);
    }

    /**
     * 根据HQL和参数（参数根据次序设置），执行executeSQL操作。
     *
     * @param sql    SQL语句
     * @param params 查询参数
     */
    protected void executeSQL(final String sql, final Object[] params) {
        getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                SQLQuery sqlQuery = session.createSQLQuery(sql);
                if (params != null) {
                    for (int i = 0; i < params.length; i++) {
                        sqlQuery.setParameter(i, params[i]);
                    }
                }
                return sqlQuery.executeUpdate();
            }
        });
    }

    /**
     * 根据HQL，执行查询操作。
     *
     * @param hql HQL语句
     * @return List 查询结果
     */
    @SuppressWarnings("unchecked")
    protected List<Entity> find(String hql) {
        return (List<Entity>) getHibernateTemplate().find(hql);
    }

    /**
     * 根据HQL和参数，执行查询操作。
     *
     * @param hql    HQL语句
     * @param params 查询参数
     * @return List 查询结果
     */
    @SuppressWarnings("unchecked")
    protected List<Entity> find(String hql, Object[] params) {
        return (List<Entity>) getHibernateTemplate().find(hql, params);
    }


    /**
     * 根据HQL和参数
     *
     * @param hql           HQL语句
     * @param parameterName 指代名
     * @param param         参数值
     * @return List 查询结果
     */
    @SuppressWarnings("unchecked")
    protected List<Entity> find(String hql, String parameterName, Object param) {
        return (List<Entity>) getHibernateTemplate().findByNamedParam(hql, parameterName, param);
    }

    /**
     * 根据HQL和参数
     *
     * @param hql            HQL语句
     * @param parameterNames 指代名
     * @param params         参数值
     * @return List 查询结果
     */
    @SuppressWarnings("unchecked")
    protected List<Entity> find(String hql, String[] parameterNames, Object[] params) {
        return (List<Entity>) getHibernateTemplate().findByNamedParam(hql, parameterNames, params);
    }

    /**
     * 根据主键加载特定持久化类的实例。
     *
     * @param klass 类
     * @param id    主键
     * @return Object 取得的实例
     */
    public Entity load(Class<Entity> klass, Serializable id) {
        return getHibernateTemplate().get(klass, id);
    }

    /**
     * 查询出所有特定持久化类的实例。
     *
     * @param klass 类
     * @return List 取得的实例结果集
     */
    public List<Entity> loadAll(Class<Entity> klass) {
        return getHibernateTemplate().loadAll(klass);
    }

    /**
     * 保存特定实例。
     *
     * @param entity 持久化类
     * @return Serializable 取得的实例的主键
     */
    public Serializable save(Entity entity) {
        return getHibernateTemplate().save(entity);
    }

    /**
     * 保存多个特定实例（循环依次保存）。
     *
     * @param entities 持久化类集
     * @return List 保存后包含主键的结果集
     */
    public List<Serializable> save(Collection<Entity> entities) {
        List<Serializable> result = new ArrayList<Serializable>();
        for (Entity entity : entities) {
            result.add(save(entity));
        }
        return result;
    }

    /**
     * 更新特定实例。
     *
     * @param entity 持久化类
     */
    public void update(Entity entity) {
        this.getHibernateTemplate().merge(entity);
    }

    /**
     * 更新多个特定实例（循环依次更新）。
     *
     * @param entities 持久化类集
     */
    @SuppressWarnings("rawtypes")
    public void update(Collection<Entity> entities) {
        for (Entity entity : entities) {
            update(entity);
        }
    }

    /**
     * 根据HQL，执行查询操作。
     *
     * @param hql HQL语句
     * @return List 查询结果
     */
    protected Entity findOne(String hql) {
        return getListOne(find(hql));
    }

    /**
     * 根据HQL和参数
     *
     * @param hql           HQL语句
     * @param parameterName 指代名
     * @param param         参数值
     * @return List 查询结果
     */
    protected Entity findOne(String hql, String parameterName, Object param) {
        return getListOne(find(hql, parameterName, param));
    }

    /**
     * 根据HQL和参数
     *
     * @param hql            HQL语句
     * @param parameterNames 指代名
     * @param params         参数值
     * @return List 查询结果
     */
    protected Entity findOne(String hql, String[] parameterNames, Object[] params) {
        return getListOne(find(hql, parameterNames, params));
    }

    /**
     * 根据HQL和参数，注意执行带有in关键字的查询操作。 这种方式只能带有一个参数，即 hql 中只能有一个参数
     *
     * @param hql    HQL语句
     * @param params 参数值
     * @return List 查询结果
     */
    protected Entity findOne(String hql, Object[] params) {
        return getListOne(find(hql, params));
    }

    /**
     * 根据HQL，执行查询操作。
     *
     * @param hql HQL语句
     * @return List 查询结果
     */
    @SuppressWarnings("unchecked")
    protected <Vo> List<Vo> findVo(String hql) {
        return (List<Vo>) getHibernateTemplate().find(hql);
    }

    /**
     * 根据HQL和参数，执行查询操作。
     *
     * @param hql    HQL语句
     * @param params 查询参数
     * @return List 查询结果
     */
    @SuppressWarnings("unchecked")
    protected <Vo> List<Vo> findVo(String hql, Object[] params) {
        return (List<Vo>) getHibernateTemplate().find(hql, params);
    }


    /**
     * 根据HQL和参数
     *
     * @param hql           HQL语句
     * @param parameterName 指代名
     * @param param         参数值
     * @return List 查询结果
     */
    @SuppressWarnings("unchecked")
    protected <Vo> List<Vo> findVo(String hql, String parameterName, Object param) {
        return (List<Vo>) getHibernateTemplate().findByNamedParam(hql, parameterName, param);
    }

    /**
     * 根据HQL和参数
     *
     * @param hql            HQL语句
     * @param parameterNames 指代名
     * @param params         参数值
     * @return List 查询结果
     */
    @SuppressWarnings("unchecked")
    protected <Vo> List<Vo> findVo(String hql, String[] parameterNames, Object[] params) {
        return (List<Vo>) getHibernateTemplate().findByNamedParam(hql, parameterNames, params);
    }

    /**
     * 根据HQL，执行查询操作。
     *
     * @param hql HQL语句
     * @return List 查询结果
     */
    @SuppressWarnings("unchecked")
    protected <Vo> Vo findOneVo(String hql) {
        return (Vo) getListOne(findVo(hql));
    }

    /**
     * 根据HQL和参数
     *
     * @param hql           HQL语句
     * @param parameterName 指代名
     * @param param         参数值
     * @return List 查询结果
     */
    @SuppressWarnings("unchecked")
    protected <Vo> Vo findOneVo(String hql, String parameterName, Object param) {
        return (Vo) getListOne(findVo(hql, parameterName, param));
    }

    /**
     * 根据HQL和参数
     *
     * @param hql            HQL语句
     * @param parameterNames 指代名
     * @param params         参数值
     * @return List 查询结果
     */
    @SuppressWarnings("unchecked")
    protected <Vo> Vo findOneVo(String hql, String[] parameterNames, Object[] params) {
        return (Vo) getListOne(findVo(hql, parameterNames, params));
    }

    /**
     * 根据HQL和参数，注意执行带有in关键字的查询操作。 这种方式只能带有一个参数，即 hql 中只能有一个参数
     *
     * @param hql    HQL语句
     * @param params 参数值
     * @return List 查询结果
     */
    @SuppressWarnings("unchecked")
    protected <Vo> Vo findOneVo(String hql, Object[] params) {
        return (Vo) getListOne(findVo(hql, params));
    }

    /**
     * 分页查询HQL结果集。
     *
     * @param hql 完整的HQL语句
     * @param pageInfo    页面对象（CurrentPageNum 当前页，从零开始；RowsOfPage 每页条数）
     * @return Page 包括结果集 list，与pageInfo对象
     */
    protected <EV> Page<EV> find(String hql, PageInfo pageInfo) {
        return find(hql, new Object[]{}, pageInfo);
    }


    /**
     * 带参数分页查询HQL结果集。
     *
     * @param hql 完整的HQL语句
     * @param params      参数集
     * @param pageInfo    页面对象（CurrentPageNum 当前页，从零开始；RowsOfPage 每页条数）
     * @return Page (包括结果集 list，与pageInfo对象）
     */
    protected <EV> Page<EV> find(String hql, Object[] params, PageInfo pageInfo) {
        Page<EV> page = new Page<EV>();
        List<EV> data = find(hql, params, pageInfo.getStartIndex(), pageInfo.getRowsOfPage());
        page.setData(data);
        page.setPageInfo(pageInfo);
        initPageInfo(hql, params, pageInfo);
        return page;
    }


    protected  Page  findPage(String sql,Map<String,Object> params,PageInfo pageInfo){
        Page page = new Page();
        List data=findObjects(sql,params,pageInfo.getStartIndex(),pageInfo.getRowsOfPage());
        page.setData(data);
        page.setPageInfo(pageInfo);
        initPageInfo(sql,params,pageInfo);
        return page;
    }


    protected List findBySql(String sql,Map<String,Object> params){
        return findList(sql,params);
    }




    //===========================================================================================

    /**
     * 获取一条数据
     *
     * @param list
     * @param <K>
     * @return
     */
    private <K> K getListOne(List<K> list) {
        if (list == null || list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    /**
     * 功能描述: 添加说明
     * @param hql hql
     * @param params params
     * @param pageInfo pageInfo
     * Date: 2014-6-25上午09:19:05
     */
    private void initPageInfo(String hql, Object[] params, PageInfo pageInfo) {
        StringBuilder querySB = new StringBuilder();
        String hqlAllUpper = hql.toUpperCase();

        int index_from = hqlAllUpper.indexOf("FROM");
        if (index_from == -1) {
            throw new RuntimeException("HQL语句没有包含\"FROM\"!");
        }
        int index_order = hqlAllUpper.indexOf("ORDER");

        querySB.append("SELECT COUNT(*) ");
        if(index_order == -1){
            querySB.append(hql.substring(index_from));
        }else{
            querySB.append(hql.substring(index_from, index_order));
        }

        // 计算页面参数
        List rowcount = getHibernateTemplate().find(querySB.toString(), params);
        if (rowcount != null && rowcount.size() > 0) {
            int count = ((Number) rowcount.get(0)).intValue();
            pageInfo.setTotalRows(count);
            if (count % pageInfo.getRowsOfPage() > 0) {
                pageInfo.setTotalPages(count / pageInfo.getRowsOfPage() + 1);
            } else {
                pageInfo.setTotalPages(count / pageInfo.getRowsOfPage());
            }
        }
    }

    /**
     * 功能描述: 添加说明
     * @param params params
     * @param pageInfo pageInfo
     * Date: 2014-6-25上午09:19:05
     */
    private void initPageInfo(String sql, Map<String,Object> params, PageInfo pageInfo) {
        SQLQuery sqlQuery = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
        if (params != null) {
            Set<Map.Entry<String, Object>> entries = params.entrySet();
            for (Map.Entry entry:entries){
                sqlQuery.setParameter(String.valueOf(entry.getKey()),entry.getValue());
            }
        }
        // 计算页面参数
        List rowcount = sqlQuery.list();
        if (rowcount != null && rowcount.size() > 0) {
            int count = rowcount.size();
            pageInfo.setTotalRows(count);
            if (count % pageInfo.getRowsOfPage() > 0) {
                pageInfo.setTotalPages(count / pageInfo.getRowsOfPage() + 1);
            } else {
                pageInfo.setTotalPages(count / pageInfo.getRowsOfPage());
            }
        }
    }

    /**
     * 根据HQL和开始数和条数，执行查询操作，针对mysql，hibernate均能支持数据库分页。
     *
     * @param hql HQL语句
     * @param params 参数集
     * @param start 开始点
     * @param size 条数
     * @return List 查询结果
     */
    @SuppressWarnings("unchecked")
    private <EV> List<EV> find(final String hql, final Object[] params, final int start, final int size) {
        return getHibernateTemplate().execute(new HibernateCallback<List<EV>>() {
            public List<EV> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                if (params != null) {
                    for (int i = 0; i < params.length; i++) {
                        query.setParameter(i, params[i]);
                    }
                }
                query.setFirstResult(start);
                query.setMaxResults(size);
                return query.list();
            }
        });
    }



    private List findObjects(final String sql, final Map<String,Object> params, final int startIndex, final int pageSize){
        return getHibernateTemplate().execute(new HibernateCallback<List>() {
            public List doInHibernate(Session session) throws HibernateException {
                SQLQuery query = session.createSQLQuery(sql);
                if (params != null) {
                    Set<Map.Entry<String, Object>> entries = params.entrySet();
                    for (Map.Entry entry:entries){
                        query.setParameter(String.valueOf(entry.getKey()),entry.getValue());
                    }
                }
                query.setFirstResult(startIndex);
                query.setMaxResults(pageSize);
                return query.list();
            }
        });
    }
    private List findList(final String sql,final Map<String, Object> params) {
        return getHibernateTemplate().execute(new HibernateCallback<List>() {
            public List doInHibernate(Session session) throws HibernateException {
                SQLQuery query = session.createSQLQuery(sql);
                if (params != null) {
                    Set<Map.Entry<String, Object>> entries = params.entrySet();
                    for (Map.Entry entry:entries){
                        query.setParameter(String.valueOf(entry.getKey()),entry.getValue());
                    }
                }
                return query.list();
            }
        });
    }

}
