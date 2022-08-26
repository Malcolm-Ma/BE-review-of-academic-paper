package com.apex.app.dao;

import com.apex.app.domain.bo.UserSearchResultBo;
import com.apex.app.domain.model.UserBase;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * User Dao
 *
 * @author Mingze Ma
 */
@Repository
public interface UserDao {

    /**
     * Search user info by query string
     *
     * @param query query string
     * @return User Search Result Bo
     */
    List<UserSearchResultBo> searchUser(@Param("query") String query, @Param("limit") int limit);

    @MapKey("id")
    Map<String, UserBase> getUserMapByOrgId(@Param("org_id") String orgId);
}
