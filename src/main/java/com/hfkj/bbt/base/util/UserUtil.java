package com.hfkj.bbt.base.util;

import com.hfkj.bbt.base.entity.ExtendUser;
import com.hfkj.bbt.base.entity.Role;
import com.hfkj.bbt.base.entity.User;
import com.hfkj.bbt.systemanage.web.vo.UserVo;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2017-06-26.
 */
public class UserUtil {


    /**
     * 获取当前用户
     *
     * @return
     */
    public static User getCurrentUser() {
        return ((ExtendUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }

    /**
     * 获取当前用户的角色
     * @return
     */
    public static List<Role> getCurrentUserRoles() {
        return ((ExtendUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRoleList();
    }


    public static Role getCurrentMostHeighRole(){
        List<Role> roleList = ((ExtendUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRoleList();
        sortAthu(roleList);
        return roleList.get(0);
    }

    private static void sortAthu(List<Role> roles) {
        //排序
        Comparator<Role> comparator = new Comparator<Role>() {
            @Override
            public int compare(Role o1, Role o2) {
                return o2.getRoleLevel()-o1.getRoleLevel();
            }
        };


        if(null!=roles){
            Collections.sort(roles,comparator);
        }
    }

    public static UserVo UserToVo (User user ){
        UserVo userIn =new UserVo();
        userIn.setUserId(user.getId());
        userIn.setRealName(user.getRealName());
        userIn.setSex(user.getSex());
        userIn.setBirthday(DateUtil.tranDateToString("yyyy-MM-dd HH:mm:ss",user.getBirthday()));
        userIn.setSchoolCode(user.getSchoolCode());
        userIn.setSubjectId(user.getSubjectId());
        userIn.setEducational(user.getEducational());
        userIn.setProfessional(user.getProfessional());
        userIn.setIcNo(user.getIcardNo());
        userIn.setUserName(user.getUserName());
        return userIn;
    }

}
