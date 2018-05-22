package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.application.web.vo.ApplicationVo;
import com.hfkj.bbt.base.dao.BaseDao;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.base.page.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/21 0021.
 */
@Repository
public class ApplicationDao extends BaseDao {

    /**
     * 应用监测
     *
     * @param applicationVo
     * @return
     */
    public Page selectDistinctSchoolList(ApplicationVo applicationVo) {
        Map<String, Object> params = new HashMap<String, Object>();
        String sql = "SELECT   " +
                "tcy.`name` tcyName,   " +
                "ts.`name` tsName,   " +
                "(SELECT count(tcs.id) FROM tab_classes tcs where tcs.school_code = ts.school_code) banjizongshu,   " +
                "(SELECT count(tcsr.id) FROM tab_classroom tcsr LEFT JOIN tab_teach_building ttb ON ttb.id=tcsr.building_id  where tcsr.equipment_no is not null AND ttb.school_code=ts.school_code ) jiancheshu,   " +
                "(SELECT COUNT(ee.equipment_no) FROM tab_equipment ee LEFT JOIN tab_classroom rr ON rr.equipment_no=ee.equipment_no LEFT JOIN tab_teach_building btt ON btt.id=rr.building_id WHERE btt.school_code=ts.school_code and ee.work_status=1) yunxing,   " +
                "ROUND((SELECT COUNT(ee.equipment_no) FROM tab_equipment ee LEFT JOIN tab_classroom rr ON rr.equipment_no=ee.equipment_no LEFT JOIN tab_teach_building btt ON btt.id=rr.building_id WHERE btt.school_code=ts.school_code and ee.work_status=1) " +
                "/(SELECT count(tcsr.id) FROM tab_classroom tcsr LEFT JOIN tab_teach_building ttb ON ttb.id=tcsr.building_id  where tcsr.equipment_no is not null AND ttb.school_code=ts.school_code ),2) shiyonlv,   " +
                "ts.school_code   " +
                "FROM   tab_school ts " +
                "LEFT JOIN tab_cityarea tcy on tcy.detail_code = ts.qxm " +
                "LEFT JOIN tab_school_type tst on tst.id=ts.type " +
                " where  tcy.`name` like :distinctName" +
                " AND ts.`name` like :schoolName  AND ts.type!=11";
        params.put("distinctName", applicationVo.getDistinctName());
        params.put("schoolName", applicationVo.getSchoolName());
        PageInfo pageInfo = new PageInfo();
        pageInfo.setRowsOfPage(applicationVo.getSizeNumber());
        pageInfo.setCurrentPage(applicationVo.getStartNumber());
        return findPage(sql, params, pageInfo);
    }

