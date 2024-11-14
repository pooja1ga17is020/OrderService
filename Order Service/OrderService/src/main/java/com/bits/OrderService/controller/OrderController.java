package com.bits.OrderService.controller;

import com.bits.OrderService.entity.Orders;
import com.bits.OrderService.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/add-order")
    public ResponseEntity<Orders> addOrders(@RequestBody Orders orders)
    {
        return ResponseEntity.ok(orderService.createOrders(orders));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Orders> updateOrders(@PathVariable("id") Long order_id, @RequestBody Orders orders)
    {
        return ResponseEntity.ok(orderService.updateOrders(order_id, orders));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrders(@PathVariable("id") Long order_id)
    {
        orderService.deleteOrder(order_id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all-orders")
    public ResponseEntity<List<Orders>> getAllOrders()
    {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

}
