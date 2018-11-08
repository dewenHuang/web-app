package com.huangdw.converter;

import com.huangdw.entity.Employee;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 自定义类型转换器
 * <p>实现前端传一个字符串"黄德文-32", 后台用 Employee 类型的形参进行接收</p>
 *
 * @author huangdw
 * @create 2018-11-07 20:27
 */
@Component
public class EmployeeConverter implements Converter<String, Employee> {
    @Override
    public Employee convert(String source) {
        if (StringUtils.isNotBlank(source)) {
            // 黄德文-32
            String[] vals = source.split("-");
            if (vals.length == 2) {
                Employee employee = new Employee();
                employee.setName(vals[0]);
                employee.setAge(Integer.valueOf(vals[1]));

                System.out.println(source + " convert to " + employee);
                return employee;
            }
        }
        return null;
    }
}
