package com.huangdw.senior.interceptor;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.huangdw.dto.CommonResult;
import com.huangdw.enums.RespEnum;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.util.concurrent.ExecutionException;

/**
 * @project: web-app
 * @description: 登录拦截器
 * @author: huangdw
 * @create: 2018-07-03 10:23
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);

    /**
     * 全局是否需要 HTTPS 登录
     */
    private boolean isAllNeedHttps = false;

    /**
     * 全局是否需要登陆
     */
    private boolean isAllNeedLogin = false;

    /**
     * 跳转登陆 URL
     */
    private String loginUrl = "https://xxx.com/passport/login.jsp";

    private ResultTypeEnum defaultResultType = ResultTypeEnum.FORWARD;

    private Class<? extends LoginValidator> defaultClass = UcenterDefaultLoginValidatorImpl.class;

    // 登录校验处理类缓存: 防止反复创建大量对象
    private LoadingCache<Class<? extends LoginValidator>, LoginValidator> validatorCache = CacheBuilder.newBuilder()
            .build(new CacheLoader<Class<? extends LoginValidator>, LoginValidator>() {
                @Override
                public LoginValidator load(Class<? extends LoginValidator> validatorClass) throws Exception {
                    Constructor<?>[] constructors = validatorClass.getConstructors();
                    LoginValidator loginValidator = (LoginValidator) constructors[0].newInstance();

                    return loginValidator;
                }
            });

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler; // 指向处理器中的一个方法

        // 获取注解在对象上的注解中的 LoginControl
        LoginControl beanLoginControl = handlerMethod.getBeanType().getAnnotation(LoginControl.class);
        // 获取注解在方法上的注解中的 LoginControl
        LoginControl methodLoginControl = handlerMethod.getMethod().getAnnotation(LoginControl.class);
        // 方法上的注解覆盖类上的(方法注解优先)
        LoginControl loginControl = methodLoginControl != null ? methodLoginControl : beanLoginControl;
        // 如果方法和类上都没有声明 LoginControl 注解, 采用全局配置(isAllNeedLogin), 如果未配置则默认不需要登录
        if (loginControl == null) {
            // 如果全局配置 isAllNeedLogin 为 true, 则需要校验是否登录
            if (isAllNeedLogin) {
                // 校验是否登陆
                if (validate(request, defaultClass)) {
                    // 校验通过: 放行
                    return true;
                }
            } else {
                // 未声明注解, 并且全局配置为不需要登录: 放行
                return true;
            }
        } else {
            // 如果不需要校验登录
            if (loginControl.required() == RequiredEnum.NO) {
                return true;
            } else {
                // 否则校验是否登录
                if (validate(request, loginControl.validatorClass())) {
                    // 校验通过: 放行
                    return true;
                }
            }
        }

        // 以下是未通过登录校验的处理
        ResultTypeEnum resultType = loginControl != null ? loginControl.resultType() : defaultResultType;
        if (ResultTypeEnum.JSON == resultType) { // 返回 JSON 串
            CommonResult result = CommonResult.fail(RespEnum.USER_NOT_LOGIN);
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(JSON.toJSONString(result));
            out.flush();
            out.close();
            return false;
        } else { // 重定向到登录页
            // 从 request 中获取请求 URL, 再得到 returnUrl
            String returnUrl = "";
            if (loginControl == null ? isAllNeedHttps : loginControl.needHttps() == RequiredEnum.YES) {
                // 转换 returnUrl 中的协议为 HTTPS
            }
            if (StringUtils.isBlank(returnUrl)) {
                response.sendRedirect(loginUrl);
            } else {
                response.sendRedirect(loginUrl + "?ret=" + returnUrl); // 去 loginUrl 页面登录成功后, 再回 returnUrl 页面
            }
            return false;
        }
    }

    /**
     * 登录验证
     *
     * @param request
     * @param validatorClass
     * @return
     */
    private boolean validate(HttpServletRequest request, Class<? extends LoginValidator> validatorClass) throws ExecutionException {
        // 从缓存中获取一个处理类
        LoginValidator loginValidator = validatorCache.get(validatorClass);
        if (loginValidator == null) {
            LOGGER.error("配置的 validatorClass 为空 {}", validatorClass.getSimpleName());
            throw new IllegalArgumentException("validatorClass is null");
        }
        // 登录验证
        return loginValidator.validate(request);
    }

    public void setDefaultClass(Class<? extends LoginValidator> defaultClass) {
        this.defaultClass = defaultClass;
    }

    public void setAllNeedHttps(boolean allNeedHttps) {
        isAllNeedHttps = allNeedHttps;
    }

    public void setAllNeedLogin(boolean allNeedLogin) {
        isAllNeedLogin = allNeedLogin;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public void setDefaultResultType(ResultTypeEnum defaultResultType) {
        this.defaultResultType = defaultResultType;
    }
}
