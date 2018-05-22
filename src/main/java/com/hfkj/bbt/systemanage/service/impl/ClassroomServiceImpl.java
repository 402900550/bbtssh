package com.hfkj.bbt.systemanage.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hfkj.bbt.base.dao.*;
import com.hfkj.bbt.base.entity.*;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.systemanage.service.IClassroomService;
import com.hfkj.bbt.systemanage.web.vo.ClassVo;
import com.hfkj.bbt.systemanage.web.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017-10-30.
 */
@Service
public class ClassroomServiceImpl implements IClassroomService {


    @Autowired
    private SystemanageDao systemanageDao;

    @Autowired
    private ClassRoomDao classRoomDao;

    @Autowired
    private GradeDao gradeDao;

    @Autowired
    private ClassDao classDao;

    @Autowired
    private BuildingDao buildingDao;

    @Autowired
    private EquipmentDao equipmentDao;

    @Autowired
    private DevelopTypeDao developTypeDao;

    @Autowired
    private AccessoryDao accessoryDao;


    @Override
    public Page selectClassroomList(SearchVo searchVo) {
        return systemanageDao.selectClassroomList(searchVo);
    }

    @Override
    public ClassRoom getClassRoom(Long roomId) {
        return classRoomDao.getClassRoom(roomId);
    }



    @Override
    public List<Grade> selectGrade(){
        return gradeDao.getGrades();
    }




    @Override
    public List<ClassRoom> getClassAndRoom(Long buildingId) {
        return  classRoomDao.getClassRoomByBuilding(buildingId);
    }



    @Override
    public List<ClassRoom> getClassRoomByBuilding(Long buildingId) {
        return classRoomDao.selectClassRoom(buildingId);
    }


    @Transactional(readOnly = false)
    public ResponseEntity updateClassRoomList(ClassVo classVo){
        //首先清除以前教室的状态
        ClassRoom classRoom = classRoomDao.getClassRoom(classVo.getRoomId());
        classRoom.setStatus(0);
        classRoom.setRoomCode(classVo.getRoomCode());
        classRoom.setRoomType(classVo.getRoomType());
        Classes oldClass=classDao.getClassRoomId(classVo.getRoomId());
        if (oldClass!=null){
            oldClass.setRoomId(null);
            oldClass.setStatus(0);
            classDao.update(oldClass);
        }
        if (classVo.getRoomType()==2){
            classRoom.setStatus(1);
            classRoomDao.update(classRoom);
            return ResponseEntity.isOk("保存成功,办公室无法绑定班级!");
        }
        //对现在的处理
        if (classVo.getClassId()!=null){
            Classes newClass = classDao.getClass(classVo.getClassId());
            newClass.setRoomId(classVo.getRoomId());
            newClass.setStatus(1);
            classDao.update(newClass);
            classRoom.setStatus(1);
        }

        classRoomDao.update(classRoom);
        return ResponseEntity.isOk("保存成功!");
    }


    /**
     * 添加设备与教室
     * @param classVo
     * @return
     */
    @Transactional(readOnly = false)
    public ResponseEntity saveClassRoom(ClassVo classVo){
        ClassRoom classRoom = new ClassRoom();
        classRoom.setRoomCode(classVo.getRoomCode());
        classRoom.setBuildingId(classVo.getBuildingId());
        classRoom.setRoomType(classVo.getRoomType());
        int roomType = classVo.getRoomType();
        if (roomType==2){
            classRoom.setStatus(1);
            classRoomDao.update(classRoom);
            return ResponseEntity.isOk("保存成功,办公室无法绑定班级!");
        }
        if(classVo.getClassId()==null) {
            classRoom.setStatus(0);
            classRoomDao.save(classRoom);
            return ResponseEntity.isOk("保存成功,但是没有绑定班级!");
        }else {
            classRoom.setStatus(1);
            classRoomDao.save(classRoom);
            Classes classes = classDao.getClass(classVo.getClassId());
            classes.setRoomId(classRoom.getId());
            classes.setStatus(1);
            classDao.update(classes);
            return ResponseEntity.isOk("保存成功!");
        }
    }

    /**
     * 根据id删除设备与教室
     * @param roomId
     */
    @Transactional(readOnly = false)
    public void deleteRoomById(Long roomId){
        classRoomDao.deleteClassRoomById(roomId);
    }

    @Override
    public List<Classes> getGradeClassAndSchool(String schoolCode, Long gradeId) {
        return classDao.getGradeClassAndSchool(schoolCode,gradeId);
    }


    public JSONObject loadEquipmentsByDevelopType(Long developType, String schoolCode){
        DevelopType develop = developTypeDao.getDevelop(developType);
        List<DevelopType> develops = developTypeDao.getDevelops(develop.getId());
        List<Accessory> accessories = accessoryDao.findAccessoryBySchoolAndDevelopType(schoolCode);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("develops",develops);
        jsonObject.put("accessories",accessories);
        return jsonObject;
    }

    @Override
    public JSONObject loadBuildingAndClassRoomByClass(Long classId) {
        Classes aClass = classDao.getClass(classId);
        JSONObject jsonObject=new JSONObject();
        if (aClass==null){
            jsonObject.put("message","请选择正确的班级!");
            jsonObject.put("success","0");
            return jsonObject;
        }
        Long roomId = aClass.getRoomId();

        if (roomId==null){
            jsonObject.put("message","该班级没有绑定教室!请先绑定教室");
            jsonObject.put("success","0");
            return jsonObject;
        }
        ClassRoom classRoom = classRoomDao.getClassRoom(roomId);
        jsonObject.put("roomName",classRoom.getRoomCode());
        jsonObject.put("roomId",classRoom.getId());
        TeachBuilding building = buildingDao.findBuilding(classRoom.getBuildingId());
        jsonObject.put("buildingId",building.getId());
        jsonObject.put("buildingName",building.getBuildingName());
        return jsonObject;
    }

}
