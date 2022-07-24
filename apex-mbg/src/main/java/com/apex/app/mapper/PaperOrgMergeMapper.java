package com.apex.app.mapper;

import com.apex.app.domain.model.PaperOrgMerge;
import com.apex.app.domain.model.PaperOrgMergeExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaperOrgMergeMapper {
    long countByExample(PaperOrgMergeExample example);

    int deleteByExample(PaperOrgMergeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PaperOrgMerge row);

    int insertSelective(PaperOrgMerge row);

    List<PaperOrgMerge> selectByExample(PaperOrgMergeExample example);

    PaperOrgMerge selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") PaperOrgMerge row, @Param("example") PaperOrgMergeExample example);

    int updateByExample(@Param("row") PaperOrgMerge row, @Param("example") PaperOrgMergeExample example);

    int updateByPrimaryKeySelective(PaperOrgMerge row);

    int updateByPrimaryKey(PaperOrgMerge row);
}