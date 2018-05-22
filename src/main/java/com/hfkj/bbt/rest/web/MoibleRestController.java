package com.hfkj.bbt.rest.web;


import com.hfkj.bbt.application.service.IApplicationService;
import com.hfkj.bbt.base.entity.Role;
import com.hfkj.bbt.base.entity.RoleToUser;
import com.hfkj.bbt.base.entity.User;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.base.util.ComUtil;
import com.hfkj.bbt.base.util.UserUtil;
import com.hfkj.bbt.operationsManagement.service.IOperationsService;
import com.hfkj.bbt.operationsManagement.web.vo.OperationVo;
import com.hfkj.bbt.rest.web.vo.DriveByWireVo;
import com.hfkj.bbt.systemanage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "mobileRest")
public class MoibleRestController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IApplicationService applicationService;

    @Autowired
    private IOperationsService operationsService;



    /**
     * 手机页面登陆接口
     * @param userName
     * @return
     */
    @RequestMapping(value = "restLogin",method = RequestMethod.POST)
    @ResponseBody
    public User restLogin(String userName){
        return userService.loadUserByUserName(userName);
    }



    /**
     * 查询单个学校的数据
     */
    @RequestMapping(value = "findSingleApplication",method = RequestMethod.POST)
    @ResponseBody
    public List findSingleApplication(String schoolCode,String className){
        if (ComUtil.stringIsNotNull(className)){
            className="%"+className+"%";
        }else {
            className="%%";
        }
        return applicationService.findSingleApplication(schoolCode,className);
    }
    /**
     * 查询单个学校的应用监测概览数据
     */
    @RequestMapping(value = "findSingleGeneralView",method = RequestMethod.POST)
    @ResponseBody
    public List findSingleGeneralView(String schoolCode){
        return applicationService.findSingleGeneralView(schoolCode);
    }

    /**
     * 手机接口 查询使用率概况
     * @param schoolCode
     * @return
     */
    @RequestMapping(value = "selectMobileDistinctSchoolList",method = RequestMethod.POST)
    @ResponseBody
    public List selectMobileDistinctSchoolList(String schoolCode){
        return applicationService.selectMobileDistinctSchoolList(schoolCode);
    }

    /**
     * 环境监测 手机接口
     */
    @RequestMapping(value = "loadMobileEnvironment")
    @ResponseBody
    public List loadMobileEnvironment(String schoolCode){
        return applicationService.loadEnvironmentIndex(schoolCode);
    }


    /**
     * 待处理运维单个数查询
     */
    @RequestMapping(value = "getOperationsCount")
    @ResponseBody
    public String getOperationsCount(String schoolCode,Long userId){
        Role role = userService.getRoleById(userId);
        String roleName="TEACHER";
        if (role!=null){
            roleName=role.getRoleName();
        }
        List<OperationVo> operationVos = operationsService.selectOperationsPending(schoolCode, roleName, userId);
        if (ComUtil.listNotNullAndEmpty(operationVos)){
            return String.valueOf(operationVos.size());
        }else {
            return "0";
        }
    }


    /**
     *待处理运维单列表查询 手机接口
     */
    @RequestMapping(value = "getOperationsDaichuliList",method = RequestMethod.POST)
    @ResponseBody
    public List<OperationVo> getOperationsDaichuliList(String schoolCode,Long userId){
        Role role = userService.getRoleById(userId);
        String roleName="TEACHER";
        if (role!=null){
            roleName=role.getRoleName();
        }
        return operationsService.selectOperationsPending(schoolCode, roleName, userId);
    }
    /**
     *已完成运维单列表查询 手机接口
     */
    @RequestMapping(value = "getCompleteOperations",method = RequestMethod.POST)
    @ResponseBody
    public List getCompleteOperations(String schoolCode,Integer toPage, Integer pageSize){
        return operationsService.selectOperationsMyList(toPage,pageSize,1,schoolCode).getData();
    }



    /**
     * 分页查询单个学校的数据
     */
    @RequestMapping(value = "findPageSingleApplication",method = RequestMethod.POST)
    @ResponseBody
    public List findPageSingleApplication(String schoolCode, String className,int startNumber,int sizeNumber){
        if (ComUtil.stringIsNotNull(className)){
            className="%"+className+"%";
        }else {
            className="%%";
        }
        List list = applicationService.findPageSingleApplication(schoolCode,className,startNumber,sizeNumber).getData();
        return list;
    }



    /**
     * 修改密码 手机页面
     */
    @RequestMapping(value = "modifyPassword",method = RequestMethod.POST)
    @ResponseBody
    public String modifyPassword(Long userId,String oldPwd,String newPwd){
        return String.valueOf(userService.modifyUserPwd(userId,oldPwd,newPwd));
    }

    /**
     * 修改密码 手机页面
     */
    @RequestMapping(value = "testestest")
    @ResponseBody
    public String tetetet(){
        return "0";
    }

    /**
     * 根据用户ID获取学校名称和角色名称
     */
    @RequestMapping(value = "getSchoolAndRoleName",method = RequestMethod.POST)
    @ResponseBody
    public List getSchoolNameAndRoleName(Long userId){
        return userService.getSchoolNameAndRoleName(userId);
    }

    /**
     * 手机电控
     */
    @RequestMapping(value = "driveByWire",method = RequestMethod.POST)
    @ResponseBody
    public String driveByWire(DriveByWireVo vo){

        Role role = userService.getRoleById(vo.getUserId());
        if (role==null){
            return "4";
        }
        if (!"SCHOOLADMIN".equals(role.getRoleName())){
            return "4";
        }

        return String.valueOf(applicationService.driveByWire(vo));
    }



    /**
     * 根据openid获取用户
     */
    @RequestMapping(value = "findUserByOpenId",method = RequestMethod.POST)
    @ResponseBody
    public User findUserByOpenId(String openId){
        return userService.findUserByOpenid(openId);
    }

}
















