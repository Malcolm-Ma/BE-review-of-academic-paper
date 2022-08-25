package com.apex.app.controller.vo;

import com.apex.app.domain.bo.OrgInfoBo;
import lombok.Data;

/**
 * Change Org Process Response
 *
 * @author Mingze Ma
 */
@Data
public class ChangeOrgProcessResponse {

    private String orgId;

    private Integer currentReviewProcess;

    private OrgInfoBo orgInfo;

    public ChangeOrgProcessResponse(OrgInfoBo orgInfo) {
        this.orgInfo = orgInfo;
        this.orgId = orgInfo.getId();
        this.currentReviewProcess = orgInfo.getReviewProcess().intValue();
    }
}