    /**
     * 应用检测单个学校查询
     *
     * @param applicationVo
     * @return
     */
    public Page selectEquipmentSingleSchoolList(ApplicationVo applicationVo) {
        Map<String, Object> params = new HashMap<String, Object>();
        String sql = "SELECT " +
                " tg.`name`, " +
                " tc.class_name, " +
                " tb.building_name, " +
                " tr.room_code, " +
                " tu.real_name, " +
                " tsb.subject_name, " +
                "  te.work_status, " +
                "  te.pc_status, " +
                "  te.display_status, " +
                "  te.msgsource_status, " +
                "  te.power, " +
                "  ts.school_code, " +
                "  tr.id roomid, " +
                "  tc.id tcid ," +
                "  te.lights_status, " +
                "  te.sockets_status, " +
                "  te.aircondition_status," +
                "  te.fan_status," +
                "  te.equipment_no," +
                "  te.equipment_type," +
                "  tr.room_type," +
                "  tg.id tgId," +
                "  te.equipment_ip   " +
                "  FROM " +
                " tab_classroom tr " +
                " LEFT JOIN tab_teach_building tb ON tb.id = tr.building_id " +
                " LEFT JOIN tab_school ts ON ts.school_code=tb.school_code " +
                " LEFT JOIN tab_classes tc ON tr.id = tc.room_id " +
                " LEFT JOIN tab_grade tg ON tg.id = tc.grade_id " +
                " LEFT JOIN tab_equipment te ON tr.equipment_no = te.equipment_no " +
                " LEFT JOIN tab_user tu ON tu.icard_no = te.icard_no  " +
                " LEFT JOIN tab_subject tsb ON tsb.id = tu.subject_id " +
                " WHERE ts.school_code=:schoolCode  ";
        if (!"%%".equals(applicationVo.getClassName())) {
            sql += " AND CONCAT(tg.`name`,tc.class_name) LIKE :className  ";
            params.put("className", applicationVo.getClassName());
        }

        if (!"%%".equals(applicationVo.getTeacherName())) {
            sql += " AND tu.real_name = :teacherName  ";
            params.put("teacherName", applicationVo.getTeacherName());
        }
        if (applicationVo.getWorkStatus() != null) {
            sql += " AND te.work_status = :workStatus  ";
            params.put("workStatus", applicationVo.getWorkStatus());
        }
        sql += " order by tc.class_name ";

        params.put("schoolCode", applicationVo.getSchoolCode());
        PageInfo pageInfo = new PageInfo();
        pageInfo.setRowsOfPage(applicationVo.getSizeNumber());
        pageInfo.setCurrentPage(applicationVo.getStartNumber());
        return findPage(sql, params, pageInfo);
    }

    /**
     * 首页应用检测页面
     *
     * @return
     */
    public List loadEquipmentSingle(int qxmId) {
        String sql = "SELECT  " +
                "  ts.`name` sname,  " +
                "  COUNT(room.equipment_no) jianceshu,  " +
                "  SUM(eq.work_status) yunxingshu  " +
                " FROM  " +
                "  tab_school ts  " +
                " LEFT JOIN tab_teach_building tb  ON tb.school_code = ts.school_code  " +
                " LEFT JOIN tab_classroom room ON room.building_id = tb.id  " +
                " LEFT JOIN tab_equipment eq ON eq.equipment_no=room.equipment_no  " +
                " WHERE ts.qxm=:qxmId  " +
                " GROUP BY ts.school_code";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("qxmId", qxmId);
        return findBySql(sql, params);
    }

    /**
     * 首页资产管理
     *
     * @return
     */
    public List LoadClassRoomEQuiMentSchool(int qxmId) {
        String sql = "SELECT   " +
                " (SELECT COUNT(teq.facilities) FROM tab_accessory teq LEFT JOIN tab_school ttts ON ttts.school_code=teq.school_code WHERE teq.facilities = 1 AND ttts.qxm = ts.qxm) wanhaoshu,   " +
                " (SELECT SUM(tcm.cost) FROM tab_classroom tcm WHERE tcm.building_id = ttb.id ),   " +
                " (SELECT sum(tas.price) FROM tab_accessory tas  LEFT JOIN tab_school ts on tas.school_code = ts.school_code WHERE tas.facilities != 3  AND ts.qxm =:qxmId ), " +
                " (SELECT COUNT(*) FROM tab_accessory accs LEFT JOIN tab_school tsc ON tsc.school_code=accs.school_code WHERE tsc.qxm=ts.qxm) shebeizongshu , " +
                " (SELECT COUNT(*) FROM tab_classes clas LEFT JOIN tab_school tsc ON tsc.school_code=clas.school_code WHERE tsc.qxm=ts.qxm) zongbanji, " +
                " (SELECT COUNT(*) FROM tab_classroom room LEFT JOIN tab_teach_building ttb ON ttb.id=room.building_id LEFT JOIN tab_school tsc ON tsc.school_code=ttb.school_code  " +
                " WHERE tsc.qxm=ts.qxm AND room.equipment_no IS NOT NULL ) jianceshu, " +
                " ROUND((SELECT COUNT(*) FROM tab_classroom room LEFT JOIN tab_teach_building ttb ON ttb.id=room.building_id LEFT JOIN tab_school tsc ON tsc.school_code=ttb.school_code  " +
                " WHERE tsc.qxm=ts.qxm AND room.equipment_no IS NOT NULL )/(SELECT COUNT(*) FROM tab_classes clas LEFT JOIN tab_school tsc ON tsc.school_code=clas.school_code WHERE tsc.qxm=ts.qxm) ,2) jiancelv, " +
                " ROUND((SELECT COUNT(teq.facilities) FROM tab_accessory teq LEFT JOIN tab_school ttts ON ttts.school_code=teq.school_code WHERE teq.facilities = 1 AND ttts.qxm = ts.qxm)/(SELECT COUNT(*) FROM tab_accessory accs LEFT JOIN tab_school tsc ON tsc.school_code=accs.school_code WHERE tsc.qxm=ts.qxm),2) wanhaolv " +
                " FROM tab_school ts   " +
                " LEFT JOIN tab_teach_building ttb ON ttb.school_code = ts.school_code   " +
                " LEFT JOIN tab_classroom tcr ON tcr.building_id = ttb.id   " +
                " LEFT JOIN tab_cityarea tcy ON ts.qxm = tcy.detail_code   " +
                " LEFT JOIN tab_accessory tac ON tac.room_id = tcr.id   " +
                " WHERE ts.qxm =:qxmId " +
                " GROUP BY ts.qxm ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("qxmId", qxmId);
        return findBySql(sql, params);
    }

