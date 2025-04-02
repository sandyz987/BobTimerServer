package com.sandyz.bobtimerserver.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sandyz.bobtimerserver.auth.UserHolder;
import com.sandyz.bobtimerserver.mapper.ArticleMapper;
import com.sandyz.bobtimerserver.mapper.CommentMapper;
import com.sandyz.bobtimerserver.mapper.PraiseMapper;
import com.sandyz.bobtimerserver.pojo.Comment;
import com.sandyz.bobtimerserver.pojo.Praise;
import com.sandyz.bobtimerserver.service.CommentService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class CommentServiceImpl implements CommentService {
    private final ArticleMapper articleMapper;
    private final CommentMapper commentMapper;
    private final PraiseMapper praiseMapper;

    public CommentServiceImpl(ArticleMapper articleMapper, CommentMapper commentMapper, PraiseMapper praiseMapper) {
        this.articleMapper = articleMapper;
        this.commentMapper = commentMapper;
        this.praiseMapper = praiseMapper;
    }

    @Override
    public int postComment(int articleId, String content) {
        int userId = UserHolder.getUserId();
        if (userId == -1) {
            return -1;
        }
        if (articleMapper.selectByPrimaryKey(articleId) == null) {
            return -1;
        }
        Comment comment = new Comment();
        comment.setWhich(1);
        comment.setUserId(userId);
        comment.setText(content);
        comment.setReplyId(articleId);
        comment.setSubmitTime(new Timestamp(System.currentTimeMillis()));

        commentMapper.insert(comment);
        return comment.getId();
    }

    @Override
    public int replyComment(int commentId, String content) {
        int userId = UserHolder.getUserId();
        if (userId == -1) {
            return -1;
        }
        Comment commentToReply = commentMapper.selectByPrimaryKey(commentId);
        if (commentToReply == null) {
            return -1;
        }

        Comment comment = new Comment();
        comment.setWhich(2);
        comment.setUserId(userId);
        comment.setText(content);
        comment.setReplyId(commentId);
        comment.setReplyUserId(commentToReply.getUserId());
        comment.setSubmitTime(new Timestamp(System.currentTimeMillis()));

        commentMapper.insert(comment);

        return comment.getId();
    }

    @Override
    public Boolean toggleLikeComment(int commentId) {
        if (commentMapper.selectByPrimaryKey(commentId) == null) {
            return false;
        }
        try {
            Praise praise = new Praise();
            praise.setId(commentId);
            praise.setUserId(UserHolder.getUserId());
            praise.setWhich(1);
            praiseMapper.insertSelective(praise);
        } catch (DuplicateKeyException e) {
            praiseMapper.deletePraise(commentId, 1, UserHolder.getUserId());
        }
        return true;
    }

    @Override
    public PageInfo<Comment> getComment(int articleId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        int userId = UserHolder.getUserId();
        return new PageInfo<>(commentMapper.getCommentsByArticleId(articleId, userId));
    }

    @Override
    public PageInfo<Comment> getReply(int commentId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        int userId = UserHolder.getUserId();
        return new PageInfo<>(commentMapper.getRepliesByCommentId(commentId, userId));
    }

    @Override
    public boolean deleteComment(int commentId) {
        Comment comment = commentMapper.selectByPrimaryKey(commentId);
        if (comment == null) {
            return false;
        }
        if (comment.getUserId() != UserHolder.getUserId()) {
            return false;
        }
        commentMapper.deleteByPrimaryKey(commentId);
        return true;
    }
}
