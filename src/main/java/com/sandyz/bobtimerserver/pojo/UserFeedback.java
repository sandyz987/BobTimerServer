package com.sandyz.bobtimerserver.pojo;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "user_feedback")
public class UserFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feedbackId;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false, length = 3000)
    private String feedbackText;

    @Column(nullable = false)
    private Timestamp feedbackTime;

    @Column(length = 3000)
    private String replyText;

    @Column(nullable = false)
    private String status;
}