    /**
     * 环境检测
     *
     * @param applicationVo
     * @return
     */
    public Page selectEnvironmentGradeClassList(ApplicationVo applicationVo) {
        Map<String, Object> params = new HashMap<String, Object>();
        String sql = "SELECT " +
                " CONCAT(tg.`name`,tc.class_name), " +
                " CONCAT(tb.building_name,'-',tr.room_code), " +
                " te.temperature, " +
                " te.humidity, " +
                " te.pm2_5, " +
                " te.illuminate, " +
                " te.noise " +
                "FROM " +
                " tab_classroom tr " +
                "LEFT JOIN tab_teach_building tb ON tb.id = tr.building_id " +
                "LEFT JOIN tab_school ts ON ts.school_code = tb.school_code " +
                "LEFT JOIN tab_classes tc ON tr.id = tc.room_id " +
                "LEFT JOIN tab_grade tg ON tg.id = tc.grade_id " +
                "LEFT JOIN tab_equipment te ON tr.equipment_no = te.equipment_no " +
                "LEFT JOIN tab_user tu ON tu.icard_no = te.icard_no " +
                "LEFT JOIN tab_subject tsb ON tsb.id = tu.subject_id " +
                "WHERE " +
                " ts.school_code = :schoolCode " +
                "AND CONCAT(tg.`name`, tc.class_name) LIKE :className" +
                " ORDER BY tr.room_code ";
        params.put("schoolCode", applicationVo.getSchoolCode());
        params.put("className", applicationVo.getClassName());
        PageInfo pageInfo = new PageInfo();
        pageInfo.setRowsOfPage(applicationVo.getSizeNumber());
        pageInfo.setCurrentPage(applicationVo.getStartNumber());
        return findPage(sql, params, pageInfo);
    }

    /**
     * 首页资产管理图标数据
     *
     * @param qxmId
     * @return
     */
    public List loadChartDataAsset(int qxmId) {
        String sql = "SELECT " +
                " YEAR (ac.purchase_date) years, " +
                " SUM(ac.price) zijin ," +
                " ROUND(SUM(ac.price)/(SELECT SUM(acc.price) FROM tab_accessory acc LEFT JOIN tab_school tts ON acc.school_code=tts.school_code WHERE tts.qxm=ts.qxm),2 ) " +
                " FROM " +
                " tab_accessory ac " +
                " LEFT JOIN tab_school ts ON ts.school_code = ac.school_code " +
                " WHERE ts.qxm=:qxmId " +
                " GROUP BY " +
                " YEAR (ac.purchase_date)";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("qxmId", qxmId);
        return findBySql(sql, params);
    }

