package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.base.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017-06-26.
 */
@Repository
public class UserDao extends BaseDao<User> {


    /**
     * 根据用户名查询
     * @param userName
     * @return
     */
    public User getUserByUserName(String userName){
        String hql="FROM User WHERE userName=?";
        return findOne(hql,new Object[]{userName});
    }

    /**
     * 根据ID查询
     * @param userId
     * @return
     */
    public User getUserById(Long userId){
        String hql="FROM User WHERE id=?";
        return findOne(hql,new Object[]{userId});
    }

    /**
     * 停用用户
     * @param userId
     * @return
     */
    public int setUserStatusStop(Long userId){
        String hql="UPDATE User SET status=-1 WHERE id=?";
        return executeUpdate(hql,new Object[]{userId});
    }

    /**
     * 启用用户
     * @param userId
     * @return
     */
    public int enableUser(Long userId){
        String hql="UPDATE User SET status=1 WHERE id=?";
        return executeUpdate(hql,new Object[]{userId});
    }


    /**
     * 根据IC卡查询用户
     * @param icardNo
     * @return
     */
    public User getUserByIcard(String icardNo){
        String hql ="FROM User WHERE icardNo=?";
        return findOne(hql,new Object[]{icardNo});
    }

    /**
     * 根据学校代码查询用户
     * @param schoolCode
     * @return
     */
    public List<User> getUserBySchoolCode(String schoolCode){
        String hql="FROM User WHERE schoolCode=? AND status!=-1  ORDER BY id";
        return find(hql,new Object[]{schoolCode});
    }


    /**
     * 根据ID数组查询用户
     * @param ids
     * @return
     */
    public List<User> getUsersByIds(Long[] ids){
        String hql="FROM User WHERE id IN :ids";
        return find(hql,"ids",ids);
    }

    /**
     * 删除用户
     * @param userId
     */
    public void deleteUserById(Long userId){
        String hql="DELETE User WHERE id=?";
        executeUpdate(hql,new Object[]{userId});
    }

    /**
     * 找学校管理员
     */
    public String findSchoolManagersBySchoolCode(String schoolCode){
        String hql="FROM User WHERE schoolCode=? AND status!=-1 AND id IN (SELECT id FROM RoleToUser Where roleId=3 )";
        List<User> users =find(hql,new Object[]{schoolCode});
        String userStr = "";
        for(User user :users){
            userStr+=user.getSchoolCode()+",";
        }
        return userStr;
    }

    /**
     * 根据openid查询
     * @param openid
     * @return
     */
    public User getUserByOpenId(String openid){
        String hql="FROM User WHERE openid=?";
        return findOne(hql,new Object[]{openid});
    }

}
