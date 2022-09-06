package com.apex.app.domain.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class ReviewTaskOverall implements Serializable {
    private String id;

    @ApiModelProperty(value = "attribution of tasks to the organization.")
    private String orgId;

    @ApiModelProperty(value = "Paper id which need to be reviewed")
    private String submissionId;

    @ApiModelProperty(value = "reviewing status: 0-> finished; 1 -> ongoing; 2 -> preparing")
    private Byte status;

    private Date deadline;

    private Byte decision;

    private Date createdTime;

    private Boolean blindMode;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(String submissionId) {
        this.submissionId = submissionId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Byte getDecision() {
        return decision;
    }

    public void setDecision(Byte decision) {
        this.decision = decision;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Boolean getBlindMode() {
        return blindMode;
    }

    public void setBlindMode(Boolean blindMode) {
        this.blindMode = blindMode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orgId=").append(orgId);
        sb.append(", submissionId=").append(submissionId);
        sb.append(", status=").append(status);
        sb.append(", deadline=").append(deadline);
        sb.append(", decision=").append(decision);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", blindMode=").append(blindMode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}