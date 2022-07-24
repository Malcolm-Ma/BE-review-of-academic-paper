package com.apex.app.domain.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class UserReviewEvaluation implements Serializable {
    private Long id;

    private String userId;

    private String taskId;

    private Date reviewDate;

    private Byte index;

    private Short totalScore;

    private Byte confidence;

    @ApiModelProperty(value = "Accept as a short paper: 0->no; 1->yes")
    private Byte asShortPaper;

    @ApiModelProperty(value = "status of review: 0->superseded by others; 1->active;")
    private Byte status;

    private String evaluationContent;

    private String confidenceRemark;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Byte getIndex() {
        return index;
    }

    public void setIndex(Byte index) {
        this.index = index;
    }

    public Short getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Short totalScore) {
        this.totalScore = totalScore;
    }

    public Byte getConfidence() {
        return confidence;
    }

    public void setConfidence(Byte confidence) {
        this.confidence = confidence;
    }

    public Byte getAsShortPaper() {
        return asShortPaper;
    }

    public void setAsShortPaper(Byte asShortPaper) {
        this.asShortPaper = asShortPaper;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getEvaluationContent() {
        return evaluationContent;
    }

    public void setEvaluationContent(String evaluationContent) {
        this.evaluationContent = evaluationContent;
    }

    public String getConfidenceRemark() {
        return confidenceRemark;
    }

    public void setConfidenceRemark(String confidenceRemark) {
        this.confidenceRemark = confidenceRemark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", taskId=").append(taskId);
        sb.append(", reviewDate=").append(reviewDate);
        sb.append(", index=").append(index);
        sb.append(", totalScore=").append(totalScore);
        sb.append(", confidence=").append(confidence);
        sb.append(", asShortPaper=").append(asShortPaper);
        sb.append(", status=").append(status);
        sb.append(", evaluationContent=").append(evaluationContent);
        sb.append(", confidenceRemark=").append(confidenceRemark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}