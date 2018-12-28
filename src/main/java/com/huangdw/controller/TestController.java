package com.huangdw.controller;

import com.huangdw.dto.CommonResult;
import com.huangdw.entity.Employee;
import com.huangdw.enums.RespEnum;
import com.huangdw.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: my-controller-app
 * @description: 测试处理器
 * @author: huangdw
 * @create: 2018-04-13
 */
@Controller
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/excWithSpringMvc")
//    @ResponseBody
    public CommonResult excWithSpringMvc(String userName, String password, Employee employee) {
        throw new CustomException("用户名含有特殊字符", RespEnum.PARAMETER_ERROR);
    }

    @RequestMapping("/excWithSpringAop")
    @ResponseBody
    public CommonResult excWithSpringAop() {
        LOGGER.info("Xxx method begin, password: {}", "123456");
        throw new CustomException("用户密码少于8位", RespEnum.PARAMETER_ERROR);
    }

    /**
     * 测试绑定参数
     *
     * @param name 不传，自动赋值null；可以传空串或者空格
     * @param age 不传，抛出IllegalStateException异常，因为不能赋值null给int参数；不可以传任何不能转换int的参数（包括空串、空格等），会报400错；
     *            所以需要添加@RequestParam注解指定参数必传（就不存在赋值null的问题），避免抛出异常，当然如果同时指定required和defaultValue的话，可以对null、""和" "进行默认值处理；
     *            对于可能为空的参数（age为空，没有意义！），可以使用包装类Integer接收，这样就不会抛出IllegalStateException异常了。
     */
    @RequestMapping(value = "/bindParams", method = RequestMethod.GET)
    public void bindParams(String name, @RequestParam(value = "age") int age, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("绑定参数成功! name=" + name + ", age=" + age);
    }

    /**
     * 在 Interceptor(preHandler()) 之后执行, 且在Interceptor(postHandler()) 之前执行
     *
     * @return
     */
    @RequestMapping("/test")
    public ModelAndView handleRequest(){
        System.out.println("------------- TestController executed ------------");
        return new ModelAndView("test");
    }
}
