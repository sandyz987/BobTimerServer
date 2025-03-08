package com.sandyz.bobtimerserver.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "school")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer schoolId;

    @Column(nullable = false, length = 30)
    private String schoolName;

    @Column(nullable = false, length = 3000)
    private String statement;

    @Column(nullable = false, length = 1000)
    private String schoolAvatarUrl;

    @Column(nullable = false)
    private Long schoolMember;
}