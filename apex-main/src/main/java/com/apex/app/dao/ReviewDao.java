package com.apex.app.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Mingze Ma
 */
@Repository
public interface ReviewDao {

    Integer getSubmissionCount(@Param("org_id") String orgId);

}
