package com.sandyz.bobtimerserver.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sandyz.bobtimerserver.auth.UserHolder;
import com.sandyz.bobtimerserver.mapper.ArticleMapper;
import com.sandyz.bobtimerserver.mapper.PicMapper;
import com.sandyz.bobtimerserver.mapper.PraiseMapper;
import com.sandyz.bobtimerserver.pojo.Article;
import com.sandyz.bobtimerserver.pojo.Pic;
import com.sandyz.bobtimerserver.pojo.Praise;
import com.sandyz.bobtimerserver.service.ArticleService;
import com.sandyz.bobtimerserver.vo.ArticlePostQuery;
import com.sandyz.bobtimerserver.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleMapper articleMapper;
    private final PicMapper picMapper;
    private final PraiseMapper praiseMapper;

    public ArticleServiceImpl(ArticleMapper articleMapper, PicMapper picMapper, PraiseMapper praiseMapper) {
        this.articleMapper = articleMapper;
        this.picMapper = picMapper;
        this.praiseMapper = praiseMapper;
    }

    @Override
    public PageInfo<ArticleVO> getArticles(String topic, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (topic == null || topic.isBlank()) {
            return new PageInfo<>(articleMapper.getArticlesAll());
        }
        return new PageInfo<>(articleMapper.getArticles(topic));
    }

    @Override
    public PageInfo<ArticleVO> getArticlesByUser(int userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(articleMapper.getArticlesByUserId(userId));
    }

    @Transactional
    @Override
    public ArticleVO postArticle(ArticlePostQuery articlePostQuery) {
        Article article = new Article();
        article.setText(articlePostQuery.getText());
        article.setTopic(articlePostQuery.getTopic());
        article.setUserId(articlePostQuery.getUserId());
        article.setSubmitTime(new Timestamp(System.currentTimeMillis()));
        articleMapper.insert(article);
        if (articlePostQuery.getImages() != null) {
            ArrayList<Pic> pics = new ArrayList<>();
            for (String image : articlePostQuery.getImages()) {
                Pic pic = new Pic();
                pic.setArticleId(article.getArticleId());
                pic.setPicUrl(image);
                pics.add(pic);
            }
            picMapper.batchInsert(pics);
        }
        return getArticleById(article.getArticleId());
    }

    @Override
    public ArticleVO getArticleById(int articleId) {
        return articleMapper.getArticleById(articleId);
    }

    @Transactional
    @Override
    public Boolean deleteArticle(int articleId) {
        picMapper.deleteByArticleId(articleId);
        return articleMapper.deleteByPrimaryKey(articleId) != 0;
    }

    @Override
    public Boolean toggleLike(int articleId) {
        if (articleMapper.selectByPrimaryKey(articleId) == null) {
            return false;
        }
        try {
            Praise praise = new Praise();
            praise.setId(articleId);
            praise.setUserId(UserHolder.getUser().getId());
            praise.setWhich(0);
            praiseMapper.insertSelective(praise);
        } catch (DuplicateKeyException e) {
            praiseMapper.deletePraise(articleId, 0, UserHolder.getUser().getId()); // 取消点赞
        }
        return true;
    }


}
