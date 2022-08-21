package com.apex.app.mapper;

import com.apex.app.domain.model.PaperAllocation;
import com.apex.app.domain.model.PaperAllocationExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaperAllocationMapper {
    long countByExample(PaperAllocationExample example);

    int deleteByExample(PaperAllocationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PaperAllocation row);

    int insertSelective(PaperAllocation row);

    List<PaperAllocation> selectByExample(PaperAllocationExample example);

    PaperAllocation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") PaperAllocation row, @Param("example") PaperAllocationExample example);

    int updateByExample(@Param("row") PaperAllocation row, @Param("example") PaperAllocationExample example);

    int updateByPrimaryKeySelective(PaperAllocation row);

    int updateByPrimaryKey(PaperAllocation row);
}