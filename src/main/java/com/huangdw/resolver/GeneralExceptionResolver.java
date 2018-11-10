package com.huangdw.resolver;

import com.alibaba.fastjson.JSON;
import com.huangdw.dto.CommonResult;
import com.huangdw.enums.XxxErrorEnum;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @program: my-controller-app
 * @description: 通用异常解析器
 * @author: huangdw
 * @create: 2018-04-13
 */
@Component
public class GeneralExceptionResolver implements HandlerExceptionResolver, Ordered { // 可以拦截Handler映射、参数绑定以及目标方法执行时发生的异常，推荐使用

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        // 客户端 IP
        String clientIp = IpUtil.getClientIp(request);
        // 请求 URI
        String requestUri = request.getRequestURI();

        // 判断是否Ajax请求（如果是Ajax请求，则请求类型为XMLHttpRequest）
        if (!(request.getHeader("Accept").contains("application/json") ||
                (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").contains("XMLHttpRequest")))) {
            // 传统同步请求，返回JSP
            return new ModelAndView("error/500", "errMsg", "报错啦。。。"); // 针对前后端没有分离的情况
        } else {
            // Ajax异步请求，返回Json串
            CommonResult result;
            // 为安全起见，只有业务异常我们对前端可见，否则统一归为系统异常
            if (e instanceof CustomException) {// 2.业务异常
                CustomException customException = (CustomException) e;
                LOGGER.error("An handler custom exception occurred, ip: {}, uri: {}, param: {}", clientIp, requestUri, customException.getParamDesc(), e);

                result = new CommonResult(customException.getError());
            } else if (e instanceof BindException) {// 1.客户端异常需要细化
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

                result = new CommonResult(XxxErrorEnum.PARAMETER_BIND_ERROR, errMsg.substring(0, errMsg.lastIndexOf("\n")));
            } else {// 3.服务器异常
                LOGGER.error("An handler unknown exception occurred, ip: {}, uri: {}", clientIp, requestUri, e);
                result = new CommonResult(XxxErrorEnum.SYSTEM_ERROR);
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