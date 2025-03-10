package com.sandyz.bobtimerserver.service;

import com.github.pagehelper.PageInfo;
import com.sandyz.bobtimerserver.vo.ArticlePostQuery;
import com.sandyz.bobtimerserver.vo.ArticleVO;

public interface ArticleService {
    PageInfo<ArticleVO> getArticles(String topic, int pageNum, int pageSize);
    PageInfo<ArticleVO> getArticlesByUser(int userId, int pageNum, int pageSize);
    ArticleVO postArticle(ArticlePostQuery articlePostQuery);
    ArticleVO getArticleById(int articleId);
    Boolean deleteArticle(int articleId);
}
