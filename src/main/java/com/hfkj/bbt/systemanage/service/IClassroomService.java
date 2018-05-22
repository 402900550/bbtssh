package com.hfkj.bbt.systemanage.service;

import com.alibaba.fastjson.JSONObject;
import com.hfkj.bbt.base.entity.*;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.systemanage.web.vo.ClassVo;
import com.hfkj.bbt.systemanage.web.vo.SearchVo;

import java.util.List;

/**
 * Created by Administrator on 2017-10-30.
 */
public interface IClassroomService {


    Page selectClassroomList(SearchVo searchVo);


    ClassRoom getClassRoom(Long roomId);



    /**
     * 查询所有年级
     * @return
     */
    List<Grade> selectGrade();


    /**
     * 根据id查询所有教室编号
     * @param buildingId
     * @return
     */
    List<ClassRoom> getClassAndRoom(Long buildingId);


    List<ClassRoom> getClassRoomByBuilding(Long buildingId);



    /**
     * 修改设备
     * @param classVo
     * @return
     */
    ResponseEntity updateClassRoomList(ClassVo classVo);

    /**
     * 添加设备与教室
     * @param classVo
     * @return
     */
    ResponseEntity saveClassRoom(ClassVo classVo);

    /**
     * 根据id删除设备与教室
     * @param roomId
     */
    void deleteRoomById(Long roomId);

    /**
     * 根据学校Id与年级Id查询班级
     * @return
     */
    List<Classes> getGradeClassAndSchool(String schoolCode, Long gradeId);

    /**
     * 加载绑定设备需要的数据
     * @param developType
     * @param schoolCode
     * @return
     */
    JSONObject loadEquipmentsByDevelopType(Long developType, String schoolCode);


    JSONObject loadBuildingAndClassRoomByClass(Long classId);

}
