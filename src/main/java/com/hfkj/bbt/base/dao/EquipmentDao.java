package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.assetmanage.web.vo.AssetSearchVo;
import com.hfkj.bbt.base.dao.BaseDao;
import com.hfkj.bbt.base.entity.Equipment;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.base.page.PageInfo;
import com.hfkj.bbt.systemanage.web.vo.SearchVo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-10-20.
 */
@Repository
public class EquipmentDao extends BaseDao<Equipment> {


    public Equipment findEquipmentByNo(String equipmentNo,Integer equipmentType){
        String hql="FROM Equipment WHERE   equipmentNo=? AND equipmentType=?";
        return findOne(hql,new Object[]{equipmentNo,equipmentType});
    }

    public List<Equipment> findEquipment(){
        String hql="FROM Equipment";
        return find(hql);
    }

    public List<Equipment> findEquipment(String schoolCode){
        String hql="FROM Equipment WHERE schoolCode=? AND status=0";
        return find(hql,new Object[]{schoolCode});
    }

    public List<Equipment> findEquipment(String schoolCode,Integer equipmentType){
        String hql="FROM Equipment WHERE schoolCode=? AND equipmentType=? ";
        return find(hql,new Object[]{schoolCode,equipmentType});
    }

    public Equipment findEquipmentByNo(String equipmentNo){
        String hql="FROM Equipment WHERE equipmentNo=? ";
        return findOne(hql,new Object[]{equipmentNo});
    }






    /**
     * 查询学校设备
     * @param assetSearchVo
     * @return
     */
    public Page selectEquimentSchoolAndClassRoom(AssetSearchVo assetSearchVo) {
        Map<String,Object> params=new HashMap<String,Object>();
        String sql="SELECT  " +
                " tcy.`name` tcyName, " +
                " ts.`name` tsName, " +
                " ts.school_code, " +
                " (SELECT count(cscs.id) from tab_classes cscs WHERE cscs.school_code=ts.school_code) classAll, " +
                " (SELECT count(tcsr.id) FROM tab_classroom tcsr where tcsr.equipment_no is not null and tcsr.building_id = ttb.id ), " +
                " (SELECT COUNT(tacs.id) FROM tab_accessory tacs where tacs.school_code = ts.school_code) facilitiesAll, " +
                " (SELECT COUNT(teq.facilities) FROM tab_accessory teq where teq.facilities = 1 and teq.school_code= ts.school_code ) facilitiesSim, " +
                " (SELECT SUM(tcm.cost) from tab_classroom tcm where tcm.building_id = ttb.id ), " +
                " (SELECT sum(tas.price) from tab_accessory tas  WHERE tas.facilities != 3 and tas.school_code = ts.school_code ), " +
                "  max(tac.purchase_date), " +
                "  min(tac.purchase_date), " +
                " ROUND((SELECT COUNT(teq.facilities) FROM tab_accessory teq where teq.facilities = 1 and teq.school_code = ts.school_code )/(SELECT COUNT(tacs.id) FROM tab_accessory tacs where tacs.school_code = ts.school_code),2) " +
                " FROM  " +
                " tab_school ts  " +
                " LEFT JOIN tab_teach_building ttb ON ttb.school_code = ts.school_code  " +
                " LEFT JOIN tab_classroom tcr ON tcr.building_id = ttb.id  " +
                " LEFT JOIN tab_cityarea tcy ON ts.qxm = tcy.detail_code  " +
                " LEFT JOIN tab_accessory tac ON tac.school_code = ts.school_code  " +
//                " WHERE (SELECT count(cscs.id) from tab_classes cscs WHERE cscs.school_code=ts.school_code)>0 " +
                " WHERE tcy.`name` LIKE :distinctName AND ts.`name` LIKE :schoolName AND ts.type!=11" ;
        if(assetSearchVo.getSchoolType()!=10086){
            sql+=" AND ts.type LIKE :schoolType ";
            params.put("schoolType",assetSearchVo.getSchoolType());
        }
        sql+= " GROUP BY ts.school_code ORDER BY classAll desc";
        params.put("distinctName",assetSearchVo.getDistinctName());
        params.put("schoolName",assetSearchVo.getSchoolName());
        PageInfo pageInfo =new PageInfo();
        pageInfo.setRowsOfPage(assetSearchVo.getSizeNumber());
        pageInfo.setCurrentPage(assetSearchVo.getStartNumber());
        return findPage(sql,params,pageInfo);

    }

