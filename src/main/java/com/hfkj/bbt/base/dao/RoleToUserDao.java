package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.base.entity.RoleToUser;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-06-26.
 */
@Repository
public class RoleToUserDao extends BaseDao<RoleToUser> {


    public List<RoleToUser> getRoleToUsers(Long userId){
        String hql="FROM RoleToUser WHERE userId=?";
        return find(hql,new Object[]{userId});
    }

    /**
     * 根据id查询用户权限
     * @param userId
     * @return
     */
    public RoleToUser getRoleToUserById(Long userId){
        String hql="FROM RoleToUser WHERE userId=?";
        return findOne(hql,new Object[]{userId});
    }

    /**
     * 根据学校代码查询学校管理员
     * @param schoolCode
     * @return
     */
    public List getSchoolManagerBySchool(String schoolCode){
        Map<String,Object> params=new HashMap<String,Object>();
        String sql=" SELECT GROUP_CONCAT(tu.id) " +
                "FROM tab_user tu   " +
                "LEFT JOIN tab_role_user tru on tru.user_id = tu.id  " +
                "where tu.school_code =:schoolCode and tru.role_id = 3  ";
        params.put("schoolCode",schoolCode);
        return findBySql(sql,params);
    }

    /**
     * 查询教委 区县管理员
     * @return
     */
    public String findEdutionUser(){
        Map<String,Object> params=new HashMap<String,Object>();
        String sql=" SELECT GROUP_CONCAT(tu.id) " +
                "FROM tab_user tu   " +
                "LEFT JOIN tab_role_user tru on tru.user_id = tu.id  " +
                "where  tru.role_id = 4  ";
        List bySql = findBySql(sql, params);
        return String.valueOf(bySql.get(0));

    }

}
