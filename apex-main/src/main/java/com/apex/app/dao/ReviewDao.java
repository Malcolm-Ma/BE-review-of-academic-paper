package com.apex.app.dao;

import com.apex.app.domain.model.PaperAllocation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mingze Ma
 */
@Repository
public interface ReviewDao {

    Integer getSubmissionCount(@Param("org_id") String orgId);

    List<String> getSubmissionIdList(@Param("org_id") String orgId);

    void insertPaperAllocation(List<PaperAllocation> allocationList);

    void deleteAllocationByOrgId(@Param("org_id") String orgId);

}
