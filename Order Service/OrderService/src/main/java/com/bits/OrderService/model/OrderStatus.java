package com.bits.OrderService.model;

public enum OrderStatus {
    Pending,
    OrderAccepted,
    Preparing,
    ReadyForDelivery,
    PickedUp,
    OutForDelivery,
    Delivered,
    Canceled,
    Reschedule

}
