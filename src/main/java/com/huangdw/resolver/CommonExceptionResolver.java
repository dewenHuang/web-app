package com.huangdw.resolver;

import com.alibaba.fastjson.JSON;
import com.huangdw.dto.CommonResult;
import com.huangdw.enums.RespEnum;
import com.huangdw.exception.CustomException;
import com.huangdw.util.IpUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * @program: my-controller-app
 * @description: 通用异常解析器
 * @author: huangdw
 * @create: 2018-04-13
 */
@Component
public class CommonExceptionResolver implements HandlerExceptionResolver, Ordered {// 可以拦截Handler映射、参数绑定以及目标方法执行时发生的异常，推荐使用

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        // 客户端 IP
        String clientIp = IpUtil.getClientIp(request);
        // 请求 URI
        String requestUri = request.getRequestURI();
        // 请求参数
        Map<String, Object> params = WebUtils.getParametersStartingWith(request, null);

        // 判断是否Ajax请求（如果是Ajax请求，则请求类型为XMLHttpRequest）
        if (!(request.getHeader("Accept").contains("application/json") ||
                (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").contains("XMLHttpRequest")))) {
            // 传统同步请求，返回JSP
            return new ModelAndView("error/500", "errMsg", "报错啦。。。"); // 针对前后端没有分离的情况，此处可以根据异常类型返回不同的页面（比如：404.ftl、500.ftl等）
        } else {
            // Ajax异步请求，返回Json串
            CommonResult result;
            // 为安全起见，只有业务异常我们对前端可见，否则统一归为系统异常
            if (e instanceof CustomException) {// 2.业务异常
                CustomException customException = (CustomException) e;
                LOGGER.error("An handler custom exception occurred, ip: {}, uri: {}, params: {}", clientIp, requestUri, JSON.toJSONString(params), e);

                result = CommonResult.fail(customException.getError());
            } else if (e instanceof BindException) {// 1.客户端错误需要细化（比如：请求方法找不到-404、请求方式不允许-405和请求语法不正确-400等）
                BindException bindException = (BindException) e;
                // 字段绑定错误集合
                List<FieldError> fieldErrors = bindException.getBindingResult().getFieldErrors();

                StringBuilder sb = new StringBuilder();
                if (CollectionUtils.isNotEmpty(fieldErrors)) {
                    for (FieldError fieldError : fieldErrors) {
                        sb.append(fieldError.getDefaultMessage());
                        sb.append("\n");
                    }
                }
                String errMsg = sb.toString();

                result = CommonResult.fail(RespEnum.REQUEST_BAD, errMsg.substring(0, errMsg.lastIndexOf("\n")));
            } else {// 3.服务器错误
                LOGGER.error("An handler unknown exception occurred, ip: {}, uri: {}, params: {}", clientIp, requestUri, JSON.toJSONString(params), e);
                result = CommonResult.fail(RespEnum.SERVER_FAIL);
            }

            try {
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(result));
                writer.flush();
                writer.close();
            } catch (IOException e1) {
                LOGGER.error("与客户端通讯异常！", e1);
            }

            return null; // 此处没有返回ModelAndView，还会按照顺序执行下一个解析器
        }
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE; // 越小越先执行
    }
}
