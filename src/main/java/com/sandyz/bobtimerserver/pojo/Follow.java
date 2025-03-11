package com.sandyz.bobtimerserver.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "follow")
public class Follow {
    @Id
    private Integer userId;

    @Id
    private Integer followedUserId;

    @Column(nullable = false)
    private Timestamp followTime;
}