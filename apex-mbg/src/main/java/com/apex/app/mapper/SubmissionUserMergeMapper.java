package com.apex.app.mapper;

import com.apex.app.domain.model.SubmissionUserMerge;
import com.apex.app.domain.model.SubmissionUserMergeExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SubmissionUserMergeMapper {
    long countByExample(SubmissionUserMergeExample example);

    int deleteByExample(SubmissionUserMergeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SubmissionUserMerge row);

    int insertSelective(SubmissionUserMerge row);

    List<SubmissionUserMerge> selectByExample(SubmissionUserMergeExample example);

    SubmissionUserMerge selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") SubmissionUserMerge row, @Param("example") SubmissionUserMergeExample example);

    int updateByExample(@Param("row") SubmissionUserMerge row, @Param("example") SubmissionUserMergeExample example);

    int updateByPrimaryKeySelective(SubmissionUserMerge row);

    int updateByPrimaryKey(SubmissionUserMerge row);
}