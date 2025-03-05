package com.sandyz.bobtimerserver.pojo;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "dynamic_list")
public class Dynamic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dynamicId;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private Timestamp submitTime;

    @Column(nullable = false, length = 3000)
    private String text;

    @Column(nullable = false, length = 100)
    private String topic;
}