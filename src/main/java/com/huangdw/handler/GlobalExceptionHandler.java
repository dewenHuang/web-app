package com.huangdw.handler;

import com.alibaba.fastjson.JSON;
import com.huangdw.dto.CommonResult;
import com.huangdw.util.IpUtil;
import com.huangdw.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理器
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
        // 服务端 IP
        String serverIp = IpUtil.getServerIp();
        // 请求 URI
        String requestUri = request.getRequestURI();
        // 请求参数
        Map<String, Object> params = WebUtils.getParametersStartingWith(request, null);

        // 打印错误日志
        LOGGER.error("An handler exception occurred, clientIp: {}, serverIp: {}, uri: {}, param: {}", clientIp, serverIp, requestUri, JSON.toJSONString(params), ex);

        // 1、Web前端（同步Get请求）返回 CommonResult
//        return RequestUtil.getResult(ex);

        // 2、Admin后台返回 ModalAndView 跳转错误页面
        CommonResult result = RequestUtil.getResult(ex);
        if (RequestUtil.isAjaxRequest(request)) {
            // Ajax 异步请求
            return result;
        } else {
            // 传统同步请求
            Map<String, Object> model = new HashMap<>();
            model.put("errCode", result.getCode());
            model.put("errMsg", result.getMsg());
            return new ModelAndView("error", model);
        }
    }
}
