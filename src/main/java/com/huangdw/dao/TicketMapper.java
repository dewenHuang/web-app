package com.huangdw.dao;

import com.huangdw.entity.Ticket;

public interface TicketMapper {
    int deleteById(Integer id);

    int insert(Ticket record);

    Ticket selectById(Integer id);

    int updateById(Ticket record);

    /**
     * 根据 ID 查询车票信息(包含顾客信息)
     *
     * @param id
     * @return
     */
    Ticket selectTicketById(int id);
}