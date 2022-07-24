package com.apex.app.mapper;

import com.apex.app.domain.model.OrgBase;
import com.apex.app.domain.model.OrgBaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrgBaseMapper {
    long countByExample(OrgBaseExample example);

    int deleteByExample(OrgBaseExample example);

    int deleteByPrimaryKey(String id);

    int insert(OrgBase row);

    int insertSelective(OrgBase row);

    List<OrgBase> selectByExampleWithBLOBs(OrgBaseExample example);

    List<OrgBase> selectByExample(OrgBaseExample example);

    OrgBase selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("row") OrgBase row, @Param("example") OrgBaseExample example);

    int updateByExampleWithBLOBs(@Param("row") OrgBase row, @Param("example") OrgBaseExample example);

    int updateByExample(@Param("row") OrgBase row, @Param("example") OrgBaseExample example);

    int updateByPrimaryKeySelective(OrgBase row);

    int updateByPrimaryKeyWithBLOBs(OrgBase row);

    int updateByPrimaryKey(OrgBase row);
}