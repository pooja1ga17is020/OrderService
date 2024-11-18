package com.bits.OrderService.service;

import com.bits.OrderService.Exception.OrderNotFoundException;
import com.bits.OrderService.entity.Orders;
import com.bits.OrderService.model.OrderDto;
import com.bits.OrderService.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Long createOrder(OrderDto orderDto)
    {
        Orders order = orderRepository.save(buildOrdersEntity(orderDto));
        return order.getOrder_id();
    }

    public OrderDto updateOrders(Long orderId, OrderDto orderDto)
    {
        Orders orders = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        orders.setRestaurant_name(orderDto.getRestaurant_name());
        orders.setItem_name(orderDto.getItem_name());
        orders.setQuantity(orderDto.getQuantity());
        orders.setStatus(orderDto.getStatus());
        orders.setTotal_price(orderDto.getTotal_price());
        orders.setOrderDate(orderDto.getOrderDate());
        orders.setDelivery_address(orderDto.getDelivery_address());
        orders.setUser_id(orderDto.getUser_id());
        Orders updatedOrders = orderRepository.save(orders);
        return convertEntityToDto(updatedOrders);
    }

    public List<OrderDto> getAllOrders()
    {
        List<OrderDto> orderDtos = new ArrayList<>();
        List<Orders> orders = orderRepository.findAll();
        orders.forEach(order -> {
                    orderDtos.add(OrderDto.builder()
                                    .restaurant_name(order.getRestaurant_name())
                                    .item_name(order.getItem_name())
                                    .quantity(order.getQuantity())
                                    .status(order.getStatus())
                                    .total_price(order.getTotal_price())
                                    .orderDate(order.getOrderDate())
                                    .delivery_address(order.getDelivery_address())
                                    .user_id(order.getUser_id())
                            .build());

                });
            return orderDtos;
    }

    public Boolean deleteOrders(Long id)
    {
        if(orderRepository.existsById(id))
        {
            orderRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    private OrderDto convertEntityToDto(Orders updatedOrders) {
        return OrderDto.builder()
                .restaurant_name(updatedOrders.getRestaurant_name())
                .item_name(updatedOrders.getItem_name())
                .quantity(updatedOrders.getQuantity())
                .status(updatedOrders.getStatus())
                .total_price(updatedOrders.getTotal_price())
                .orderDate(updatedOrders.getOrderDate())
                .delivery_address(updatedOrders.getDelivery_address())
                .user_id(updatedOrders.getUser_id())
                .build();
    }

    private Orders buildOrdersEntity(OrderDto orderDto) {
        return Orders.builder()
                .restaurant_name(orderDto.getRestaurant_name())
                .quantity(orderDto.getQuantity())
                .status(orderDto.getStatus())
                .item_name(orderDto.getItem_name())
                .total_price(orderDto.getTotal_price())
                .orderDate(orderDto.getOrderDate())
                .delivery_address(orderDto.getDelivery_address())
                .user_id(orderDto.getUser_id())
                .build();
    }


}
