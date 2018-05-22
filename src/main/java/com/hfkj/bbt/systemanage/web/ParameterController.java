package com.hfkj.bbt.systemanage.web;


import com.hfkj.bbt.base.entity.ResponseEntity;
import com.hfkj.bbt.base.util.ComUtil;
import com.hfkj.bbt.systemanage.service.IOtherService;
import com.hfkj.bbt.systemanage.service.IParameterService;
import com.hfkj.bbt.systemanage.web.vo.ParameterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2017-10-31.
 */
@Controller
@RequestMapping(value = "parameter")
public class ParameterController {

    @Autowired
    private IParameterService parameterService;

    @Autowired
    private IOtherService otherService;

    /**
     * 添加设备参数
     *
     * @param
     * @return
     */
    @RequestMapping(value = "doAddOrModifyParameter", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addParameter(ParameterVo vo,String doType) {
        String parameterValue = vo.getParameterValue();
        Long parameterTypeId = vo.getParameterTypeId();
        if (parameterTypeId==null){
            return ResponseEntity.isFail("参数类型不能为空!");
        }
        if (!ComUtil.stringIsNotNull(parameterValue)){
            return ResponseEntity.isFail("参数值不能为空!");
        }

        if ("modify".equalsIgnoreCase(doType)){
            parameterService.updateParameter(vo.getParameterId(),vo.getParameterValue(),vo.getParameterTypeId(),vo.getRemark());
            return ResponseEntity.isOk("修改成功!");
        }
        if ("add".equalsIgnoreCase(doType)){
            parameterService.addParameter(vo.getParameterValue(),vo.getParameterTypeId(),vo.getRemark());
            return ResponseEntity.isOk("新增成功!");
        }

        return ResponseEntity.isFail("添加失败!");


    }


//    /**
//     * 删除设备参数
//     */
//    @RequestMapping(value = "deleteParameter", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity deleteParameter(Long PId) {
//
//        parameterService.deleteParameter(PId);
//        return ResponseEntity.isOk("删除失败!");
//
//
//    }


    /**
     * 参数类型添加
     */
    @RequestMapping(value = "doAddParameterType", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity doAddParameterType(String typeName) {

        parameterService.addParameterType(typeName);
        return ResponseEntity.isOk("新增成功!");


    }



    @RequestMapping(value = "selectParameterList", method = RequestMethod.POST)
    public ModelAndView selectParameterList(ParameterVo vo){
        vo.check();
        ModelAndView modelAndView=new ModelAndView("systemManage/parameterList");
        modelAndView.addObject("page",parameterService.selectParameterList(vo));
        return modelAndView;
    }


    @RequestMapping(value = "openParameterWindow")
    public ModelAndView openParameterWindow(Long parameterId){
        ModelAndView modelAndView=new ModelAndView("systemManage/addOrModifyParameter");
        modelAndView.addObject("parameterTypes",otherService.getAllParameterType());

        if (parameterId!=null){
            modelAndView.addObject("currentParameter",otherService.getParametersById(parameterId));
        }

        return modelAndView;
    }


}



















