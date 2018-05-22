package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.base.entity.WorkSchedule;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017-12-11.
 */
@Repository
public class ScheduleDao extends BaseDao<WorkSchedule> {


    public List<WorkSchedule> findSchedule(String schoolCode, String timeType){
        String hql="FROM WorkSchedule WHERE school_code=? AND timeType=? ORDER BY id ";
        return find(hql,new Object[]{schoolCode,timeType});
    }


    public List<WorkSchedule> findSchedule(String schoolCode, String timeType,Long gradeId){
        String hql="FROM WorkSchedule WHERE school_code=? AND timeType=? AND gradeId=? ORDER BY 'end' ";
        return find(hql,new Object[]{schoolCode,timeType,gradeId});
    }
}
