package com.huangdw.relatedQuery;

import com.huangdw.dao.CustomerMapper;
import com.huangdw.dao.TicketMapper;
import com.huangdw.entity.Customer;
import com.huangdw.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @project: web-app
 * @description:
 * @author: huangdw
 * @create: 2018-11-02 16:41
 */
@Service
public class RelatedQueryService {
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private TicketMapper ticketMapper;

    public Customer queryCustomerByName(String name) {
        return customerMapper.selectCustomerByName(name);
    }

    public Ticket queryTicketById(int id) {
        return ticketMapper.selectTicketById(id);
    }
}
