package com.hfkj.bbt.systemanage.web;

import com.alibaba.fastjson.JSONObject;
import com.hfkj.bbt.assetmanage.service.IEquipmentService;
import com.hfkj.bbt.base.entity.*;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.base.util.ComUtil;
import com.hfkj.bbt.base.util.UserUtil;
import com.hfkj.bbt.systemanage.service.IClassService;
import com.hfkj.bbt.systemanage.service.IClassroomService;
import com.hfkj.bbt.systemanage.service.IOtherService;
import com.hfkj.bbt.systemanage.web.vo.BundingEquipmentVo;
import com.hfkj.bbt.systemanage.web.vo.ClassVo;
import com.hfkj.bbt.systemanage.web.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

/**
 * Created by Administrator on 2017-10-30.
 */
@Controller
@RequestMapping(value = "classroom")
public class ClassroomController {

    @Autowired
    private IClassroomService classroomService;
    @Autowired
    private IClassService classService;
    @Autowired
    private IEquipmentService equipmentService;

    @Autowired
    private IOtherService otherService;
    @RequestMapping(value = "selectClassroomList",method = RequestMethod.POST)
    public ModelAndView selectClassroomList(SearchVo searchVo){
        searchVo.check();
        Page page = classroomService.selectClassroomList(searchVo);
        ModelAndView mav=new ModelAndView("systemManage/classroomList");
        mav.addObject("page",page);
        return mav;
    }



    @RequestMapping(value = "loadClassroomById",method = RequestMethod.POST)
    public ModelAndView loadClassroomById(Long roomId,Long buildingId){
        ModelAndView mav=new ModelAndView("systemManage/addOrModifyClassroom");

        ClassRoom classRoom = classroomService.getClassRoom(roomId);
        mav.addObject("currentRoom",classRoom);
        mav.addObject("classGradeId",classService.getClassRoomById(roomId));

        mav.addObject("roomS",classroomService.getClassAndRoom(buildingId));
        mav.addObject("gradeS",classroomService.selectGrade());
        mav.addObject("buildingId",buildingId);
        return mav;
    }


    @RequestMapping(value ="loadClassroomByBuilding")
    @ResponseBody
    public List<ClassRoom> loadClassroomByBuilding(Long buildingId){
        return classroomService.getClassRoomByBuilding(buildingId);
    }

    /**
     * 修改设备以及添加设备
     * @param classVo
     * @return
     */
    @RequestMapping(value = "updateClassRoom")
    @ResponseBody
    public ResponseEntity updateClassRoom(ClassVo classVo){

        if (!classVo.validate()){
            return ResponseEntity.isFail("请完善数据!");
        }

        if(classVo.getRoomId()==null) {
            return classroomService.saveClassRoom(classVo);
        }else{
            return classroomService.updateClassRoomList(classVo);
        }
    }

    /**
     * 删除教室与设备
     * @param
     * @param operateSchoolCode
     * @return
     */
    @RequestMapping(value = "deleteClassRoomById", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity deleteClassRoomById(Long roomId, String operateSchoolCode) {
            Classes classes = classService.getClassRoomById(roomId);
            List<Accessory> accessories= equipmentService.getClassRoomIdAccessriesById(roomId);
            if(classes!=null){
                return ResponseEntity.isFail("该教室绑定了班级，不能删除，请先解除绑定!");
            }else if(ComUtil.listNotNullAndEmpty(accessories)){
                return ResponseEntity.isFail("该教室绑定了设备，不能删除，请先解除绑定!");
            }else if(classes!=null && ComUtil.listNotNullAndEmpty(accessories)){
                return ResponseEntity.isFail("该教室绑定了设备和班级，不能删除，请先解除绑定!");
            }else {
                classroomService.deleteRoomById(roomId);
                return ResponseEntity.isOk("删除成功!");
            }

    }

    @RequestMapping(value = "selectGradeAndClassById",method = RequestMethod.POST)
    @ResponseBody
    public  List<Classes> selectGradeAndClassById(Long gradeId,String schoolCode){
        return classroomService.getGradeClassAndSchool(schoolCode, gradeId);
    }

    @RequestMapping(value = "selectClassRoomBuildingId",method = RequestMethod.POST)
    @ResponseBody
    public  List<ClassRoom> selectClassRoomBuildingId(Long buildingId){
        return classroomService.getClassAndRoom(buildingId);
    }


    /**
     * 根据班级加载教学楼和教室 设备录入使用
     * @param classId
     * @return
     */
    @RequestMapping(value = "loadBuildingAndClassRoomByClass",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject loadBuildingAndClassRoomByClass(Long classId){
        return classroomService.loadBuildingAndClassRoomByClass(classId);
    }



    @RequestMapping(value = "bundingAccessory")
    public ModelAndView bundingAccessory(Long roomId,String schoolCode){
        ModelAndView modelAndView=new ModelAndView("systemManage/bundingAccessory");
        //配备表
        modelAndView.addObject("develops",otherService.getDevelop());
        modelAndView.addObject("roomId",roomId);
        modelAndView.addObject("theSchoolCode",schoolCode);
        return modelAndView;
    }


    /**
     * 根据配备类型加载设备(绑定设备页面)
     * @param developType
     * @return
     */
    @RequestMapping(value = "loadEquipmentsByDevelopType")
    @ResponseBody
    public JSONObject loadEquipmentsByDevelopType(Long developType){
        User currentUser = UserUtil.getCurrentUser();
        String schoolCode = currentUser.getSchoolCode();
        if (ComUtil.stringIsNotNull(schoolCode)){
            return classroomService.loadEquipmentsByDevelopType(developType, schoolCode);
        }
        return null;
    }


    /**
     * 根据学校查询年级
     * @param schoolCode
     */
    @RequestMapping(value = "selectGradeBySchool",method = RequestMethod.POST)
    @ResponseBody
    public List<Grade> selectGradeBySchool(String schoolCode){
        return classService.getGradeList(schoolCode);
    }


    /**
     * 执行设备绑定
     * @return
     */
    @RequestMapping(value = "doBundingEquipments",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity doBundingEquipments(BundingEquipmentVo vo){
//        if (ComUtil.haveAuthority(vo.getSchoolCode())){
        equipmentService.doModifyBundingEquipments(vo);
//        }else {
//            return ResponseEntity.isOk("没有权限!");
//        }
        return ResponseEntity.isOk("绑定成功!");
    }


    @RequestMapping(value = "modifyAccessory")
    public ModelAndView modifyAccessory(Long roomId,String schoolCode){
        ModelAndView modelAndView=new ModelAndView("systemManage/modifyAccessory");
        //配备表
        modelAndView.addAllObjects(equipmentService.loadModifyAccessory(roomId));
        modelAndView.addObject("roomId",roomId);
        modelAndView.addObject("theSchoolCode",schoolCode);
        return modelAndView;
    }

    /**
     * 执行设备修改绑定
     * @return
     */
    @RequestMapping(value = "modifyBundingAccessory",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity modifyBundingAccessory(BundingEquipmentVo vo){
//        if (ComUtil.haveAuthority(vo.getSchoolCode())){
        equipmentService.doModifyBundingEquipments(vo);
//        }else {
//            return ResponseEntity.isOk("没有权限!");
//        }
        return ResponseEntity.isOk("修改成功!");
    }

}

















