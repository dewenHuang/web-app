package com.huangdw.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * 订单明细实体
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetail implements Serializable {
    private static final long serialVersionUID = -716897364201903064L;

    private Integer id;

    private Integer orderId;

    private Integer itemId;

    private Integer itemNum;

    /** 订单明细所对应的商品 */
    private Item item;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", itemId=" + itemId +
                ", itemNum=" + itemNum +
                ", item=" + item +
                '}';
    }
}