package com.apex.app.mapper;

import com.apex.app.domain.model.ReviewEvaluation;
import com.apex.app.domain.model.ReviewEvaluationExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReviewEvaluationMapper {
    long countByExample(ReviewEvaluationExample example);

    int deleteByExample(ReviewEvaluationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ReviewEvaluation row);

    int insertSelective(ReviewEvaluation row);

    List<ReviewEvaluation> selectByExampleWithBLOBs(ReviewEvaluationExample example);

    List<ReviewEvaluation> selectByExample(ReviewEvaluationExample example);

    ReviewEvaluation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ReviewEvaluation row, @Param("example") ReviewEvaluationExample example);

    int updateByExampleWithBLOBs(@Param("row") ReviewEvaluation row, @Param("example") ReviewEvaluationExample example);

    int updateByExample(@Param("row") ReviewEvaluation row, @Param("example") ReviewEvaluationExample example);

    int updateByPrimaryKeySelective(ReviewEvaluation row);

    int updateByPrimaryKeyWithBLOBs(ReviewEvaluation row);

    int updateByPrimaryKey(ReviewEvaluation row);
}