    public Page loadEquimentSchoolAndClassRoom() {
        Map<String,Object> params=new HashMap<String,Object>();
        String sql="SELECT  " +
                " tcy.`name` tcyName, " +
                " ts.`name` tsName, " +
                " ts.school_code, " +
                " (SELECT count(cscs.id) from tab_classes cscs WHERE cscs.school_code=ts.school_code) classAll, " +
                " (SELECT count(tcsr.id) FROM tab_classroom tcsr where tcsr.equipment_no is not null and tcsr.building_id = ttb.id ), " +
                " (SELECT COUNT(tacs.id) FROM tab_accessory tacs where tacs.school_code = ts.school_code) facilitiesAll, " +
                " (SELECT COUNT(teq.facilities) FROM tab_accessory teq where teq.facilities = 1 and teq.school_code= ts.school_code ) facilitiesSim, " +
                " (SELECT SUM(tcm.cost) from tab_classroom tcm where tcm.building_id = ttb.id ), " +
                " (SELECT sum(tas.price) from tab_accessory tas  WHERE tas.facilities != 3 and tas.school_code = ts.school_code ), " +
                "  max(tac.purchase_date), " +
                "  min(tac.purchase_date), " +
                " ROUND((SELECT COUNT(teq.facilities) FROM tab_accessory teq where teq.facilities = 1 and teq.school_code = ts.school_code )/(SELECT COUNT(tacs.id) FROM tab_accessory tacs where tacs.school_code = ts.school_code),2) " +
                " FROM  " +
                " tab_school ts  " +
                " LEFT JOIN tab_teach_building ttb ON ttb.school_code = ts.school_code  " +
                " LEFT JOIN tab_classroom tcr ON tcr.building_id = ttb.id  " +
                " LEFT JOIN tab_cityarea tcy ON ts.qxm = tcy.detail_code  " +
                " LEFT JOIN tab_accessory tac ON tac.room_id = tcr.id ";
//                " where (SELECT count(cscs.id) from tab_classes cscs WHERE cscs.school_code=ts.school_code)>0 ";
        sql+= " GROUP BY ts.school_code ORDER BY classAll desc ";
        PageInfo pageInfo =new PageInfo();
        return findPage(sql,params,pageInfo);
    }

    /**
     * 查询教室班级设备
     * @param assetSearchVo
     * @return
     */
    public Page selectClassRoomEquiment(AssetSearchVo assetSearchVo) {
        Map<String,Object> params=new HashMap<String,Object>();
        String sql="SELECT " +
                " tg.`name` tgName, " +
                " tc.class_name tcName, " +
                " tdt.`name` tdtName, " +
                " tcr.equipment_no, " +
                " COUNT(tas.facilities), " +
                " (SELECT COUNT(ta.facilities) FROM tab_accessory ta where ta.facilities = 1 and ta.room_id = tcr.id ), " +
                " SUM(tas.price), " +
                " tcr.cost, " +
                " MAX(tas.purchase_date), " +
                " MIN(tas.purchase_date), " +
                " tcr.id tcrId, " +
                " tcr.building_id tvId, " +
                " tc.id classId " +
                " FROM tab_classes tc  " +
                " LEFT JOIN tab_classroom tcr on tcr.id= tc.room_id  " +
                " LEFT JOIN tab_grade tg on tg.id = tc.grade_id  " +
                " LEFT JOIN tab_develop_type tdt on tdt.id=tcr.develop_type_id  " +
                " LEFT JOIN tab_accessory tas on tas.room_id = tcr.id  " +
                " where tc.school_code = :schoolCode "+
                " and tg.`name` like :gradeName and  tc.class_name like :className ";
        sql+= " GROUP BY tc.id ";
        params.put("schoolCode",assetSearchVo.getSchoolCode());
        params.put("className",assetSearchVo.getClassName());
        params.put("gradeName",assetSearchVo.getGradeName());
        PageInfo pageInfo =new PageInfo();
        pageInfo.setRowsOfPage(assetSearchVo.getSizeNumber());
        pageInfo.setCurrentPage(assetSearchVo.getStartNumber());
        return findPage(sql,params,pageInfo);
    }

