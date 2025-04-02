package com.sandyz.bobtimerserver.service;

import com.github.pagehelper.PageInfo;
import com.sandyz.bobtimerserver.pojo.Comment;

public interface CommentService {
    int postComment(int articleId, String content);
    int replyComment(int commentId, String content);
    Boolean toggleLikeComment(int commentId);
    PageInfo<Comment> getComment(int articleId, int pageNum, int pageSize);
    PageInfo<Comment> getReply(int commentId, int pageNum, int pageSize);
    boolean deleteComment(int commentId);
}
