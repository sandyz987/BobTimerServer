package com.sandyz.bobtimerserver.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pic")
public class Pic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer articleId;

    @Column(nullable = false, length = 3000)
    private String picUrl;
}