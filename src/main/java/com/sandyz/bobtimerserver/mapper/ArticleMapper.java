package com.sandyz.bobtimerserver.mapper;

import com.sandyz.bobtimerserver.pojo.Article;
import com.sandyz.bobtimerserver.vo.ArticleFullVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer articleId);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

    List<ArticleFullVO> getArticles(@Param("topic") String topic, @Param("userId") Integer userId);

    List<ArticleFullVO> getArticlesAll(@Param("userId") Integer userId);

    List<ArticleFullVO> getArticlesByUserId(@Param("userId") Integer userId);

    ArticleFullVO getArticleById(@Param("articleId") Integer articleId, @Param("userId") Integer userId);

//    int postArticle(@Param("article") Article article);
//
//    List<Article> getArticlesByTopic(String topic);


}