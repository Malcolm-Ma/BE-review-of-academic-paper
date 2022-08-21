package com.apex.app.domain.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class BiddingPreference implements Serializable {
    private Long id;

    private String submissionId;

    private String userId;

    private Byte preference;

    private String orgId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(String submissionId) {
        this.submissionId = submissionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Byte getPreference() {
        return preference;
    }

    public void setPreference(Byte preference) {
        this.preference = preference;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", submissionId=").append(submissionId);
        sb.append(", userId=").append(userId);
        sb.append(", preference=").append(preference);
        sb.append(", orgId=").append(orgId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}