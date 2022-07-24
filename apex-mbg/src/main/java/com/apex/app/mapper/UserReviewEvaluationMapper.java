package com.apex.app.mapper;

import com.apex.app.domain.model.UserReviewEvaluation;
import com.apex.app.domain.model.UserReviewEvaluationExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserReviewEvaluationMapper {
    long countByExample(UserReviewEvaluationExample example);

    int deleteByExample(UserReviewEvaluationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserReviewEvaluation row);

    int insertSelective(UserReviewEvaluation row);

    List<UserReviewEvaluation> selectByExampleWithBLOBs(UserReviewEvaluationExample example);

    List<UserReviewEvaluation> selectByExample(UserReviewEvaluationExample example);

    UserReviewEvaluation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") UserReviewEvaluation row, @Param("example") UserReviewEvaluationExample example);

    int updateByExampleWithBLOBs(@Param("row") UserReviewEvaluation row, @Param("example") UserReviewEvaluationExample example);

    int updateByExample(@Param("row") UserReviewEvaluation row, @Param("example") UserReviewEvaluationExample example);

    int updateByPrimaryKeySelective(UserReviewEvaluation row);

    int updateByPrimaryKeyWithBLOBs(UserReviewEvaluation row);

    int updateByPrimaryKey(UserReviewEvaluation row);
}