    /**
     * 首页温度图形
     *
     * @param qxmId
     * @return
     */
    public List loadEnvironmentIndex(String qxmId) {
        String sql = "SELECT " +
                " ts.school_code, " +
                " eq.temperature, " +
                " eq.humidity, " +
                " eq.pm2_5, " +
                " eq.illuminate, " +
                " eq.noise " +
                "FROM tab_equipment eq " +
                "LEFT JOIN tab_classroom room ON room.equipment_no = eq.equipment_no  " +
                "LEFT JOIN tab_teach_building ttb on ttb.id = room.building_id  " +
                "LEFT JOIN tab_school ts ON ts.school_code = ttb.school_code " +
                "WHERE ts.school_code=:qxmId  GROUP BY ts.school_code ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("qxmId", qxmId);
        return findBySql(sql, params);
    }

    /**
     * 首页资产管理完好率
     *
     * @return
     */
    public List LoadClassRoomEQuiMent(int qxmId) {
        String sql = "SELECT COUNT(tc.id),ROUND(COUNT(tc.id)/(SELECT COUNT(tc.id)FROM tab_classes tc LEFT JOIN tab_classroom tcr ON tc.room_id = tcr.id LEFT JOIN tab_equipment te ON te.equipment_no = tcr.equipment_no),2), " +
                "(SELECT COUNT(tc.id) FROM tab_equipment te LEFT JOIN tab_classroom tcr ON te.equipment_no = tcr.equipment_no LEFT JOIN tab_classes tc ON tc.room_id = tcr.id)  " +
                "FROM tab_classes tc " +
                "LEFT JOIN tab_classroom tcr ON tc.room_id = tcr.id " +
                "LEFT JOIN tab_equipment te ON te.equipment_no = tcr.equipment_no  " +
                "LEFT JOIN tab_school ts on ts.school_code = tc.school_code  " +
                "WHERE tc.id not in (SELECT ts.class_id FROM tab_operations ts where ts.end_date is NULL ) and ts.qxm=:qxmId ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("qxmId", qxmId);
        return findBySql(sql, params);
    }


    /**
     * 查询设备使用记录
     *
     * @param applicationVo
     * @return
     */
    public Page selectUsedRecord(ApplicationVo applicationVo) {
        Map<String, Object> params = new HashMap<String, Object>();
        String sql = "SELECT " +
                " ts.`name`, " +
                " CONCAT(ttb.building_name,tcr.room_code), " +
                " tur.equipment_no,  " +
                " CONCAT(tg.`name`,tcs.class_name), " +
                " tu.real_name, " +
                " tsj.subject_name, " +
                " tur.zt5_start, " +
                " tur.zt5_end, " +
                " (UNIX_TIMESTAMP(tur.zt5_end)-UNIX_TIMESTAMP(tur.zt5_start))/3600  " +
                " FROM " +
                " tab_used_record tur  " +
                " LEFT JOIN tab_user tu ON tu.icard_no=tur.icard_no " +
                " LEFT JOIN tab_subject tsj ON tu.subject_id=tsj.id " +
                " LEFT JOIN tab_classroom  tcr ON tcr.equipment_no=tur.equipment_no " +
                " LEFT JOIN tab_teach_building ttb ON ttb.id=tcr.building_id " +
                " LEFT JOIN tab_school ts ON ts.school_code=ttb.school_code " +
                " LEFT JOIN tab_classes tcs ON tcs.room_id=tcr.id " +
                " LEFT JOIN tab_grade tg ON tg.id=tcs.grade_id " +
                " WHERE ts.`name` LIKE :schoolName  AND CONCAT(tg.`name`,tcs.class_name)" +
                " like :className " ;


        if (!"%%".equals(applicationVo.getTeacherName())) {
            sql += "AND tu.real_name LIKE :teacherName ";
            params.put("teacherName", applicationVo.getTeacherName());
        }
        sql+=" ORDER BY tur.collect_time DESC";

        params.put("schoolName", applicationVo.getSchoolName());
        params.put("className", applicationVo.getClassName());

        PageInfo pageInfo = new PageInfo();
        pageInfo.setRowsOfPage(applicationVo.getSizeNumber());
        pageInfo.setCurrentPage(applicationVo.getStartNumber());
        return findPage(sql, params, pageInfo);
    }

