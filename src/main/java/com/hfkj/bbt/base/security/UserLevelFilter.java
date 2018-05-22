package com.hfkj.bbt.base.security;

import com.hfkj.bbt.base.dao.RoleDao;
import com.hfkj.bbt.base.entity.Role;
import com.hfkj.bbt.base.entity.User;
import com.hfkj.bbt.base.util.ComUtil;
import com.hfkj.bbt.base.util.UserUtil;
import com.hfkj.bbt.operationsManagement.activiti.ActivitiService;
import com.hfkj.bbt.operationsManagement.web.vo.OperationVo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2017-06-26.
 */
public class UserLevelFilter implements Filter {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private ActivitiService service;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        User currentUser = (User) request.getAttribute("currentUser");
        if (currentUser == null) {
            currentUser = UserUtil.getCurrentUser();
        }
        List<Role> currentUserRoles = UserUtil.getCurrentUserRoles();
        sortAthu(currentUserRoles);
        request.setAttribute("currentUser", currentUser);
        //默认权限最高角色
        if (ComUtil.listNotNullAndEmpty(currentUserRoles)) {
            request.setAttribute("currentRole", currentUserRoles.get(0));
        }
        loadAllLevel(request);
        List<OperationVo> processTask = service.getProcessTask(currentUser.getSchoolCode(), currentUserRoles.get(0).getRoleName()
                , currentUser.getId());
        if (null != processTask) {
            request.setAttribute("pendingCount", processTask.size());
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }


    private void loadAllLevel(HttpServletRequest request) {
        List<Role> allRoles = roleDao.getAllRoles();
        if (allRoles != null) {
            for (Role role : allRoles) {
                request.setAttribute(role.getRoleName(), role.getRoleLevel());
            }
        }
    }

    private void sortAthu(List<Role> roles) {
        //排序
        Comparator<Role> comparator = new Comparator<Role>() {
            @Override
            public int compare(Role o1, Role o2) {
                return o2.getRoleLevel() - o1.getRoleLevel();
            }
        };


        if (null != roles) {
            Collections.sort(roles, comparator);
        }
    }

    @Override
    public void destroy() {

    }
}
