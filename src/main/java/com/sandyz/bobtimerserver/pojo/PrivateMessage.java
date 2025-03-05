package com.sandyz.bobtimerserver.pojo;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "private_message")
public class PrivateMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer senderId;

    @Column(nullable = false)
    private Integer receiverId;

    @Column(nullable = false)
    private Integer messageType;

    @Column(nullable = false, length = 3000)
    private String messageContent;

    @Column(nullable = false)
    private Timestamp sendTime;

    @Column(nullable = false)
    private Integer status;
}