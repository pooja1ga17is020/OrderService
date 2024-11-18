package com.bits.OrderService.controller;

import com.bits.OrderService.entity.Orders;
import com.bits.OrderService.model.OrderDto;
import com.bits.OrderService.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
@CrossOrigin("http://localhost:9091")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/add-order")
    public ResponseEntity<Long> addOrders(@RequestBody OrderDto orderDto)
    {
        return ResponseEntity.ok(orderService.createOrder(orderDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrderDto> updateOrders(@PathVariable("id") Long orderId, @RequestBody OrderDto orderDto)
    {
        OrderDto updatedOrders = orderService.updateOrders(orderId, orderDto);
        return ResponseEntity.ok(updatedOrders);
    }

    @GetMapping("all-orders")
    public ResponseEntity<List<OrderDto>> getAllOrders()
    {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Long id)
    {
        boolean isDeleted = orderService.deleteOrders(id);
        if (isDeleted) {
            return ResponseEntity.ok("Order deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("Order not found.");
        }
    }
}
