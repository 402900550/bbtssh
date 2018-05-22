package com.hfkj.bbt.systemanage.service.impl;

import com.hfkj.bbt.base.dao.*;
import com.hfkj.bbt.base.entity.*;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.base.util.ComUtil;
import com.hfkj.bbt.base.util.DateUtil;
import com.hfkj.bbt.systemanage.service.IUserService;
import com.hfkj.bbt.systemanage.web.vo.SearchVo;
import com.hfkj.bbt.systemanage.web.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017-10-26.
 */
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {



    @Autowired
    private UserDao userDao;

    @Autowired
    private SystemanageDao systemanageDao;

    @Autowired
    private RoleToUserDao roleToUserDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private SchoolDao schoolDao;

    @Override
    public Page selectUserList(SearchVo searchVo) {
        return systemanageDao.selectUserList(searchVo);
    }


    @Transactional(readOnly = false)
    public ResponseEntity doUpdateUser(UserVo userVo){
        User user = userDao.getUserById(userVo.getUserId());
        if (user==null){
            return ResponseEntity.isFail("该用户不存在!");
        }

        user.setRealName(userVo.getRealName());
        user.setUpdateDate(new Timestamp(new Date().getTime()));
        user.setSex(userVo.getSex());
        user.setIcardNo(userVo.getIcNo());
        user.setProfessional(userVo.getProfessional());
        user.setEducational(userVo.getEducational());
        user.setSchoolCode(userVo.getSchoolCode());
        user.setSubjectId(userVo.getSubjectId());
        user.setBirthday(DateUtil.tranStringToDate("yyyy-MM-dd",userVo.getBirthday()));
        userDao.update(user);
        return ResponseEntity.isOk("保存成功!");
    }

    @Override
    public User getMostNewUser(Long userId) {
        return userDao.getUserById(userId);
    }

    public RoleToUser getRoleUserById(Long userId){return roleToUserDao.getRoleToUserById(userId);}


    public Role getRoleById(Long userId){
        RoleToUser roleToUserById = roleToUserDao.getRoleToUserById(userId);
        if (roleToUserById!=null){
            return roleDao.getRoleByRoleId(roleToUserById.getRoleId());
        }
        return null;
    }

    @Override
    @Transactional(readOnly = false)
    public int modifyUserPwd(Long userId, String oldPwd, String newPwd) {
        User user = userDao.getUserById(userId);
        if (user==null){
            return 2;
        }
        if (ComUtil.stringIsNotNull(oldPwd,newPwd)){
            String password = user.getPassword();
            if (password.equals(oldPwd)){
                user.setPassword(newPwd);
                userDao.update(user);
                return 1;
            }else {
                return 3;
            }
        }

        return 0;
    }

    @Override
    public List getSchoolNameAndRoleName(Long userId) {
        User user = userDao.getUserById(userId);
        List list=new ArrayList();
        String schoolCode = user.getSchoolCode();
        if (ComUtil.stringIsNotNull(schoolCode)){
            School school = schoolDao.getSchool(schoolCode);
            if (school!=null){
                list.add(school.getName());
            }else {
                list.add("该用户没有学校!");
            }
        }else {
            list.add("该用户没有学校!");
        }

        RoleToUser roleToUserById = roleToUserDao.getRoleToUserById(userId);
        if (roleToUserById!=null){
            Long roleId = roleToUserById.getRoleId();
            if (roleId!=null){
                Role roleByRoleId = roleDao.getRoleByRoleId(roleId);
                if (roleByRoleId!=null){
                    list.add(roleByRoleId.getDescription());
                }else {
                    list.add("教师");
                }
            }else {
                list.add("教师");
            }
        }else {
            list.add("教师");
        }

        return list;
    }

    /**
     * 手机端页面登陆验证
     * @param userName
     * @return
     */
    @Override
    public User loadUserByUserName(String userName) {
        User userByUserName = userDao.getUserByUserName(userName);

        return userByUserName;
    }


    @Transactional(readOnly = false)
    public ResponseEntity updateUserList(UserVo userVo) {
        User user = userDao.getUserById(userVo.getUserId());
        RoleToUser roleToUser =  roleToUserDao.getRoleToUserById(userVo.getUserId());
        if (user==null){
            return ResponseEntity.isFail("该用户不存在!");
        }
        user.setRealName(userVo.getRealName());
        user.setUpdateDate(new Timestamp(new Date().getTime()));
        user.setSex(userVo.getSex());
        user.setProfessional(userVo.getProfessional());
        user.setEducational(userVo.getEducational());
        user.setSchoolCode(userVo.getSchoolCode());
        user.setSubjectId(userVo.getSubjectId());
        user.setBirthday(DateUtil.tranStringToDate("yyyy-MM-dd",userVo.getBirthday()));
        user.setIcardNo(userVo.getIcNo());
        user.setUpdateDate(new Timestamp(new Date().getTime()));
        userDao.update(user);
        roleToUser.setRoleId(userVo.getUserRoleId());
        roleToUserDao.update(roleToUser);
        return ResponseEntity.isOk("修改成功!");
    }

    @Transactional(readOnly = false)
    public void deleteUserById(Long userId){
        userDao.deleteUserById(userId);
    }

    @Override
    @Transactional(readOnly = false)
    public ResponseEntity saveUserList(UserVo userVo) {
        User user = new User();
        user.setUserName(userVo.getUserName());
        user.setSchoolCode(userVo.getSchoolCode());
        user.setRealName(userVo.getRealName());
        user.setSex(userVo.getSex());
        user.setBirthday(DateUtil.tranStringToDate("yyyy-MM-dd", userVo.getBirthday()));
        user.setEducational(userVo.getEducational());
        user.setPassword("123456");
        user.setProfessional(userVo.getProfessional());
        user.setSubjectId(userVo.getSubjectId());
        user.setIcardNo(userVo.getIcNo());
        user.setStatus(1);
        user.setCreateDate(new Timestamp(new Date().getTime()));
        userDao.save(user);
        RoleToUser roleToUser = new RoleToUser();
        roleToUser.setRoleId(userVo.getUserRoleId());
        roleToUser.setUserId(user.getId());
        roleToUserDao.save(roleToUser);
        return ResponseEntity.isOk("添加成功!");
    }



    /**
     * 精确查询user
     * @param openid
     * @return
     */
    public User findUserByOpenid(String openid){
        return userDao.getUserByOpenId(openid);
    }

    /**
     * 保存openid
     * @param userName
     * @param openid
     */
    @Override
    @Transactional(readOnly = false)
    public int saveOpenId(String userName,String openid){
        try{
            User user = userDao.getUserByUserName(userName);
            user.setOpenid(openid);
            userDao.update(user);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return  0;
        }

    }
}







