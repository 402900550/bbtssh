package com.hfkj.bbt.base.security;

import com.hfkj.bbt.base.dao.RoleDao;
import com.hfkj.bbt.base.dao.RoleToUserDao;
import com.hfkj.bbt.base.dao.UserDao;
import com.hfkj.bbt.base.entity.ExtendUser;
import com.hfkj.bbt.base.entity.Role;
import com.hfkj.bbt.base.entity.RoleToUser;
import com.hfkj.bbt.base.entity.User;
import com.hfkj.bbt.base.util.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-06-26.
 */
public class MyUserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleToUserDao roleToUserDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.getUserByUserName(userName);
        if(null==user){
            throw new UsernameNotFoundException("notRegister");
        }
        if (user.getStatus()==-1){
            throw new UsernameNotFoundException("userStop");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        //获得该用户对应的所有角色
        List<RoleToUser> roleToUsers = roleToUserDao.getRoleToUsers(user.getId());
        List<Role> roles = new ArrayList<Role>();
        SimpleGrantedAuthority authority;
        if(ComUtil.listNotNullAndEmpty(roleToUsers)){
            for(RoleToUser roleToUser:roleToUsers){
                Role role = roleDao.getRoleByRoleId(roleToUser.getRoleId());
                roles.add(role);
                authority = new SimpleGrantedAuthority(role.getRoleName());
                authorities.add(authority);
            }
        } else {
            //若没有角色 默认添加最低权限角色
            Role role = roleDao.getRoleByRoleId(1L);
            roles.add(role);
            authority = new SimpleGrantedAuthority(role.getRoleName());
            authorities.add(authority);
        }
        //若没有角色 应该设置默认角色 待添加
        ExtendUser extendUser = new ExtendUser(user.getUserName(),user.getPassword(),authorities);
        extendUser.setUser(user);
        extendUser.setRoleList(roles);
        return extendUser;
    }





}
