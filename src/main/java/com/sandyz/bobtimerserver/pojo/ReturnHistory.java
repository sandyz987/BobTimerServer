package com.sandyz.bobtimerserver.pojo;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "return_history")
public class ReturnHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer returnId;

    @Column(nullable = false)
    private Integer orderId;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private Integer goodId;

    @Column(nullable = false, length = 1000)
    private String returnReason;

    @Column(nullable = false)
    private String returnStatus;

    @Column(nullable = false)
    private Timestamp returnTime;
}