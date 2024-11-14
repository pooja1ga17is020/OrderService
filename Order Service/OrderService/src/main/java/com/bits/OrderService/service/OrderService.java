package com.bits.OrderService.service;

import com.bits.OrderService.entity.Orders;


import java.util.List;

public interface OrderService {

    Orders createOrders(Orders orders);

    Orders updateOrders(Long order_id, Orders orders);

    void deleteOrder(Long order_id);

    List<Orders> getAllOrders();
}
