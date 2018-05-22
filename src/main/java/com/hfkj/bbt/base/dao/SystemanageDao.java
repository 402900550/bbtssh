package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.base.dao.BaseDao;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.base.page.PageInfo;
import com.hfkj.bbt.systemanage.web.vo.SearchVo;

import com.hfkj.bbt.systemanage.web.vo.UserVo;
import org.springframework.stereotype.Repository;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/24 0024.
 */
@Repository
public class SystemanageDao extends BaseDao {


    /**
     * 查询教学楼
     * @param searchVo
     * @return
     */
    public Page selectTeachBuilding(SearchVo searchVo){
        String sql=" SELECT ttb.building_name,ts.`name`,ttb.id ttbid,ts.school_code  " +
                " FROM tab_teach_building ttb " +
                " LEFT JOIN tab_school ts ON ts.school_code = ttb.school_code  " +
                " LEFT JOIN tab_cityarea tc ON tc.detail_code = ts.qxm " +
                " WHERE  tc.name like :distinctName AND ts.name like :schoolName AND " +
                " ttb.building_name like :buildingName ";
        Map<String,Object> params=new HashMap<String,Object>();
        //params.put("distinctName",searchVo.getDistinctName());
        params.put("schoolName",searchVo.getSchoolName());
        params.put("buildingName",searchVo.getBuildingName());
        PageInfo pageInfo =new PageInfo();
        pageInfo.setRowsOfPage(searchVo.getSizeNumber());
        pageInfo.setCurrentPage(searchVo.getStartNumber());
        return findPage(sql,params,pageInfo);
    }

    /**
     * 根据条件查询所有学校详情
     * @param searchVo
     * @return
     */
    public Page selectSchoolList(SearchVo searchVo){
        Map<String,Object> params=new HashMap<String,Object>();
        String sql=" SELECT ts.`name` tsname ,tst.type_name typename,ts.school_code,ts.telephone,ts.address,ts.description,ts.id,tc.name tcname " +
                " FROM tab_school ts  " +
                " LEFT JOIN tab_cityarea tc on tc.detail_code=ts.qxm " +
                " LEFT JOIN tab_school_type tst on tst.id=ts.type " +
                " where tc.name like :distinctName " +
                " AND ts.`name` like :schoolName ";
        if(!(10086==searchVo.getSchoolType())){
            sql+=" AND ts.type = :schoolType  ";
            params.put("schoolType", searchVo.getSchoolType());
        }
        params.put("distinctName",searchVo.getDistinctName());
        params.put("schoolName",searchVo.getSchoolName());
        PageInfo pageInfo =new PageInfo();
        pageInfo.setRowsOfPage(searchVo.getSizeNumber());
        pageInfo.setCurrentPage(searchVo.getStartNumber());
        return findPage(sql,params,pageInfo);
    }


    /**
     * 查询用户
     * @param searchVo
     * @return
     */
    public Page selectUserList(SearchVo searchVo){
        Map<String,Object> params=new HashMap<String,Object>();
        String sql=" SELECT ts.`name` tsName,tu.real_name tuName,tu.sex,tu.birthday,tu.educational,tu.professional,tb.subject_name sname,tr.description,tu.id,tr.role_level,tu.icard_no,tu.school_code " +
                " FROM tab_user tu " +
                " LEFT JOIN tab_school ts ON ts.school_code = tu.school_code " +
                " LEFT JOIN tab_cityarea tc on tc.detail_code=ts.qxm  " +
                " LEFT JOIN tab_role_user tru on tru.user_id = tu.id  " +
                " LEFT JOIN tab_role tr on tr.id = tru.role_id " +
                " LEFT JOIN tab_subject tb on tb.id = tu.subject_id  " +
                " where tc.`name` like :distinctName AND ts.`name` like :schoolName " +
                " and tu.real_name like :userName " +
                " GROUP BY tu.id ";
        params.put("distinctName",searchVo.getDistinctName());
        params.put("schoolName",searchVo.getSchoolName());
        params.put("userName",searchVo.getUserName());
        PageInfo pageInfo =new PageInfo();
        pageInfo.setRowsOfPage(searchVo.getSizeNumber());
        pageInfo.setCurrentPage(searchVo.getStartNumber());
        return findPage(sql,params,pageInfo);
    }