    /**
     * 手机接口 查询单个教室详情数据
     * @param schoolCode
     * @return
     */
    public List findSingleApplication(String schoolCode,String className) {
        Map<String, Object> params = new HashMap<String, Object>();
        String sql = "SELECT " +
                " tg.`name`, " +
                " tc.class_name, " +
                " tb.building_name, " +
                " tr.room_code, " +
                " tu.real_name, " +
                " tsb.subject_name, " +
                "  te.work_status, " +
                "  te.pc_status, " +
                "  te.display_status, " +
                "  te.msgsource_status, " +
                "  te.power, " +
                "  ts.school_code, " +
                "  tr.id roomid, " +
                "  tc.id tcid ," +
                "  te.lights_status, " +
                "  te.sockets_status, " +
                "  te.aircondition_status," +
                "  te.fan_status," +
                "  te.equipment_no," +
                "  te.equipment_type," +
                "  tr.room_type," +
                "  tg.id tgId  " +
                "  FROM " +
                " tab_classroom tr " +
                " LEFT JOIN tab_teach_building tb ON tb.id = tr.building_id " +
                " LEFT JOIN tab_school ts ON ts.school_code=tb.school_code " +
                " LEFT JOIN tab_classes tc ON tr.id = tc.room_id " +
                " LEFT JOIN tab_grade tg ON tg.id = tc.grade_id " +
                " LEFT JOIN tab_equipment te ON tr.equipment_no = te.equipment_no " +
                " LEFT JOIN tab_user tu ON tu.icard_no = te.icard_no  " +
                " LEFT JOIN tab_subject tsb ON tsb.id = tu.subject_id " +
                " WHERE ts.school_code=:schoolCode AND CONCAT(tg.`name`, tc.class_name) = :className ";
        params.put("schoolCode",schoolCode);
        params.put("className",className);
        return findBySql(sql, params);
    }


    /**
     * 手机接口 查询单个学校数据教室概览
     * @param schoolCode
     * @return
     */
    public List findSingleGeneralView(String schoolCode) {
        Map<String, Object> params = new HashMap<String, Object>();
        String sql = "SELECT " +
                " CONCAT(tg.`name`, tc.class_name), " +
                " te.work_status, " +
                " (SELECT COUNT(*) FROM tab_accessory taa WHERE taa.room_id=tr.id), " +
                "  tr.equipment_no," +
                "  ts.school_code  " +
                " FROM " +
                " tab_classroom tr " +
                " LEFT JOIN tab_teach_building tb ON tb.id = tr.building_id " +
                " LEFT JOIN tab_school ts ON ts.school_code = tb.school_code " +
                " LEFT JOIN tab_classes tc ON tr.id = tc.room_id " +
                " LEFT JOIN tab_grade tg ON tg.id = tc.grade_id " +
                " LEFT JOIN tab_equipment te ON tr.equipment_no = te.equipment_no " +
                " LEFT JOIN tab_user tu ON tu.icard_no = te.icard_no " +
                " LEFT JOIN tab_subject tsb ON tsb.id = tu.subject_id " +
                " WHERE ts.school_code=:schoolCode  ";
        params.put("schoolCode",schoolCode);

        return findBySql(sql, params);
    }


