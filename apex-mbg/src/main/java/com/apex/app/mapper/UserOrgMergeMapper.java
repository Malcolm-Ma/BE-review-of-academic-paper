package com.apex.app.mapper;

import com.apex.app.domain.model.UserOrgMerge;
import com.apex.app.domain.model.UserOrgMergeExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserOrgMergeMapper {
    long countByExample(UserOrgMergeExample example);

    int deleteByExample(UserOrgMergeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserOrgMerge row);

    int insertSelective(UserOrgMerge row);

    List<UserOrgMerge> selectByExample(UserOrgMergeExample example);

    UserOrgMerge selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") UserOrgMerge row, @Param("example") UserOrgMergeExample example);

    int updateByExample(@Param("row") UserOrgMerge row, @Param("example") UserOrgMergeExample example);

    int updateByPrimaryKeySelective(UserOrgMerge row);

    int updateByPrimaryKey(UserOrgMerge row);
}