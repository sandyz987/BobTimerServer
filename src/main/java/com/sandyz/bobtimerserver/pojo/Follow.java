package com.sandyz.bobtimerserver.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "follow")
public class Follow {
    @Id
    private Integer userId;

    @Id
    private Integer followedUserId;
}