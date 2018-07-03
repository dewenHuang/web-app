package com.huangdw.senior.interceptor;

import com.google.common.base.Preconditions;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @project: web-app
 * @description: 具体业务的登录校验器, 应该有多个
 * @author: huangdw
 * @create: 2018-07-02 20:18
 */
public class QsurveyLoginValidatorImpl implements LoginValidator {

    @Override
    public boolean validate(HttpServletRequest request) {
        Preconditions.checkNotNull(request);

        HttpSession session = request.getSession();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());

        Preconditions.checkNotNull(webApplicationContext);
        UserService userService = (UserService) webApplicationContext.getBean("userServiceImpl");

        Preconditions.checkNotNull(userService);
        UserDTO user = userService.getCurrUser(UserUtil.getIdFromSession(request));

        return (user != null);
    }
}
