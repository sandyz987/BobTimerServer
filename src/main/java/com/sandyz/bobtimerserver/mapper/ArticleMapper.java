package com.sandyz.bobtimerserver.mapper;

import com.sandyz.bobtimerserver.pojo.Article;
import com.sandyz.bobtimerserver.vo.ArticleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer articleId);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

    List<ArticleVO> getArticles(@Param("topic") String topic);

    List<ArticleVO> getArticlesAll();

    List<ArticleVO> getArticlesByUserId(@Param("userId") Integer userId);

    ArticleVO getArticleById(@Param("articleId") Integer articleId);

//    int postArticle(@Param("article") Article article);
//
//    List<Article> getArticlesByTopic(String topic);


}