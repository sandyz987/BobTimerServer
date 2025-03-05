package com.sandyz.bobtimerserver.pojo;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "stock_history")
public class StockHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stockId;

    @Column(nullable = false)
    private Integer goodId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private String operation;

    @Column(nullable = false)
    private Timestamp operationTime;
}