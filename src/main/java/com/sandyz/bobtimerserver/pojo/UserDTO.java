package com.sandyz.bobtimerserver.pojo;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Table(name = "`user`")
public class UserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer userId;

    private String username;

    private String password;

}