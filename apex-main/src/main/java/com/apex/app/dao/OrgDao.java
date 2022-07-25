package com.apex.app.dao;

import com.apex.app.domain.bo.OrgListByUserBo;
import com.apex.app.domain.bo.OrgMemberBo;
import com.apex.app.domain.model.OrgBase;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * @author Mingze Ma
 */
@Repository
public interface OrgDao {

    List<OrgMemberBo> getOrgMemberList(@Param("org_id") String orgId);

    List<OrgListByUserBo> getOrgListByUserId(@Param("user_id") String uid);

}
