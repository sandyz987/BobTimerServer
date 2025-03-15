package com.sandyz.bobtimerserver.mapper;

import com.sandyz.bobtimerserver.pojo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> getCommentsByArticleId(@Param("articleId") Integer articleId, @Param("userId") Integer userId);

    List<Comment> getRepliesByCommentId(@Param("commentId") Integer commentId, @Param("userId") Integer userId);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}