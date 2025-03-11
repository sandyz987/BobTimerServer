package com.sandyz.bobtimerserver.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "praise")
public class Praise {
    @Id
    private Integer id;

    @Id
    private Integer userId;

    @Id
    private Integer which;

    @Column(nullable = false)
    private Timestamp praiseTime;
}