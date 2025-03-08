package com.sandyz.bobtimerserver.pojo;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 20)
    private String userId;

    @Column(unique = true, nullable = false, length = 30)
    private String stuId;

    @Column(nullable = false)
    private Integer schoolId;

    @Column(nullable = false, length = 30)
    private String nickname;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false)
    private Timestamp registerDate;

    @Column(nullable = false)
    private String sex;

    @Column(nullable = false, length = 300)
    private String text;

    @Column(nullable = false, length = 1000)
    private String avatarUrl;

    @Column(nullable = false)
    private Boolean isAdmin;
}