    /**
     * 查询学校年级班级教室
     * @param classId
     * @return
     */
    public Page selectSchoolClassAndClassRoom(Long classId) {
        Map<String,Object> params=new HashMap<String,Object>();
        String sql="SELECT ts.`name` tsName,tg.`name` tgName,tc.class_name tcName,ttb.building_name ttbName,tcr.room_code,tcr.room_type,tc.id  " +
                " FROM tab_classes tc  " +
                " LEFT JOIN tab_school ts on ts.school_code = tc.school_code  " +
                " LEFT JOIN tab_classroom tcr on  tcr.id = tc.room_id  " +
                " LEFT JOIN tab_grade tg on tg.id = tc.grade_id  " +
                " LEFT JOIN tab_teach_building ttb on ttb.id = tcr.building_id  " +
                " where tc.id = "+classId;
        PageInfo pageInfo =new PageInfo();
        return findPage(sql,params,pageInfo);
    }

    /**
     * 查询班级所有设备
     * @param classId
     * @param startNumber
     * @param sizeNumber
     * @return
     */
    public Page selectEquipmentClassRoomList(Long classId,int startNumber,int sizeNumber) {
        Map<String,Object> params=new HashMap<String,Object>();
        String sql="SELECT tdt.`name`,tp.detail,ta.price,ta.purchase_date,ta.used_time,ta.repair_times,ta.facilities,now(),ta.id " +
                " FROM tab_classes tc  " +
                " LEFT JOIN tab_accessory ta on ta.room_id = tc.room_id  " +
                " LEFT JOIN tab_develop_type tdt on tdt.id = ta.equipment_type " +
                " LEFT JOIN tab_parameters tp on tp.id = ta.size_type " +
                " where tc.id = :classId ";
        params.put("classId",classId);
        PageInfo pageInfo =new PageInfo();
        pageInfo.setRowsOfPage(sizeNumber);
        pageInfo.setCurrentPage(startNumber);
        return findPage(sql,params,pageInfo);
    }

    /**
     * 查询地区的使用情况
     * @param qxmId
     * @return
     */
    public Page loadDataAnalysisCityareaList(String qxmId) {
        Map<String, Object> params = new HashMap<String, Object>();
        String sql = "SELECT COUNT(*), " +
                " (SELECT COUNT(*) FROM tab_equipment tet  " +
                " LEFT JOIN tab_classroom tc on tet.equipment_no = tc.equipment_no  " +
                " LEFT JOIN tab_teach_building ttb on ttb.id = tc.building_id  " +
                " LEFT JOIN tab_school ts on ts.school_code = ttb.school_code  " +
                "  where ts.qxm = "+qxmId+"  and tet.is_used = 1) " +
                "FROM tab_equipment te  " +
                "LEFT JOIN tab_classroom tc on te.equipment_no = tc.equipment_no  " +
                "LEFT JOIN tab_teach_building ttb on ttb.id = tc.building_id  " +
                "LEFT JOIN tab_school ts on ts.school_code = ttb.school_code  " +
                "WHERE  te.is_used != 1 and ts.qxm = "+qxmId;
        PageInfo pageInfo = new PageInfo();
        return findPage(sql, params, pageInfo);
    }

