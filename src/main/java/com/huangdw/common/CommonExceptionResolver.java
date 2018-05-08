package com.huangdw.common;

import com.huangdw.dto.CommonResult;
import com.huangdw.enums.XxxErrorEnum;
import com.huangdw.exception.CommonException;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: my-controller-app
 * @description: 统一异常处理
 * @author: huangdw
 * @create: 2018-04-13
 */
@Component
public class CommonExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        // 判断请求类型(如果是Ajax请求, 则请求类型为 XMLHttpRequest)
        String reqType = request.getHeader("X-Requested-With");
        if (StringUtils.isBlank(reqType)) {
            // 传统同步请求
            return new ModelAndView("error/500", "errMsg", "出错了！");
        } else {
            // Ajax异步请求，返回Json串
            CommonResult result;
            if (e instanceof CommonException) {
                CommonException exception = (CommonException) e;
                result = new CommonResult(exception.getXxxError());
            } else {
                result = new CommonResult(XxxErrorEnum.SYSTEM_ERROR);
            }

            ObjectMapper objectMapper = new ObjectMapper();

            try {
                // 方式1
                response.getWriter().write(objectMapper.writeValueAsString(result));
                response.getWriter().flush();
                response.getWriter().close();

                // 方式2
//                objectMapper.writeValue(response.getWriter(), result);

                // 方式3, 设置了json编码
//                JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(response.getOutputStream(), JsonEncoding.UTF8);
//                jsonGenerator.writeObject(result);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            return null;
        }
    }
}
