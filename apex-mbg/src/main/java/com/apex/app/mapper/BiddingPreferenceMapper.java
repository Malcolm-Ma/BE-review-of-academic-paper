package com.apex.app.mapper;

import com.apex.app.domain.model.BiddingPreference;
import com.apex.app.domain.model.BiddingPreferenceExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BiddingPreferenceMapper {
    long countByExample(BiddingPreferenceExample example);

    int deleteByExample(BiddingPreferenceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BiddingPreference row);

    int insertSelective(BiddingPreference row);

    List<BiddingPreference> selectByExample(BiddingPreferenceExample example);

    BiddingPreference selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") BiddingPreference row, @Param("example") BiddingPreferenceExample example);

    int updateByExample(@Param("row") BiddingPreference row, @Param("example") BiddingPreferenceExample example);

    int updateByPrimaryKeySelective(BiddingPreference row);

    int updateByPrimaryKey(BiddingPreference row);
}