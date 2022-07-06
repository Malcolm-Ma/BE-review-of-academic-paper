package com.apex.app.mapper;

import com.apex.app.model.UserBase;
import com.apex.app.model.UserBaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserBaseMapper {
    long countByExample(UserBaseExample example);

    int deleteByExample(UserBaseExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserBase row);

    int insertSelective(UserBase row);

    List<UserBase> selectByExample(UserBaseExample example);

    UserBase selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("row") UserBase row, @Param("example") UserBaseExample example);

    int updateByExample(@Param("row") UserBase row, @Param("example") UserBaseExample example);

    int updateByPrimaryKeySelective(UserBase row);

    int updateByPrimaryKey(UserBase row);
}