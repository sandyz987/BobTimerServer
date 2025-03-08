package com.sandyz.bobtimerserver.pojo;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "seckill")
public class SecKill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seckillId;

    @Column(nullable = false)
    private Integer goodId;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal seckillPrice;

    @Column(nullable = false)
    private Integer seckillStock;

    @Column(nullable = false)
    private Timestamp startTime;

    @Column(nullable = false)
    private Timestamp endTime;

    @Column(nullable = false)
    private String status;
}