package com.sandyz.bobtimerserver.pojo;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer replyId;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private Timestamp submitTime;

    @Column(nullable = false, length = 3000)
    private String text;

    private Integer which;

    private Integer replyUserId;
}