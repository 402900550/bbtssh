package com.hfkj.bbt.assetmanage.web;

import com.alibaba.fastjson.JSONObject;
import com.hfkj.bbt.assetmanage.service.IEquipmentService;
import com.hfkj.bbt.assetmanage.web.vo.AssetSearchVo;
import com.hfkj.bbt.assetmanage.web.vo.EquipmentVo;
import com.hfkj.bbt.base.entity.*;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.base.util.ComUtil;
import com.hfkj.bbt.systemanage.service.IClassService;
import com.hfkj.bbt.systemanage.service.IOtherService;
import com.hfkj.bbt.systemanage.service.ISchoolService;
import com.hfkj.bbt.systemanage.web.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/11/1 0001.
 */
@Controller
@RequestMapping(value = "equipment")
public class EquipmentController {

    @Autowired
    private IEquipmentService equipmentService;

    @Autowired
    private ISchoolService schoolService;

    @Autowired
    private IClassService classService;


    /**
     * 添加设备
     *
     * @return
     */
    @RequestMapping(value = "addControllerEquipment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addControllerEquipment(EquipmentVo vo, String accessoryData) {
        List<Accessory> types = JSONObject.parseArray(accessoryData, Accessory.class);
        vo.setAccessories(types);
        Long roomId = vo.getRoomId();
        Integer personCost = vo.getPersonCost();
        String manager = vo.getManager();
        Long typeLong = vo.getTypeLong();
        Integer datumCost=vo.getDatumCost();
        String inputDate = vo.getInputDate();
        if (!ComUtil.stringIsNotNull(inputDate)){
            return ResponseEntity.isFail("请选择采购日期!");
        }
        if (roomId==null){
            return ResponseEntity.isFail("请绑定教室!");
        }
        if (typeLong==null){
            return ResponseEntity.isFail("请选择配备类型!");
        }
        if ( personCost== null || datumCost==null||!ComUtil.stringIsNotNull(manager) ) {
            return ResponseEntity.isFail("请填写费用和负责人!");
        }


        return equipmentService.saveAccessoryType(vo);
    }



    @RequestMapping(value = "loadEquimentCount", method = RequestMethod.POST)
    @ResponseBody
    public Page loadEquimentCount() {
        return equipmentService.loadEquimentAndClassRoom();
    }

    /**
     * 查询系统设备
     *
     * @param assetSearchVo
     * @return
     */
    @RequestMapping(value = "loadEquimentAndClassRoom")
    public ModelAndView loadEquimentAndClassRoom(AssetSearchVo assetSearchVo) {
        assetSearchVo.check();
        ModelAndView mav = new ModelAndView("assetManage/schoolEqList");
        mav.addObject("page", equipmentService.selectEquimentClassRoom(assetSearchVo));
        return mav;
    }

    @RequestMapping(value = "lookSchoolDetailEquipment")
    public ModelAndView lookSchoolDetailEquipment(String schoolCode) {
        ModelAndView modelAndView = new ModelAndView("assetManage/equipmentSchool");
        modelAndView.addObject("school", schoolService.getSchool(schoolCode));
        return modelAndView;
    }


    /**
     * 查询系统设备
     *
     * @param
     * @return
     */
    @RequestMapping(value = "loadSchoolAc")
    public ModelAndView loadSchoolAc(SearchVo searchVo) {
        ModelAndView mav = new ModelAndView("assetManage/schoolAcList");
        searchVo.check();
        mav.addObject("page", equipmentService.loadSchoolAc(searchVo));
        return mav;
    }

    /**
     * 查询学校年级班级设备
     *
     * @param assetSearchVo
     * @return
     */
    @RequestMapping(value = "selectEquipmentSchoolList")
    public ModelAndView selectEquipmentSchoolList(AssetSearchVo assetSearchVo) {
        assetSearchVo.check();
        ModelAndView mav = new ModelAndView("assetManage/equipmentSchoolList");
        mav.addObject("page", equipmentService.loadClassRoomEquiment(assetSearchVo));
        return mav;
    }

    /**
     * 查询资产总额统计图
     *
     * @return
     */
    @RequestMapping(value = "loadEquimentSchool", method = RequestMethod.POST)
    @ResponseBody
    public Page loadEquimentSchool(AssetSearchVo assetSearchVo) {
        assetSearchVo.check();
        return equipmentService.loadClassRoomEquiment(assetSearchVo);
    }

    /**
     * 查询班级设备
     *
     * @param classId
     * @return
     */
    @RequestMapping(value = "lookSchoolDetailEquipmentSchoolClass")
    public ModelAndView lookSchoolDetailEquipmentSchoolClass(Long classId) {
        ModelAndView mav = new ModelAndView("assetManage/equipmentClass");
        mav.addObject("schoolClassRoom", equipmentService.selectSchoolClassAndClassRoom(classId));
        mav.addObject("classId", classId);
        return mav;
    }

    /**
     * 查询班级所有设备
     *
     * @param classId
     * @param startNumber
     * @param sizeNumber
     * @return
     */
    @RequestMapping(value = "selectEquipmentClassRoomList")
    public ModelAndView selectEquipmentClassRoomList(Long classId, int startNumber, int sizeNumber) {
        ModelAndView mav = new ModelAndView("assetManage/equipmentClassList");
        mav.addObject("page", equipmentService.selectEquipmentClassRoomList(classId, startNumber, sizeNumber));
        return mav;
    }

    /**
     * 查询单个设备详情
     *
     * @param equimentId
     * @return
     */
    @RequestMapping(value = "lookSchoolDetailEquipmentList")
    public ModelAndView lookSchoolDetailEquipmentList(Long equimentId) {
        ModelAndView mav = new ModelAndView("assetManage/equipmentDetails");
        Accessory accessory = equipmentService.findAccessoryClassList(equimentId);
        if (accessory.getRoomId() != null) {
            Classes classes = classService.getClassRoomById(accessory.getRoomId());
            mav.addObject("schoolClassRoom", equipmentService.selectSchoolClassAndClassRoom(classes.getId()));
            mav.addObject("classId", classes);
        }
        mav.addObject("accessory", accessory);
        mav.addObject("equipmentType", equipmentService.getDevelop(accessory.getEquipmentType()));
        mav.addObject("sizeType", equipmentService.getParametersById(accessory.getSizeType()));
        mav.addObject("brand", equipmentService.getParametersById(accessory.getBrand()));
        return mav;
    }




}




















