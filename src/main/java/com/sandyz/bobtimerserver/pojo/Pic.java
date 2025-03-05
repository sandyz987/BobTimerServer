package com.sandyz.bobtimerserver.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pic_list")
public class Pic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer dynamicId;

    @Column(nullable = false, length = 3000)
    private String picUrl;
}