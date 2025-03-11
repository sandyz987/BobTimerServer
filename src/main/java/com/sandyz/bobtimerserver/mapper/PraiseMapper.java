package com.sandyz.bobtimerserver.mapper;

import com.sandyz.bobtimerserver.pojo.Praise;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PraiseMapper {
    int deletePraise(@Param("id") Integer id, @Param("which") Integer which, @Param("user_id") Integer userId);

    int praise(Praise record);

    Praise selectPraise(@Param("id") Integer id, @Param("which") Integer which, @Param("user_id") Integer userId);

    List<Praise> selectPraises(@Param("id") Integer id, @Param("which") Integer which);

    int insertSelective(Praise record);
}