    /**
     * 查询教室
     * @return
     */
    public Page selectClassroomList(SearchVo searchVo){
        Map<String,Object> params=new HashMap<String,Object>();
        String sql="SELECT tcy.`name` qxname, " +
                " ts.`name` schoolname, " +
                " ts.school_code schoolcode, " +
                " ttb.building_name, " +
                " tc.id roomid, " +
                " tc.room_code, " +
                " tc.room_type, " +
                " tc.`status` tcstatus, " +
                " tc.equipment_no, " +
                " class.id classid, " +
                " class.class_name, " +
                " gg. NAME ggname " +
                " FROM tab_classroom tc " +
                " LEFT JOIN tab_teach_building ttb ON tc.building_id = ttb.id " +
                " LEFT JOIN tab_school ts ON ts.school_code = ttb.school_code " +
                " LEFT JOIN tab_cityarea tcy ON tcy.detail_code = ts.qxm " +
                " LEFT JOIN tab_classes class ON class.room_id = tc.id  " +
                " LEFT JOIN tab_grade gg ON gg.id = class.grade_id " +
                " WHERE ttb.id = :buildingId AND tc.room_code LIKE :roomCode ";
        params.put("buildingId",searchVo.getBuildingId());
        params.put("roomCode",searchVo.getRoomCode());
        PageInfo pageInfo =new PageInfo();
        pageInfo.setRowsOfPage(searchVo.getSizeNumber());
        pageInfo.setCurrentPage(searchVo.getStartNumber());
        return findPage(sql,params,pageInfo);
    }





    public Page selectClass(SearchVo searchVo) {
        Map<String,Object> params=new HashMap<String,Object>();
        String sql="SELECT" +
                " ts.`name` sname," +
                " tc.`name` areaname," +
                " tg.`name` gname," +
                " class.class_name," +
                " ttb.building_name," +
                " room.room_code," +
                " room.equipment_no," +
                " ts.school_code," +
                " class.id classid," +
                " room.id " +
                " FROM" +
                " tab_classes class" +
                " LEFT JOIN tab_school ts ON ts.school_code = class.school_code" +
                " LEFT JOIN tab_cityarea tc ON ts.qxm = tc.detail_code" +
                " LEFT JOIN tab_grade tg ON tg.id = class.grade_id" +
                " LEFT JOIN tab_classroom room ON room.id = class.room_id" +
                " LEFT JOIN tab_teach_building ttb ON ttb.id = room.building_id" +
                " WHERE" +
                " tc.`name` LIKE :distinctName " +
                " AND ts.`name` LIKE :schoolName " +
                " AND CONCAT(tg.`name`, class.class_name) LIKE :className ORDER BY class.class_name ";
        
        params.put("distinctName",searchVo.getDistinctName());
        params.put("schoolName",searchVo.getSchoolName());
        params.put("className",searchVo.getClassName());
        PageInfo pageInfo =new PageInfo();
        pageInfo.setRowsOfPage(searchVo.getSizeNumber());
        pageInfo.setCurrentPage(searchVo.getStartNumber());
        return findPage(sql,params,pageInfo);
    }


    /**
     * 查询角色id
     * @return
     */
    public List selectRoleList(Long userId){
        String sql=" SELECT tr.role_id " +
                " FROM tab_user tu  " +
                " LEFT JOIN tab_role_user tr on tr.user_id=tu.id " +
                " where tu.id = :userId ";
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("userId",userId);
        return findBySql(sql,params);
    }

    /**
     * 根据roomId查询设备
     * @param roomId
     * @return
     */
    public List selectClassRoomList(Long roomId){
        String sql="SELECT " +
                " tc.room_type, " +
                " tc.room_code, " +
                " con.equipment_no, " +
                " tg.id gradeId, " +
                " class.id classId, " +
                " class.school_code " +
                "FROM  tab_classroom tc  " +
                "LEFT JOIN tab_connection con ON con.room_id = tc.id " +
                "LEFT JOIN tab_classes class ON class.id = con.class_id  " +
                "LEFT JOIN tab_grade tg on tg.id=class.grade_id  " +
                "where tc.id = :roomId";
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("roomId",roomId);
        return findBySql(sql,params);
    }



    public List selectGradeBySchoolCodeToSchedule(String schoolCode){
        String sql="SELECT " +
                " tg.id gradeid, " +
                " tg.`name` tname, " +
                " cs.school_code " +
                " FROM " +
                " tab_classes cs, " +
                " tab_grade tg " +
                " WHERE " +
                " cs.school_code =:schoolCode " +
                " AND tg.id = cs.grade_id " +
                " GROUP BY " +
                " tg.id, " +
                " cs.school_code";
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("schoolCode",schoolCode);
        return findBySql(sql,params);
    }


}















