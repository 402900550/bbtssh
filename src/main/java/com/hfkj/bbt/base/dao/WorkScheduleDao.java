package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.base.entity.WorkSchedule;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.base.page.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/16 0016.
 */
@Repository
public class WorkScheduleDao extends BaseDao<WorkSchedule> {

    /**
     * 根据年级id查询作息时间表
     * @param gradeId
     * @return
     */
    public List getWorkSchedule(Long gradeId, Long classId,String schoolCode){
        Map<String,Object> params=new HashMap<String,Object>();
        String sql=" SELECT tg.`name` tgName,ts.class_name tsName,tws.number_course,tws.`start`,tws.`end` " +
                " FROM tab_work_schedule tws  " +
                " LEFT JOIN tab_classes ts on ts.grade_id = tws.grade_id  " +
                " LEFT JOIN tab_grade tg on tg.id = ts.grade_id  " +
                " where tws.grade_id="+gradeId+" and ts.id = "+classId+" and tws.school_code ="+schoolCode;
        return findBySql(sql,params);
    }

    /**
     * 根据年级id查询设备使用情况
     * @param gradeId
     * @return
     */
    public List getWorkScheduleClassUser(Long gradeId, Long classId,String schoolCode){
        Map<String,Object> params=new HashMap<String,Object>();
        String sql="SELECT tws.number_course,tu.real_name,substring(tur.zt5_start,12,8),substring(tur.zt5_end,12,8) " +
                " FROM tab_work_schedule tws  " +
                " LEFT JOIN tab_classes ts on ts.grade_id = tws.grade_id  " +
                " LEFT JOIN tab_grade tg on tg.id = ts.grade_id  " +
                " LEFT JOIN tab_classroom tcr on tcr.id = ts.room_id  " +
                " LEFT JOIN tab_used_record tur on tur.equipment_no = tcr.equipment_no  " +
                " LEFT JOIN tab_user tu on tur.icard_no = tu.icard_no  " +
                " where  tws.grade_id= "+gradeId+" and ts.id = "+classId+"  AND DATE_FORMAT(tur.collect_time,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d') AND    " +
                " NOT (tws.`start` > substring(tur.zt5_end,12,8) OR tws.`end` < substring(tur.zt5_start,12,8)) " +
                " and tws.school_code = "+schoolCode+
                " GROUP BY tws.number_course  " +
                " ORDER BY tws.`end` ";
        return findBySql(sql,params);
    }


}
