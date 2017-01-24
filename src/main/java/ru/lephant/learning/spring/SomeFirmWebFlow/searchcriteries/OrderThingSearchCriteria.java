package ru.lephant.learning.spring.SomeFirmWebFlow.searchcriteries;

import ru.lephant.learning.spring.SomeFirmWebFlow.enums.OrderState;

import java.io.Serializable;

public class OrderThingSearchCriteria implements Serializable {

    private OrderState orderState;


    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }
}