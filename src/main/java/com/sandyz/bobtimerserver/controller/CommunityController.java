package com.sandyz.bobtimerserver.controller;

import com.sandyz.bobtimerserver.mapper.ArticleMapper;
import com.sandyz.bobtimerserver.util.ResultMessage;
import com.sandyz.bobtimerserver.vo.ArticlePostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private ArticleMapper articleMapper;

    @PostMapping("/post-article")
    ResultMessage postArticle(@RequestBody ArticlePostVO articlePostVO) {
        return ResultMessage.fail(501, "Not implemented yet");
    }

    @GetMapping("/articles")
    ResultMessage getArticles(@RequestParam String topic,  @RequestParam(defaultValue = "10") Integer limit, @RequestParam(defaultValue = "0") Integer offset) {
        return ResultMessage.success(200, articleMapper.getArticles(topic, limit, offset));
    }

    @GetMapping("/articles-pager")
    ResultMessage getArticlesPager(@RequestParam String topic, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return ResultMessage.fail(501, "Not implemented yet");
    }



}
