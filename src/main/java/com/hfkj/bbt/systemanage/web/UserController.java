package com.hfkj.bbt.systemanage.web;

import com.hfkj.bbt.base.entity.ResponseEntity;
import com.hfkj.bbt.base.entity.User;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.base.service.ICommonService;
import com.hfkj.bbt.base.util.ComUtil;
import com.hfkj.bbt.base.util.UserUtil;
import com.hfkj.bbt.systemanage.service.IOtherService;
import com.hfkj.bbt.systemanage.service.ISchoolService;
import com.hfkj.bbt.systemanage.service.IUserService;
import com.hfkj.bbt.systemanage.web.vo.SearchVo;
import com.hfkj.bbt.systemanage.web.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2017-10-26.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {


    @Autowired
    private ISchoolService schoolService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IOtherService iOtherService;

    @Autowired
    private ICommonService commonService;

    @RequestMapping(value = "addOrUpdateUser")
    @ResponseBody
    public ResponseEntity addOrUpdateUser(UserVo userVo){
        if (userVo.check()){
            return userService.doUpdateUser(userVo);
        }else {
            return ResponseEntity.isFail("数据不能为空!");
        }
    }


    @RequestMapping(value = "loadUserInfo",method = RequestMethod.POST)
    public ModelAndView loadUserInfo(){
        ModelAndView mav=new ModelAndView("systemManage/currentUserInfo");
        mav.addObject("currentNewUser",userService.getMostNewUser(UserUtil.getCurrentUser().getId()));
        mav.addObject("currentSchool",schoolService.getSchool(UserUtil.getCurrentUser().getSchoolCode()));
        mav.addObject("subject",iOtherService.getSubjectName());
        mav.addObject("roleId",userService.getRoleUserById(UserUtil.getCurrentUser().getId()));
        mav.addObject("userRoleId",iOtherService.getAllRoles());
        return mav;
    }

    @RequestMapping(value = "selectUserList")
    private ModelAndView selectUserList(SearchVo searchVo){
        searchVo.check();
        Page page = userService.selectUserList(searchVo);
        ModelAndView mav=new ModelAndView("systemManage/userManageList");
        mav.addObject("user",page);
        return mav;
    }

    @RequestMapping(value = "loadUserById")
    private ModelAndView loadUserById(UserVo userVo){
        ModelAndView mav=new ModelAndView("systemManage/addUserManageList");
        userVo.check();
        User user = userService.getMostNewUser(userVo.getUserId());
        List list = iOtherService.getSubjectName();
        List schoolName = commonService.getAllSchool();
        List roleId = iOtherService.selectRoleId(userVo.getUserId());
        List userRoleId = iOtherService.getAllRoles();
        mav.addObject("userList",user);
        mav.addObject("subject",list);
        mav.addObject("schoolName",schoolName);
        mav.addObject("roleId",roleId);
        mav.addObject("userRoleId",userRoleId);
        return mav;
    }


    /**
     * 修改用户
     * @param userVo
     * @return
     */
    @RequestMapping(value = "updateUserList")
    @ResponseBody
    public ResponseEntity updateUserList(UserVo userVo){
//        if (ComUtil.haveAuthority(userVo.getSchoolCode())) {
            return userService.updateUserList(userVo);
//        }else {
//            return ResponseEntity.isFail("权限不够!");
//        }
    }

    /**
     * 删除用户
     * @param
     * @param operateSchoolCode
     * @return
     */
    @RequestMapping(value = "deleteUserById", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity deleteUser(Long userId, String operateSchoolCode) {
//        if (ComUtil.haveAuthority(operateSchoolCode)) {
            userService.deleteUserById(userId);
            return ResponseEntity.isOk("删除成功!");
//        } else {
//            return ResponseEntity.isFail("权限不够!");
//        }
    }

    @RequestMapping(value = "SchoolNameAndSubjectName")
    private ModelAndView SchoolNameAndSubjectName(){
        ModelAndView mav=new ModelAndView("systemManage/addUserList");
        List list = iOtherService.getSubjectName();
        List schoolName = commonService.getAllSchool();
        List userRoleId = iOtherService.getAllRoles();
        mav.addObject("userList",userService.getMostNewUser(UserUtil.getCurrentUser().getId()));
        mav.addObject("subject",list);
        mav.addObject("schoolName",schoolName);
        mav.addObject("userRoleId",userRoleId);
        return mav;
    }

    /**
     * 添加用户
     * @param userVo
     * @return
     */
    @RequestMapping(value = "addUserList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addUserList(UserVo userVo) {
        userService.saveUserList(userVo);

        return ResponseEntity.isOk("添加成功!");
    }


    /**
     * 手机
     * @return
     */
    @RequestMapping(value = "loadUserInfoMobile",method = RequestMethod.POST)
    @ResponseBody
    public Object loadUserInfoMobile(){
        List list = new ArrayList();
        list.add(userService.getMostNewUser(UserUtil.getCurrentUser().getId()));
        list.add(schoolService.getSchool(UserUtil.getCurrentUser().getSchoolCode()));
        list.add(iOtherService.getSubjectName());
        list.add(userService.getRoleUserById(UserUtil.getCurrentUser().getId()));
        list.add(iOtherService.getAllRoles());
        return list;
    }

}
