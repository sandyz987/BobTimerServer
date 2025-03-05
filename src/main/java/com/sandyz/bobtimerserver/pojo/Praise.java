package com.sandyz.bobtimerserver.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "praise_list")
public class Praise {
    @Id
    private Integer id;

    @Id
    private Integer userId;

    @Id
    private Integer which;
}