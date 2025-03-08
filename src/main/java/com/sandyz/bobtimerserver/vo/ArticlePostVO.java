package com.sandyz.bobtimerserver.vo;

import lombok.Data;

import java.util.List;

@Data
public class ArticlePostVO {

    private String text;

    private String topic;

    private List<String> images;
}