package com.bits.OrderService.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    @NotNull(message = "Restaurant name cannot be null")
    private String restaurant_name;

    @NotNull(message = "Item name cannot be null")
    private String item_name;

    @NotNull(message = "Quantity cannot be null")
    private int quantity;

    @NotNull(message = "Status cannot be null")
    private String status;

    @NotNull(message = "Total price cannot be null")
    private float total_price;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Order date cannot be null")
    private LocalDate orderDate;

    @NotNull(message = "Delivery address cannot be null")
    private String delivery_address;

    @NotNull(message = "User id cannot be null" )
    private Long user_id;
}