    /**
     * 手机页面查询概况应用监测使用率
     *
     * @return
     */
    public List selectMobileDistinctSchoolList(String schoolCode) {
        Map<String, Object> params = new HashMap<String, Object>();
        String sql = "SELECT   " +
                "tcy.`name` tcyName,   " +
                "ts.`name` tsName,   " +
                "(SELECT count(tcs.id) FROM tab_classes tcs where tcs.school_code = ts.school_code) banjizongshu,   " +
                "(SELECT count(tcsr.id) FROM tab_classroom tcsr LEFT JOIN tab_teach_building ttb ON ttb.id=tcsr.building_id  where tcsr.equipment_no is not null AND ttb.school_code=ts.school_code ) jiancheshu,   " +
                "(SELECT COUNT(ee.equipment_no) FROM tab_equipment ee LEFT JOIN tab_classroom rr ON rr.equipment_no=ee.equipment_no LEFT JOIN tab_teach_building btt ON btt.id=rr.building_id WHERE btt.school_code=ts.school_code and ee.work_status=1) yunxing,   " +
                "ROUND((SELECT COUNT(ee.equipment_no) FROM tab_equipment ee LEFT JOIN tab_classroom rr ON rr.equipment_no=ee.equipment_no LEFT JOIN tab_teach_building btt ON btt.id=rr.building_id WHERE btt.school_code=ts.school_code and ee.work_status=1) " +
                "/(SELECT count(tcsr.id) FROM tab_classroom tcsr LEFT JOIN tab_teach_building ttb ON ttb.id=tcsr.building_id  where tcsr.equipment_no is not null AND ttb.school_code=ts.school_code ),2) shiyonlv,   " +
                "ts.school_code   " +
                "FROM   tab_school ts " +
                "LEFT JOIN tab_cityarea tcy on tcy.detail_code = ts.qxm " +
                "LEFT JOIN tab_school_type tst on tst.id=ts.type " +
                " where   ts.school_code like :schoolCode  AND ts.type!=11";
        params.put("schoolCode", schoolCode);
        return findBySql(sql, params);
    }

    /**
     * 手机接口 分页查询单个教室详情数据
     * @param schoolCode
     * @return
     */
    public Page findPageSingleApplication(String schoolCode,String className,int startNumber,int sizeNumber) {
        Map<String, Object> params = new HashMap<String, Object>();
        String sql = "SELECT " +
                " tg.`name`, " +
                " tc.class_name, " +
                " tb.building_name, " +
                " tr.room_code, " +
                " tu.real_name, " +
                " tsb.subject_name, " +
                "  te.work_status, " +
                "  te.pc_status, " +
                "  te.display_status, " +
                "  te.msgsource_status, " +
                "  te.power, " +
                "  ts.school_code, " +
                "  tr.id roomid, " +
                "  tc.id tcid ," +
                "  te.lights_status, " +
                "  te.sockets_status, " +
                "  te.aircondition_status," +
                "  te.fan_status," +
                "  te.equipment_no," +
                "  te.equipment_type," +
                "  tr.room_type," +
                "  tg.id tgId  " +
                "  FROM " +
                " tab_classroom tr " +
                " LEFT JOIN tab_teach_building tb ON tb.id = tr.building_id " +
                " LEFT JOIN tab_school ts ON ts.school_code=tb.school_code " +
                " LEFT JOIN tab_classes tc ON tr.id = tc.room_id " +
                " LEFT JOIN tab_grade tg ON tg.id = tc.grade_id " +
                " LEFT JOIN tab_equipment te ON tr.equipment_no = te.equipment_no " +
                " LEFT JOIN tab_user tu ON tu.icard_no = te.icard_no  " +
                " LEFT JOIN tab_subject tsb ON tsb.id = tu.subject_id " +
                " WHERE ts.school_code=:schoolCode AND CONCAT(tg.`name`, tc.class_name) like :className ";
        params.put("schoolCode",schoolCode);
        params.put("className",className);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setRowsOfPage(sizeNumber);
        pageInfo.setCurrentPage(startNumber);
        return findPage(sql, params,pageInfo);
    }

}




















