package com.sandyz.bobtimerserver.vo;

import com.sandyz.bobtimerserver.pojo.Article;
import com.sandyz.bobtimerserver.pojo.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommentVO extends Comment {
    private UserInfoVO authorUser;
    private List<UserInfoVO> praiseUsers;
    private int praiseCount;
    private boolean isPraised;
    private int replyCount;
}