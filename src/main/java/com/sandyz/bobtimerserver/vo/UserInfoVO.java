package com.sandyz.bobtimerserver.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserInfoVO {

    private String userId;

    private String stuId;

    private Integer schoolId;

    private String nickname;

    private Timestamp registerDate;

    private String sex;

    private String text;

    private String avatarUrl;

    private Boolean isAdmin;
}