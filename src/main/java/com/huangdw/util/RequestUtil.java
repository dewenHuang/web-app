package com.huangdw.util;

import com.huangdw.dto.CommonResult;
import com.huangdw.enums.RespEnum;
import com.huangdw.exception.CustomException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求工具类
 *
 * @author Devin
 * @date 2018/11/13
 */
public class RequestUtil {
    /**
     * 判断是否 Ajax 请求
     *
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return (null != request.getHeader("Accept") && request.getHeader("Accept").contains("application/json")) ||
                (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").contains("XMLHttpRequest"));
    }

    /**
     * 根据 Handler 异常返回通用结果
     *
     * @param ex
     * @return
     */
    public static CommonResult getResult(Exception ex) {
        RespEnum error;
        if (ex instanceof NoHandlerFoundException) {
            error = RespEnum.REQUEST_NOT_FOUND;
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            error = RespEnum.REQUEST_METHOD_NOT_ALLOWED;
        } else if (ex instanceof HttpMessageNotReadableException
                || ex instanceof MissingServletRequestParameterException
                || ex instanceof TypeMismatchException) {
            error = RespEnum.REQUEST_BAD;
        } else if (ex instanceof CustomException) {
            CustomException customException = (CustomException) ex;
            error = customException.getError();
        } else {
            error = RespEnum.SERVER_FAIL;
        }

        return CommonResult.fail(error);
    }
}
