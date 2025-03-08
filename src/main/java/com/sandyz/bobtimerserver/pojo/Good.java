package com.sandyz.bobtimerserver.pojo;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "good")
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer goodId;

    @Column(nullable = false, length = 100)
    private String goodName;

    @Column(nullable = false, length = 3000)
    private String statement;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, length = 10000)
    private String picUrl;

    @Column(nullable = false)
    private Timestamp submitTime;

    @Column(nullable = false)
    private Integer fromSchoolId;

    @Column(nullable = false)
    private Integer fromUserId;

    @Column(nullable = false)
    private Integer status;

    private Integer getUserId;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Integer soldCount;
}