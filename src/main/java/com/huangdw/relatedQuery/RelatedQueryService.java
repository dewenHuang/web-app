package com.huangdw.relatedQuery;

import com.huangdw.dao.CustomerMapper;
import com.huangdw.dao.TicketMapper;
import com.huangdw.dao.UserMapper;
import com.huangdw.entity.Customer;
import com.huangdw.entity.Ticket;
import com.huangdw.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private UserMapper userMapper;

    public Customer queryCustomerByName(String name) {
        return customerMapper.selectCustomerByName(name);
    }

    public Ticket queryTicketById(int id) {
        return ticketMapper.selectTicketById(id);
    }

    /**
     * 查询用户购买的商品信息
     *
     * @return
     */
    public List<User> queryUserItems() {
        return userMapper.selectUserAndItemsResultMap();
    }
}
