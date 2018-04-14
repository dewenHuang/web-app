package com.huangdw.controller;

import com.huangdw.dto.CommonResult;
import com.huangdw.enums.XxxErrorEnum;
import com.huangdw.exception.CommonException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: my-controller-app
 * @description: 测试处理器
 * @author: huangdw
 * @create: 2018-04-13
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/excWithSpringMvc")
//    @ResponseBody
    public CommonResult<String> excWithSpringMvc() throws Exception {
        throw new CommonException("用户名错误", XxxErrorEnum.USERNAME_ERROR);
    }

    @RequestMapping("/excWithSpringAop")
    @ResponseBody
    public CommonResult<String> excWithSpringAop() {
        return null;
    }
}
