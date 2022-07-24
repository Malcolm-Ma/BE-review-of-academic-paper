package com.apex.app.mapper;

import com.apex.app.domain.model.PaperBase;
import com.apex.app.domain.model.PaperBaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaperBaseMapper {
    long countByExample(PaperBaseExample example);

    int deleteByExample(PaperBaseExample example);

    int deleteByPrimaryKey(String id);

    int insert(PaperBase row);

    int insertSelective(PaperBase row);

    List<PaperBase> selectByExampleWithBLOBs(PaperBaseExample example);

    List<PaperBase> selectByExample(PaperBaseExample example);

    PaperBase selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("row") PaperBase row, @Param("example") PaperBaseExample example);

    int updateByExampleWithBLOBs(@Param("row") PaperBase row, @Param("example") PaperBaseExample example);

    int updateByExample(@Param("row") PaperBase row, @Param("example") PaperBaseExample example);

    int updateByPrimaryKeySelective(PaperBase row);

    int updateByPrimaryKeyWithBLOBs(PaperBase row);

    int updateByPrimaryKey(PaperBase row);
}