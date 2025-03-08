package com.sandyz.bobtimerserver.mapper;

import com.sandyz.bobtimerserver.pojo.Article;
import com.sandyz.bobtimerserver.vo.ArticleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer articleId);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

    List<ArticleVO> getArticles(@Param("topic") String topic, @Param("limit") int limit, @Param("offset") int offset);

//    List<Article> getArticlesByUserId(Integer userId);
//
//    List<Article> getArticlesByTopic(String topic);


}