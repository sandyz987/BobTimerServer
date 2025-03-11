package com.sandyz.bobtimerserver.controller;

import com.github.pagehelper.PageInfo;
import com.sandyz.bobtimerserver.auth.UserHolder;
import com.sandyz.bobtimerserver.service.ArticleService;
import com.sandyz.bobtimerserver.util.ResultMessage;
import com.sandyz.bobtimerserver.vo.ArticlePostQuery;
import com.sandyz.bobtimerserver.vo.ArticleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/community")
@Slf4j
public class CommunityController {
    @Autowired
    private ArticleService articleService;


    @PostMapping("/post-article")
    ResultMessage postArticle(@RequestParam String content, @RequestParam(defaultValue = "广场") String topic, @RequestBody(required = false) List<String> images) {
        ArticlePostQuery articlePostQuery = new ArticlePostQuery();
        articlePostQuery.setText(content);
        articlePostQuery.setTopic(topic);
        articlePostQuery.setImages(images);
        articlePostQuery.setUserId(UserHolder.getUser().getId());
        return ResultMessage.success(200, articleService.postArticle(articlePostQuery));
    }

    @DeleteMapping("/delete-article/{articleId}")
    ResultMessage deleteArticle(@PathVariable int articleId) {
        if (!articleService.deleteArticle(articleId)) {
            return ResultMessage.fail(404, "文章不存在");
        }
        return ResultMessage.success(200, "删除成功");
    }

    @GetMapping("/articles")
    PageInfo<ArticleVO> getArticles(@RequestParam(required = false) String topic, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        log.trace("getArticles: topic={} pageNum={} pageSize={}", topic, pageNum, pageSize);
        return articleService.getArticles(topic, pageNum, pageSize);
    }

    @GetMapping("/articles/user/{userId}")
    PageInfo<ArticleVO> getArticlesByUser(@PathVariable int userId, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        log.trace("getArticles: userId={} pageNum={} pageSize={}", userId, pageNum, pageSize);
        return articleService.getArticlesByUser(userId, pageNum, pageSize);
    }

    @GetMapping("/article/{articleId}")
    ResultMessage getArticleById(@PathVariable int articleId) {
        ArticleVO articleVO = articleService.getArticleById(articleId);
        if (articleVO == null) {
            return ResultMessage.fail(404, "文章不存在");
        }
        return ResultMessage.success(200, articleVO);
    }

    @PostMapping("/praise/{articleId}")
    ResultMessage praiseArticle(@PathVariable int articleId) {
        return ResultMessage.handle(articleService.toggleLike(articleId));
    }


}
