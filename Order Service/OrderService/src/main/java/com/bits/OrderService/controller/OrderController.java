package com.bits.OrderService.controller;

import com.bits.OrderService.entity.Orders;
import com.bits.OrderService.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
@CrossOrigin("http://localhost:9091")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/add-order")
    public ResponseEntity<Orders> addOrders(@RequestBody Orders orders)
    {
        return ResponseEntity.ok(orderService.saveOrders(orders));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Orders> updateOrders(@PathVariable("id") Long orderId, @RequestBody Orders orders)
    {
        return ResponseEntity.ok(orderService.updateOrders(orderId, orders));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long orderId)
    {
        orderService.deleteOrders(orderId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all-orders")
    public ResponseEntity<List<Orders>> getAllOrders()
    {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/get-order/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable("id") Long orderId)
    {
        Optional<Orders> orders = orderService.getOrderById(orderId);
        return orders.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer")
    public ResponseEntity<Page<Orders>> searchOrdersBasedOnCustomerId(
            @RequestParam(required = false) Long customerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size)
    {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Orders> orders = orderService.searchOrderBasedOnCustomerId(customerId, pageable);
        return ResponseEntity.ok(orders);
    }


}
