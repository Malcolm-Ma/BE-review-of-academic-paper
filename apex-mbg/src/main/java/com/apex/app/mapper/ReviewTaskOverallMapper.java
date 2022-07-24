package com.apex.app.mapper;

import com.apex.app.domain.model.ReviewTaskOverall;
import com.apex.app.domain.model.ReviewTaskOverallExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReviewTaskOverallMapper {
    long countByExample(ReviewTaskOverallExample example);

    int deleteByExample(ReviewTaskOverallExample example);

    int deleteByPrimaryKey(String id);

    int insert(ReviewTaskOverall row);

    int insertSelective(ReviewTaskOverall row);

    List<ReviewTaskOverall> selectByExample(ReviewTaskOverallExample example);

    ReviewTaskOverall selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("row") ReviewTaskOverall row, @Param("example") ReviewTaskOverallExample example);

    int updateByExample(@Param("row") ReviewTaskOverall row, @Param("example") ReviewTaskOverallExample example);

    int updateByPrimaryKeySelective(ReviewTaskOverall row);

    int updateByPrimaryKey(ReviewTaskOverall row);
}