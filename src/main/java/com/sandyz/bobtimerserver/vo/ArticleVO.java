package com.sandyz.bobtimerserver.vo;

import com.sandyz.bobtimerserver.pojo.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleVO extends Article {
    private UserInfoVO authorUser;
    private List<String> images;
    private List<UserInfoVO> praiseUsers;
    private int praiseCount;
    private boolean isPraised;
    private int commentCount;
}