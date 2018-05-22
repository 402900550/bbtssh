package com.hfkj.bbt.base.dao;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-10-20.
 */
@Repository
public class ObjectDao extends BaseDao {





    public String getCompanyUserAdmin(String schoolCode){
        String sql="SELECT GROUP_CONCAT(tu.id) FROM tab_user tu  " +
                "WHERE  tu.school_code=:schoolCode ";
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("schoolCode",schoolCode);
        List list = findBySql(sql, params);
        return String.valueOf(list.get(0));
    }


    /**
     * 测试用
     * @return
     */
    public List testTaskEq(Integer workStatus){
        String sql="SELECT " +
                " tcr.equipment_no, " +
                " tee.equipment_ip, " +
                " tee.heart_time, " +
                " tee.work_status " +
                " FROM " +
                " tab_classroom tcr " +
                " LEFT JOIN tab_equipment tee ON tcr.equipment_no=tee.equipment_no " +
                " " +
                " WHERE tcr.equipment_no is not null " +
                " AND (tee.work_status=:workStatus OR tee.work_status IS NULL)";
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("workStatus",workStatus);
        List list = findBySql(sql, params);
        return list;

    }




}












