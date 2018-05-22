package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.base.entity.UsedRecord;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-10-20.
 */
@Repository
public class UsedRecordDao extends BaseDao<UsedRecord> {



    public UsedRecord getRecentlyEqu(String equipmentNo){
        String hql="FROM UsedRecord e WHERE e.equipmentNo=?  AND e.collectTime=" +
                "(SELECT max(r.collectTime) FROM UsedRecord r WHERE r.equipmentNo=e.equipmentNo)";

        return findOne(hql,new Object[]{equipmentNo});
    }


    /**
     * 根据查询学校设备当天使用情况
     * @return
     */
    public List getSchoolClassWorkSchedule(){
        Map<String,Object> params=new HashMap<String,Object>();
        String sql="SELECT class.school_code,COUNT(*) " +
                "FROM tab_used_record tur    " +
                "LEFT JOIN tab_classroom room ON room.equipment_no=tur.equipment_no " +
                "LEFT JOIN tab_classes class ON class.room_id=room.id  " +
                "LEFT JOIN tab_work_schedule tws on tws.school_code = class.school_code AND tws.grade_id=class.grade_id  " +
                "where DATE_FORMAT(tur.collect_time,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d') AND    " +
                "NOT(tws.start > substring(tur.zt5_end,12,8) OR tws.end < substring(tur.zt5_start,12,8))  " +
                "GROUP BY tws.school_code ";
        return findBySql(sql,params);
    }

    /**
     * 根据查询学校所有课时使用情况
     * @return
     */
    public List getSchoolClassWorkScheduleAll(){
        Map<String,Object> params=new HashMap<String,Object>();
        String sql="SELECT SUM(c) ,shool_code FROM (SELECT " +
                " COUNT(*) * (SELECT COUNT(ws.`start`) FROM tab_work_schedule ws WHERE ws.school_code = ss.school_code AND ws.grade_id = ss.grade_id) c, " +
                " ss.grade_id, " +
                "  ss.school_code shool_code " +
                "FROM " +
                " tab_classes ss " +
                "GROUP BY " +
                " ss.grade_id,ss.school_code) kk GROUP BY kk.shool_code ";
        return findBySql(sql,params);
    }

    /**
     * 班级每天使用情况
     * @return
     */
    public List getClassWorkSchedule(){
        Map<String,Object> params=new HashMap<String,Object>();
        String sql=" SELECT ts.school_code,tg.id tgId,class.id cId,\n" +
                "COUNT(*),(SELECT COUNT(tw.`start`) FROM tab_work_schedule tw where tw.school_code = tws.school_code and tw.grade_id = tws.grade_id)\n" +
                "FROM tab_used_record tur   \n" +
                "LEFT JOIN tab_classroom room ON room.equipment_no=tur.equipment_no \n" +
                "LEFT JOIN tab_classes class ON class.room_id=room.id \n" +
                "LEFT JOIN tab_grade tg on tg.id = class.grade_id \n" +
                "LEFT JOIN tab_school ts on ts.school_code = class.school_code \n" +
                "LEFT JOIN tab_work_schedule tws on tws.school_code = class.school_code AND tws.grade_id=class.grade_id \n" +
                "where DATE_FORMAT(tur.collect_time,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d') AND   \n" +
                "NOT(tws.start > substring(tur.zt5_end,12,8) OR tws.end < substring(tur.zt5_start,12,8))  \n" +
                "GROUP BY class.id ";
        return findBySql(sql,params);
    }

    /**
     * 教师每天使用情况
     * @return
     */
    public List getTeacherWorkSchedule(){
        Map<String,Object> params=new HashMap<String,Object>();
        String sql="SELECT ts.school_code,tu.id,COUNT(*),(SELECT COUNT(tw.`start`) FROM tab_work_schedule tw where tw.school_code = tws.school_code and tw.grade_id = tws.grade_id) " +
                "FROM tab_used_record tur   " +
                "LEFT JOIN tab_classroom room ON room.equipment_no=tur.equipment_no " +
                "LEFT JOIN tab_classes class ON class.room_id=room.id  " +
                "LEFT JOIN tab_school ts on ts.school_code = class.school_code  " +
                "LEFT JOIN tab_work_schedule tws on tws.school_code = class.school_code AND tws.grade_id=class.grade_id  " +
                "LEFT JOIN tab_user tu on tu.icard_no = tur.icard_no  " +
                "where DATE_FORMAT(tur.collect_time,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d') AND    " +
                "NOT(tws.start > substring(tur.zt5_end,12,8) OR tws.end < substring(tur.zt5_start,12,8))  " +
                "GROUP BY  tu.id ";
        return findBySql(sql,params);
    }

    /**
     * 科目每天使用情况
     * @return
     */
    public List getSubjectWorkSchedule(){
        Map<String,Object> params=new HashMap<String,Object>();
        String sql=" SELECT tu.subject_id,COUNT(*),ts.school_code  " +
                "FROM tab_used_record tur       " +
                "LEFT JOIN tab_classroom room ON room.equipment_no=tur.equipment_no  " +
                "LEFT JOIN tab_classes class ON class.room_id=room.id      " +
                "LEFT JOIN tab_school ts on ts.school_code = class.school_code      " +
                "LEFT JOIN tab_work_schedule tws on tws.school_code = class.school_code AND tws.grade_id=class.grade_id      " +
                "LEFT JOIN tab_user tu on tu.icard_no = tur.icard_no      " +
                "where DATE_FORMAT(tur.collect_time,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d') AND        " +
                "NOT(tws.start > substring(tur.zt5_end,12,8) OR tws.end < substring(tur.zt5_start,12,8))      " +
                "GROUP BY tu.subject_id ";
        return findBySql(sql,params);
    }

}
