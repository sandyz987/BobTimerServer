package com.sandyz.bobtimerserver.controller;

import com.github.pagehelper.PageInfo;
import com.sandyz.bobtimerserver.auth.UserHolder;
import com.sandyz.bobtimerserver.pojo.Comment;
import com.sandyz.bobtimerserver.service.ArticleService;
import com.sandyz.bobtimerserver.service.CommentService;
import com.sandyz.bobtimerserver.util.ResultMessage;
import com.sandyz.bobtimerserver.vo.ArticlePostQuery;
import com.sandyz.bobtimerserver.vo.ArticleFullVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/community")
@Slf4j
public class CommunityController {
    private final ArticleService articleService;
    private final CommentService commentService;
    CommunityController(ArticleService articleService, CommentService commentService) {
        this.articleService = articleService;
        this.commentService = commentService;
    }

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

    @DeleteMapping("/delete-comment/{commentId}")
    ResultMessage deleteComment(@PathVariable int commentId) {
        if (!commentService.deleteComment(commentId)) {
            return ResultMessage.fail(404, "评论不存在");
        }
        return ResultMessage.success(200, "删除成功");
    }

    @GetMapping("/articles")
    PageInfo<ArticleFullVO> getArticles(@RequestParam(required = false) String topic, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        log.trace("getArticles: topic={} pageNum={} pageSize={}", topic, pageNum, pageSize);
        return articleService.getArticles(topic, pageNum, pageSize);
    }

    @GetMapping("/articles/user/{userId}")
    PageInfo<ArticleFullVO> getArticlesByUser(@PathVariable int userId, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        log.trace("getArticles: userId={} pageNum={} pageSize={}", userId, pageNum, pageSize);
        return articleService.getArticlesByUser(userId, pageNum, pageSize);
    }

    @GetMapping("/article/{articleId}")
    ResultMessage getArticleById(@PathVariable int articleId) {
        ArticleFullVO articleFullVO = articleService.getArticleById(articleId);
        if (articleFullVO == null) {
            return ResultMessage.fail(404, "文章不存在");
        }
        return ResultMessage.success(200, articleFullVO);
    }

    @PostMapping("/toggle-praise/{articleId}")
    ResultMessage togglePraise(@PathVariable int articleId) {
        return ResultMessage.handle(articleService.toggleLike(articleId));
    }

    @PostMapping("/toggle-praise-comment/{commentId}")
    ResultMessage togglePraiseComment(@PathVariable int commentId) {
        return ResultMessage.handle(commentService.toggleLikeComment(commentId));
    }
    @PostMapping("/post-comment")
    ResultMessage postComment(@RequestParam int articleId, @RequestParam String content) {
        return ResultMessage.idWrapper(commentService.postComment(articleId, content));
    }
    @PostMapping("/reply-comment")
    ResultMessage replyComment(@RequestParam int commentId, @RequestParam String content) {
        return ResultMessage.idWrapper(commentService.replyComment(commentId, content));
    }

    @GetMapping("/comments/{articleId}")
    PageInfo<Comment> getComment(@PathVariable int articleId, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        return commentService.getComment(articleId, pageNum, pageSize);
    }

    @GetMapping("/reply/{commentId}")
    PageInfo<Comment> getReply(@PathVariable int commentId, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        return commentService.getReply(commentId, pageNum, pageSize);
    }


}
