package com.huangdw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @project: web-app
 * @description: Rest 风格的 URL.
 *
 * 以 CRUD 为例:
 * 新增: /order POST
 * 修改: /order/1 PUT     update?id=1
 * 查询: /order/1 GET     get?id=1
 * 删除: /order/1 DELETE  delete?id=1
 *
 * @author: huangdw
 * @create: 2018-11-06 16:56
 */
@Controller
@RequestMapping("/restTest")
public class RestTestController {

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public String orderQuery(@PathVariable Integer id) {
        System.out.println("test restGet: " + id);
        return "success";
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String orderAdd() {
        System.out.println("test restPost");
        return "success";
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE)
    public String orderDelete(@PathVariable Integer id) {
        System.out.println("test restDelete: " + id);
        return "success";
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.PUT)
    public String orderEdit(@PathVariable Integer id) {
        System.out.println("test restPut: " + id);
        return "success";
    }
}
