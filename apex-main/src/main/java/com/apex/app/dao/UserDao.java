package com.apex.app.dao;

import com.apex.app.domain.bo.UserSearchResultBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

}
