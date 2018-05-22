package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.base.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017-06-26.
 */
@Repository
public class RoleDao extends BaseDao<Role> {



    public Role getRoleByRoleId(Long id){
        String hql="FROM Role WHERE id=?";
        return findOne(hql,new Object[]{id});
    }

    public List<Role> getAllRoles(){
        String hql = "FROM Role";
        return find(hql);
    }
}
