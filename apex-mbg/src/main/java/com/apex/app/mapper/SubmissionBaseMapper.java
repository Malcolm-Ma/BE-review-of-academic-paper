package com.apex.app.mapper;

import com.apex.app.domain.model.SubmissionBase;
import com.apex.app.domain.model.SubmissionBaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SubmissionBaseMapper {
    long countByExample(SubmissionBaseExample example);

    int deleteByExample(SubmissionBaseExample example);

    int deleteByPrimaryKey(String id);

    int insert(SubmissionBase row);

    int insertSelective(SubmissionBase row);

    List<SubmissionBase> selectByExampleWithBLOBs(SubmissionBaseExample example);

    List<SubmissionBase> selectByExample(SubmissionBaseExample example);

    SubmissionBase selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("row") SubmissionBase row, @Param("example") SubmissionBaseExample example);

    int updateByExampleWithBLOBs(@Param("row") SubmissionBase row, @Param("example") SubmissionBaseExample example);

    int updateByExample(@Param("row") SubmissionBase row, @Param("example") SubmissionBaseExample example);

    int updateByPrimaryKeySelective(SubmissionBase row);

    int updateByPrimaryKeyWithBLOBs(SubmissionBase row);

    int updateByPrimaryKey(SubmissionBase row);
}