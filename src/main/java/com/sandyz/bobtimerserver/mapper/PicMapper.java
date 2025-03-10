package com.sandyz.bobtimerserver.mapper;

import java.util.List;

import com.sandyz.bobtimerserver.pojo.Pic;
import org.apache.ibatis.annotations.Param;

public interface PicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Pic record);

    int insertSelective(Pic record);

    Pic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Pic record);

    int updateByPrimaryKey(Pic record);

    int batchInsert(@Param("list") List<Pic> list);

    int deleteByArticleId(@Param("article_id") Integer articleId);
}