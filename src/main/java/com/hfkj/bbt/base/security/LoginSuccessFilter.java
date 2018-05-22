package com.hfkj.bbt.base.security;

import com.hfkj.bbt.base.entity.User;
import com.hfkj.bbt.base.util.UserUtil;
import com.hfkj.bbt.base.web.IndexController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017-06-26.
 */
public class LoginSuccessFilter implements AuthenticationSuccessHandler {

    private static final Logger LOG = LoggerFactory.getLogger(LoginSuccessFilter.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        User currentUser = UserUtil.getCurrentUser();
        LOG.info("用户:"+currentUser.getUserName()+",登陆成功!");
        httpServletResponse.setContentType("text/html");
        httpServletResponse.setCharacterEncoding("UTF-8");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write("loginSuccess");
        writer.flush();
        writer.close();
    }
}
