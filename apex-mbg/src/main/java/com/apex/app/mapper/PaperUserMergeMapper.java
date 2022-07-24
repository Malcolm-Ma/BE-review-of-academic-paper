package com.apex.app.mapper;

import com.apex.app.domain.model.PaperUserMerge;
import com.apex.app.domain.model.PaperUserMergeExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaperUserMergeMapper {
    long countByExample(PaperUserMergeExample example);

    int deleteByExample(PaperUserMergeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PaperUserMerge row);

    int insertSelective(PaperUserMerge row);

    List<PaperUserMerge> selectByExample(PaperUserMergeExample example);

    PaperUserMerge selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") PaperUserMerge row, @Param("example") PaperUserMergeExample example);

    int updateByExample(@Param("row") PaperUserMerge row, @Param("example") PaperUserMergeExample example);

    int updateByPrimaryKeySelective(PaperUserMerge row);

    int updateByPrimaryKey(PaperUserMerge row);
}