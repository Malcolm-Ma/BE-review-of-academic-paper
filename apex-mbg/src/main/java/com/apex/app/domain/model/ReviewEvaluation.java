package com.apex.app.domain.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class ReviewEvaluation implements Serializable {
    private Long id;

    private String userId;

    private String reviewId;

    private Date reviewDate;

    private Byte reviewIndex;

    private Short overallEvaluation;

    private Byte confidence;

    @ApiModelProperty(value = "Accept as a short paper: 0->no; 1->yes")
    private Byte asShortPaper;

    @ApiModelProperty(value = "status of review: 0->superseded by others; 1->active;")
    private Byte activeStatus;

    @ApiModelProperty(value = "evaluation type: 0->comment, 1->review")
    private Byte type;

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

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Byte getReviewIndex() {
        return reviewIndex;
    }

    public void setReviewIndex(Byte reviewIndex) {
        this.reviewIndex = reviewIndex;
    }

    public Short getOverallEvaluation() {
        return overallEvaluation;
    }

    public void setOverallEvaluation(Short overallEvaluation) {
        this.overallEvaluation = overallEvaluation;
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

    public Byte getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Byte activeStatus) {
        this.activeStatus = activeStatus;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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
        sb.append(", reviewId=").append(reviewId);
        sb.append(", reviewDate=").append(reviewDate);
        sb.append(", reviewIndex=").append(reviewIndex);
        sb.append(", overallEvaluation=").append(overallEvaluation);
        sb.append(", confidence=").append(confidence);
        sb.append(", asShortPaper=").append(asShortPaper);
        sb.append(", activeStatus=").append(activeStatus);
        sb.append(", type=").append(type);
        sb.append(", evaluationContent=").append(evaluationContent);
        sb.append(", confidenceRemark=").append(confidenceRemark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}