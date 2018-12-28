package com.huangdw.handler;

import com.huangdw.dto.CommonResult;
import com.huangdw.util.IpUtil;
import com.huangdw.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 统一异常处理器
 *
 * @author Devin
 * @date 2018/11/13
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public Object handleException(Exception ex, HttpServletRequest request) {
        // 客户端 IP
        String clientIp = IpUtil.getClientIp(request);
        // 请求 URI
        String requestUri = request.getRequestURI();
        // 请求参数
        Map<String, Object> params = WebUtils.getParametersStartingWith(request, null);
        LOGGER.error("An handler exception occurred, ip: {}, uri: {}, param: {}", clientIp, requestUri, params, ex);

        // 通用结果
        CommonResult result = RequestUtil.getResult(ex);

        if (RequestUtil.isAjaxRequest(request)) {
            // Ajax 异步请求
            return CommonResult.fail(result.getRespEnum());
        } else {
            // 传统同步请求
            return CommonResult.fail(result.getRespEnum());// Web前端返回 CommonResult，Admin后台返回 ModalAndView 跳转错误页面
        }
    }
}
