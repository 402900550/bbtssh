package com.hfkj.bbt.base.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2017-06-26.
 */
public class ExtendUser extends org.springframework.security.core.userdetails.User{

    private User user;

    private List<Role> roleList;

    public ExtendUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
