package com.hfkj.bbt.systemanage.web;



import com.hfkj.bbt.base.entity.*;
import com.hfkj.bbt.base.util.ComUtil;
import com.hfkj.bbt.base.util.UserUtil;
import com.hfkj.bbt.systemanage.service.*;


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
 * Created by Administrator on 2017-10-31.
 */
@Controller
@RequestMapping(value = "class")
public class ClassController {

    @Autowired
    private IClassService classService;

    @Autowired
    private ISchoolService schoolService;

    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private IClassroomService classroomService;

    @Autowired
    private IOtherService otherService;

    /**
     * 查询班级信息
     * @param searchVo
     * @return
     */
    @RequestMapping(value = "selectClassList",method = RequestMethod.POST)
    public ModelAndView selectClassList(SearchVo searchVo){
        searchVo.check();

        ModelAndView modelAndView=new ModelAndView("systemManage/classList");

        modelAndView.addObject("page",classService.selectClass(searchVo));

        return modelAndView;
    }

    /**
     * 新增班级弹框页面回填数据
     * @return
     */
    @RequestMapping(value = "loadClassById",method = RequestMethod.POST)
    public ModelAndView loadClassById(Long classId){

        ModelAndView modelAndView=new ModelAndView("systemManage/addOrModifyClasses");

        User currentUser = UserUtil.getCurrentUser();
        String schoolCode = currentUser.getSchoolCode();
        //只显示管理员自身的学校
        modelAndView.addObject("school",schoolService.getSchool(currentUser.getSchoolCode()));
        modelAndView.addObject("allAchool",schoolService.getSchool());
        List<TeachBuilding> teachBuildings = buildingService.getBuildingBySchoolCode(schoolCode);

        modelAndView.addObject("buildingList",teachBuildings);

        //加载年级
        modelAndView.addObject("gradeList",classService.getGradeList());

        if (classId!=null){
            Classes classById = classService.getClassById(classId);
            ClassRoom classRoom = classroomService.getClassRoom(classById.getRoomId());
            if (classRoom!=null){
                TeachBuilding buildingById = buildingService.getBuildingById(classRoom.getBuildingId());
                modelAndView.addObject("classroom",classRoom);
                modelAndView.addObject("building",buildingById);
                modelAndView.addObject("classroomList",classroomService.getClassRoomByBuilding(buildingById.getId()));
            }
            modelAndView.addObject("classes",classById);
            modelAndView.addObject("gradeId",classById.getGradeId());
        }


        return modelAndView;
    }


    @RequestMapping(value = "loadEquipmentByTypeAndSchool")
    @ResponseBody
    public List<Equipment>  loadEquipmentByTypeAndSchool(String schoolCode,Integer equipmentType){
        return otherService.getEquipmentByNoAndType(schoolCode,equipmentType);
    }






    /**
     * 增加班级信息
     * @param classVo
     * @return
     */
    @RequestMapping(value = "doSaveClass",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addClassList(ClassVo classVo) {
//        if (!ComUtil.haveAuthority(classVo.getSchoolCode())) {
//            return ResponseEntity.isFail("没有权限!");
//        }
        if (!classVo.check()){
            return ResponseEntity.isFail("请完善数据!");
        }
        String doType = classVo.getDoType();
        if ("add".equalsIgnoreCase(doType)){
            int r = classService.doAddClasses(classVo);
            if (r == 1) {
                return ResponseEntity.isOk("新增成功!");
            } else if(r==0){
                return ResponseEntity.isFail("新增失败!班级重复!");
            }
        }else if ("modify".equalsIgnoreCase(doType)){
            int i = classService.updateClassList(classVo);
            if (i == 1) {
                return ResponseEntity.isOk("修改成功!");
            } else if(i==0){
                return ResponseEntity.isFail("修改失败!数据重复!");
            }
        }
        return ResponseEntity.isOk("操作失败!");

    }


    /**
     * 删除班级信息
     * @param classId
     * @return
     */
    @RequestMapping(value = "doDeleteClasses",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity doDeleteClasses(Long classId){
        Classes classById = classService.getClassById(classId);
        if (classById!=null){
//            if (ComUtil.haveAuthority(classById.getSchoolCode())) {
                int r =classService.deleteClasses(classById);
                if(r==1){
                    return ResponseEntity.isOk("删除成功!");
                }else {
                    return ResponseEntity.isFail("删除失败!请先解除绑定!");
                }
//            } else {
//                return ResponseEntity.isFail("权限不够!");
//            }
        }else {
            return ResponseEntity.isFail("班级不存在!");
        }

    }




}



















