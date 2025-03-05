package com.sandyz.bobtimerserver.pojo;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "order_list")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private Timestamp orderDate;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal totalPrice;

    @Column(nullable = false, length = 500)
    private String shippingAddress;

    @Column(nullable = false)
    private String shippingStatus;

    @Column(nullable = false)
    private String paymentStatus;

    private Timestamp deliveryTime;
}