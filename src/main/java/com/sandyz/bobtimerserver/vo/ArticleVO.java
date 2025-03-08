package com.sandyz.bobtimerserver.vo;

import com.sandyz.bobtimerserver.pojo.Article;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleVO extends Article {
    private UserInfoVO userInfoVO;
}