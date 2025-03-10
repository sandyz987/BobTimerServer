package com.sandyz.bobtimerserver.vo;

import lombok.Data;

import java.util.List;

@Data
public class ArticlePostQuery {

    private String text;

    private String topic;

    private List<String> images;

    private Integer userId;
}