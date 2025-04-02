package com.sandyz.bobtimerserver.service;

import com.github.pagehelper.PageInfo;
import com.sandyz.bobtimerserver.vo.ArticlePostQuery;
import com.sandyz.bobtimerserver.vo.ArticleFullVO;

public interface ArticleService {
    PageInfo<ArticleFullVO> getArticles(String topic, int pageNum, int pageSize);
    PageInfo<ArticleFullVO> getArticlesByUser(int userId, int pageNum, int pageSize);
    ArticleFullVO postArticle(ArticlePostQuery articlePostQuery);
    ArticleFullVO getArticleById(int articleId);
    Boolean deleteArticle(int articleId);
    Boolean toggleLike(int articleId);
}
