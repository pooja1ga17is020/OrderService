package com.bits.OrderService.service;

import com.bits.OrderService.Exception.OrderNotFoundException;
import com.bits.OrderService.entity.Orders;
import com.bits.OrderService.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Orders saveOrders(Orders orders)
    {
        return orderRepository.save(orders);
    }

    public Orders updateOrders(Long orderId, Orders orders)
    {
        var existingOrder = orderRepository.findById(orderId)
                .orElseThrow(()-> new OrderNotFoundException("Order not found"));
        existingOrder.setOrderDate(orders.getOrderDate());
        existingOrder.setQuantity(orders.getQuantity());
        existingOrder.setStatus(orders.getStatus());
        existingOrder.setTotal_price(orders.getTotal_price());
        existingOrder.setCustomerId(orders.getCustomerId());
        existingOrder.setDeliveryId(orders.getDeliveryId());
        existingOrder.setRestaurantId(orders.getRestaurantId());
        existingOrder.setItem_name(orders.getItem_name());
        return orderRepository.save(existingOrder);
    }

    public void deleteOrders(Long orderId)
    {
        var existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        orderRepository.deleteById(orderId);
    }

    public List<Orders> getAllOrders()
    {
        return orderRepository.findAll();
    }

    public Optional<Orders> getOrderById(Long orderId)
    {
        return orderRepository.findById(orderId);
    }


}
