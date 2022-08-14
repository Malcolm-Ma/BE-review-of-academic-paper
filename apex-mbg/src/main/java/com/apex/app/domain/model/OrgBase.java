package com.apex.app.domain.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class OrgBase implements Serializable {
    private String id;

    private String name;

    private Date createTime;

    private Integer activeStatus;

    private String email;

    private Date submissionDdl;

    @ApiModelProperty(value = "0 -> preparing; 1 -> collecting; 2 -> bidding; 3 -> reviewing; 4 -> finished")
    private Byte reviewProcess;

    private String description;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Integer activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getSubmissionDdl() {
        return submissionDdl;
    }

    public void setSubmissionDdl(Date submissionDdl) {
        this.submissionDdl = submissionDdl;
    }

    public Byte getReviewProcess() {
        return reviewProcess;
    }

    public void setReviewProcess(Byte reviewProcess) {
        this.reviewProcess = reviewProcess;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", createTime=").append(createTime);
        sb.append(", activeStatus=").append(activeStatus);
        sb.append(", email=").append(email);
        sb.append(", submissionDdl=").append(submissionDdl);
        sb.append(", reviewProcess=").append(reviewProcess);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}