    /**
     * 查询学校的使用情况
     * @param schoolCode
     * @return
     */
    public Page loadDataAnalysisSchoolCityarea(String schoolCode) {
        Map<String, Object> params = new HashMap<String, Object>();
        String sql = "SELECT COUNT(*), " +
                " (SELECT COUNT(*) FROM tab_equipment tet  " +
                " LEFT JOIN tab_classroom tc on tet.equipment_no = tc.equipment_no  " +
                " LEFT JOIN tab_teach_building ttb on ttb.id = tc.building_id  " +
                " LEFT JOIN tab_school ts on ts.school_code = ttb.school_code  " +
                "  where ts.school_code = "+schoolCode+"  and tet.is_used = 1) " +
                "FROM tab_equipment te  " +
                "LEFT JOIN tab_classroom tc on te.equipment_no = tc.equipment_no  " +
                "LEFT JOIN tab_teach_building ttb on ttb.id = tc.building_id  " +
                "LEFT JOIN tab_school ts on ts.school_code = ttb.school_code  " +
                "WHERE  te.is_used != 1 and ts.school_code = "+schoolCode;
        PageInfo pageInfo = new PageInfo();
        return findPage(sql, params, pageInfo);
    }

    /**
     * 七天不使用故障
     * @return
     */
    public List getOperationsEquipment(){
        Map<String,Object> params=new HashMap<String,Object>();
        String sql="SELECT ts.school_code,ts.`name`,tc.id tId,(SELECT COUNT(tu.id) FROM tab_used_record tu where tu.collect_time BETWEEN DATE_ADD(NOW(),INTERVAL -7 DAY) AND NOW() and tu.equipment_no = te.equipment_no ) " +
                "FROM tab_equipment te  " +
                "LEFT JOIN tab_classroom tcr on tcr.equipment_no=te.equipment_no  " +
                "LEFT JOIN tab_classes tc on tc.room_id = tcr.id  " +
                "LEFT JOIN tab_school ts on ts.school_code = tc.school_code  " +
                "GROUP BY te.equipment_no  ";
        return findBySql(sql,params);
    }


    /**
     * 频繁使用设备
     * @return
     */
    public List getFrequentlyEquipment(){
        Map<String,Object> params=new HashMap<String,Object>();
        String sql="SELECT ts.school_code,ts.`name`,tc.id tId,(SELECT COUNT(tu.id) FROM tab_used_record tu where DATE_FORMAT(tu.collect_time,'%y%m%d') = DATE_FORMAT(NOW(),'%y%m%d') and tu.equipment_no = te.equipment_no ) " +
                "FROM tab_equipment te  " +
                "LEFT JOIN tab_classroom tcr on tcr.equipment_no=te.equipment_no  " +
                "LEFT JOIN tab_classes tc on tc.room_id = tcr.id  " +
                "LEFT JOIN tab_school ts on ts.school_code = tc.school_code  " +
                "GROUP BY te.equipment_no ";
        return findBySql(sql,params);
    }

    /**
     * 查询地区的使用情况(首页)
     * @param qxmId
     * @return
     */
    public List loadDataAnalysisCityarea(int qxmId) {
        Map<String, Object> params = new HashMap<String, Object>();
        String sql = "SELECT ROUND(COUNT(*)/ " +
                " (SELECT COUNT(*) FROM tab_equipment tet  " +
                " LEFT JOIN tab_classroom tc on tet.equipment_no = tc.equipment_no  " +
                " LEFT JOIN tab_teach_building ttb on ttb.id = tc.building_id  " +
                " LEFT JOIN tab_school ts on ts.school_code = ttb.school_code  " +
                "  where ts.qxm = "+qxmId+" ),2) " +
                "FROM tab_equipment te  " +
                "LEFT JOIN tab_classroom tc on te.equipment_no = tc.equipment_no  " +
                "LEFT JOIN tab_teach_building ttb on ttb.id = tc.building_id  " +
                "LEFT JOIN tab_school ts on ts.school_code = ttb.school_code  " +
                "WHERE  te.is_used = 1 and ts.qxm = "+qxmId;
        return findBySql(sql, params);
    }

}
