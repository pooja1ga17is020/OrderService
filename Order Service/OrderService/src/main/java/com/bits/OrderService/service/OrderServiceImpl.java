package com.bits.OrderService.service;

import com.bits.OrderService.Exception.OrderNotFoundException;
import com.bits.OrderService.entity.Orders;
import com.bits.OrderService.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;


    @Override
    public Orders createOrders(Orders orders) {
        return orderRepository.save(orders);
    }

    @Override
    public Orders updateOrders(Long order_id, Orders orders) {
        var existingOrder = orderRepository.findById(order_id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        existingOrder.setOrderDate(orders.getOrderDate());
        existingOrder.setStatus(orders.getStatus());
        existingOrder.setQuantity(orders.getQuantity());
        existingOrder.setItem_name(orders.getItem_name());
        existingOrder.setTotal_price(orders.getTotal_price());
        existingOrder.setDelivery_address(orders.getDelivery_address());
        existingOrder.setUser_id(orders.getUser_id());
        existingOrder.setRestaurant_name(orders.getRestaurant_name());
        return orderRepository.save(existingOrder);
    }

    @Override
    public void deleteOrder(Long order_id) {
        var existingOrder = orderRepository.findById(order_id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        orderRepository.delete(existingOrder);
    }

    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }
}
