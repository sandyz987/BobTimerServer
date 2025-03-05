package com.sandyz.bobtimerserver.pojo;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "cart_list")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private Integer goodId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Timestamp addTime;
}