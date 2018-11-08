package com.huangdw.controller;

import com.huangdw.dto.CommonResult;
import com.huangdw.entity.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @project: web-app
 * @description: Rest 风格的 URL.
 *
 * 以 CRUD 一条记录为例:
 * 新增: /emp POST
 * 修改: /emp PUT
 * 查询: /emp/1 GET
 * 删除: /emp/1 DELETE
 *
 * @author: huangdw
 * @create: 2018-11-06 16:56
 */
@Controller
@RequestMapping("/restTest")
public class RestTestController {
    /**
     * 跳转雇员列表页
     *
     * @return
     */
    @RequestMapping(value = "/emps", method = RequestMethod.GET)
    public String toEmpList(Model model) {
        // 从数据库查询雇员列表信息
        List<Employee> employees = new ArrayList<>();
        model.addAttribute("employees", employees);
        return "emp_list";
    }

    /**
     * 跳转添加页面
     *
     * @return
     */
    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String toEmpAdd() {
        return "emp_add";
    }

    /**
     * 执行添加操作
     *
     * @param record
     * @return
     */
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult doEmpAdd(Employee record, BindingResult bindingResult) {
        // 其实推荐在统一异常处理中捕获处理 BindException, 并将验证失败的字段集合交给前端展示
        if (bindingResult.hasFieldErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                System.out.println(fieldError.getField() + ": " + fieldError.getDefaultMessage());
            }
        }

        // 往数据库插入一条雇员记录
        return new CommonResult();
    }

    /**
     * 跳转编辑页面
     *
     * @return
     */
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public String toEmpEdit(@PathVariable Integer id, Model model) {
        // 根据 ID 查询雇员信息
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "emp_edit";
    }

    /**
     * 执行编辑操作
     *
     * @param record
     * @return
     */
    @RequestMapping(value = "/emp", method = RequestMethod.PUT)
    @ResponseBody
    public CommonResult doEmpEdit(Employee record) {
        // 更新数据库中的一条雇员记录
        return new CommonResult();
    }

    /**
     * 执行删除操作
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult doEmpDelete(@PathVariable Integer id) {
        // 根据 ID 删除雇员信息
        return new CommonResult();
    }
}
