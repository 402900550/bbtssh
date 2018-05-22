package com.hfkj.bbt.systemanage.service;

import com.hfkj.bbt.base.entity.ResponseEntity;
import com.hfkj.bbt.base.entity.Role;
import com.hfkj.bbt.base.entity.RoleToUser;
import com.hfkj.bbt.base.entity.User;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.systemanage.web.vo.SearchVo;
import com.hfkj.bbt.systemanage.web.vo.UserVo;

import java.util.List;

/**
 * Created by Administrator on 2017-10-26.
 */
public interface IUserService {

    ResponseEntity doUpdateUser(UserVo userVo);

    User getMostNewUser(Long userId);

    /**
     * 根据条件查询用户详情
     */
    Page selectUserList(SearchVo searchVo);

    /**
     * 修改用户
     * @param userVo
     * @return
     */
    ResponseEntity updateUserList(UserVo userVo);

    /**
     * 根据id删除用户
     * @param userId
     */
    void deleteUserById(Long userId);


    /**
     * 添加用户
     * @param userVo
     * @return
     */
    ResponseEntity saveUserList(UserVo userVo);

    /**
     * 查询角色
     * @param userId
     * @return
     */
    RoleToUser getRoleUserById(Long userId);


    User loadUserByUserName(String userName);


    Role getRoleById(Long userId);

    int modifyUserPwd(Long userId,String oldPwd,String newPwd);

    List getSchoolNameAndRoleName(Long userId);

    User findUserByOpenid(String openid);

    //保存openid
    int saveOpenId(String userName,String openid);
}

