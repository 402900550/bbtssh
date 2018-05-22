package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.base.entity.Operations;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.base.page.PageInfo;
import com.hfkj.bbt.base.util.UserUtil;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/2 0002.
 */
@Repository
public class OperationsDao extends BaseDao<Operations> {


    /**
     * 根据用户id查询设备报修情况
     *
     * @return
     */
    public Page selectAllExceptionList(Integer toPage, Integer pageSize,String schoolCode) {
        Map<String,Object> params=new HashMap<String,Object>();
        String sql=" SELECT " +
                "  top.id opid," +
                "  top.exception_type," +
                "  top.process_id," +
                "  DATE_FORMAT(top.start_date,'%Y-%m-%d %H:%i:%s')," +
                "  CONCAT(tg.`name`,cls.class_name)," +
                "  DATE_FORMAT(top.end_date,'%Y-%m-%d %H:%i:%s')," +
                "  top.exception_description," +
                "  (UNIX_TIMESTAMP(top.end_date)-UNIX_TIMESTAMP(top.start_date))/3600  " +
                " FROM" +
                " tab_operations top" +
                " LEFT JOIN tab_classes cls ON cls.id = top.class_id" +
                " LEFT JOIN tab_grade tg ON tg.id = cls.grade_id" +
                " WHERE top.school_code=:schoolCode order by top.start_date desc";
        params.put("schoolCode", schoolCode);
        PageInfo pageInfo =new PageInfo();
        pageInfo.setRowsOfPage(pageSize);
        pageInfo.setCurrentPage(toPage);
        return findPage(sql,params,pageInfo);
    }

    /**
     *  完成的运维单
     * @param toPage
     * @param pageSize
     * @return
     */
    public Page selectCompleteExcpetionList(Integer toPage, Integer pageSize,String schoolCode) {
        Map<String,Object> params=new HashMap<String,Object>();
        String sql=" SELECT " +
                "  top.id opid," +
                "  top.exception_type," +
                "  top.process_id," +
                "  DATE_FORMAT(top.start_date,'%Y-%m-%d %H:%i:%s')," +
                "  CONCAT(tg.`name`,cls.class_name)," +
                "  DATE_FORMAT(top.end_date,'%Y-%m-%d %H:%i:%s')," +
                "  top.exception_description," +
                "  (UNIX_TIMESTAMP(top.end_date)-UNIX_TIMESTAMP(top.start_date))/3600  " +
                " FROM" +
                " tab_operations top" +
                " LEFT JOIN tab_classes cls ON cls.id = top.class_id" +
                " LEFT JOIN tab_grade tg ON tg.id = cls.grade_id" +
                " WHERE top.school_code=:schoolCode AND top.end_date IS NOT NULL order by top.start_date desc";
        params.put("schoolCode", schoolCode);
        PageInfo pageInfo =new PageInfo();
        pageInfo.setRowsOfPage(pageSize);
        pageInfo.setCurrentPage(toPage);
        return findPage(sql,params,pageInfo);

    }

    public Operations getOpertionbyProcessId(String processId) {
        String hql="FROM Operations WHERE processId=?";
        return findOne(hql,new Object[]{processId});
    }

    /**
     * 运维首页显示（图）
     * @return
     */
    public List getOperations() {
        Map<String,Object> params=new HashMap<String,Object>();
        String sql="SELECT COUNT(tp.id),(SELECT COUNT(*) FROM tab_operations where end_date is NOT null ),ROUND((SELECT COUNT(*) FROM tab_operations where end_date is NOT null)/COUNT(tp.id),2) " +
                "FROM tab_operations tp ";
        return findBySql(sql,params);
    }

    /**
     * 查询未完成的异常
     * @return
     */
    public List<Operations> getOpertionsBySchool(String schoolCode){
        String hql="FROM Operations WHERE schoolCode=? AND endDate IS NULL order by startDate desc";
        return find(hql,new Object[]{schoolCode});
    }


    public List<Operations> getAccessory(Long accessoryId) {
        String hql="FROM Operations WHERE accessoryId=? AND endDate IS NULL AND birthType='scrap' ";
        return find(hql,new Object[]{accessoryId});
    }
}











