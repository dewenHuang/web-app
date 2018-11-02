package com.huangdw.relatedQuery;

import com.huangdw.entity.Customer;
import com.huangdw.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @project: web-app
 * @description:
 * @author: huangdw
 * @create: 2018-11-02 16:50
 */
@Controller
@RequestMapping("/relatedQuery")
public class RelatedQueryController {
    @Autowired
    private RelatedQueryService relatedQueryService;

    @RequestMapping("/queryCustomerByName")
    @ResponseBody
    public Customer queryCustomerByName(String name) {
        return relatedQueryService.queryCustomerByName(name);
    }

    @RequestMapping("/queryTicketById")
    @ResponseBody
    public Ticket queryTicketById(Integer id) {
        return relatedQueryService.queryTicketById(id);
    }
}
