package com.apex.app.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReviewEvaluationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReviewEvaluationExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andReviewIdIsNull() {
            addCriterion("review_id is null");
            return (Criteria) this;
        }

        public Criteria andReviewIdIsNotNull() {
            addCriterion("review_id is not null");
            return (Criteria) this;
        }

        public Criteria andReviewIdEqualTo(String value) {
            addCriterion("review_id =", value, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdNotEqualTo(String value) {
            addCriterion("review_id <>", value, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdGreaterThan(String value) {
            addCriterion("review_id >", value, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdGreaterThanOrEqualTo(String value) {
            addCriterion("review_id >=", value, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdLessThan(String value) {
            addCriterion("review_id <", value, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdLessThanOrEqualTo(String value) {
            addCriterion("review_id <=", value, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdLike(String value) {
            addCriterion("review_id like", value, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdNotLike(String value) {
            addCriterion("review_id not like", value, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdIn(List<String> values) {
            addCriterion("review_id in", values, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdNotIn(List<String> values) {
            addCriterion("review_id not in", values, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdBetween(String value1, String value2) {
            addCriterion("review_id between", value1, value2, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdNotBetween(String value1, String value2) {
            addCriterion("review_id not between", value1, value2, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewDateIsNull() {
            addCriterion("review_date is null");
            return (Criteria) this;
        }

        public Criteria andReviewDateIsNotNull() {
            addCriterion("review_date is not null");
            return (Criteria) this;
        }

        public Criteria andReviewDateEqualTo(Date value) {
            addCriterion("review_date =", value, "reviewDate");
            return (Criteria) this;
        }

        public Criteria andReviewDateNotEqualTo(Date value) {
            addCriterion("review_date <>", value, "reviewDate");
            return (Criteria) this;
        }

        public Criteria andReviewDateGreaterThan(Date value) {
            addCriterion("review_date >", value, "reviewDate");
            return (Criteria) this;
        }

        public Criteria andReviewDateGreaterThanOrEqualTo(Date value) {
            addCriterion("review_date >=", value, "reviewDate");
            return (Criteria) this;
        }

        public Criteria andReviewDateLessThan(Date value) {
            addCriterion("review_date <", value, "reviewDate");
            return (Criteria) this;
        }

        public Criteria andReviewDateLessThanOrEqualTo(Date value) {
            addCriterion("review_date <=", value, "reviewDate");
            return (Criteria) this;
        }

        public Criteria andReviewDateIn(List<Date> values) {
            addCriterion("review_date in", values, "reviewDate");
            return (Criteria) this;
        }

        public Criteria andReviewDateNotIn(List<Date> values) {
            addCriterion("review_date not in", values, "reviewDate");
            return (Criteria) this;
        }

        public Criteria andReviewDateBetween(Date value1, Date value2) {
            addCriterion("review_date between", value1, value2, "reviewDate");
            return (Criteria) this;
        }

        public Criteria andReviewDateNotBetween(Date value1, Date value2) {
            addCriterion("review_date not between", value1, value2, "reviewDate");
            return (Criteria) this;
        }

        public Criteria andReviewIndexIsNull() {
            addCriterion("review_index is null");
            return (Criteria) this;
        }

        public Criteria andReviewIndexIsNotNull() {
            addCriterion("review_index is not null");
            return (Criteria) this;
        }

        public Criteria andReviewIndexEqualTo(Byte value) {
            addCriterion("review_index =", value, "reviewIndex");
            return (Criteria) this;
        }

        public Criteria andReviewIndexNotEqualTo(Byte value) {
            addCriterion("review_index <>", value, "reviewIndex");
            return (Criteria) this;
        }

        public Criteria andReviewIndexGreaterThan(Byte value) {
            addCriterion("review_index >", value, "reviewIndex");
            return (Criteria) this;
        }

        public Criteria andReviewIndexGreaterThanOrEqualTo(Byte value) {
            addCriterion("review_index >=", value, "reviewIndex");
            return (Criteria) this;
        }

        public Criteria andReviewIndexLessThan(Byte value) {
            addCriterion("review_index <", value, "reviewIndex");
            return (Criteria) this;
        }

        public Criteria andReviewIndexLessThanOrEqualTo(Byte value) {
            addCriterion("review_index <=", value, "reviewIndex");
            return (Criteria) this;
        }

        public Criteria andReviewIndexIn(List<Byte> values) {
            addCriterion("review_index in", values, "reviewIndex");
            return (Criteria) this;
        }

        public Criteria andReviewIndexNotIn(List<Byte> values) {
            addCriterion("review_index not in", values, "reviewIndex");
            return (Criteria) this;
        }

        public Criteria andReviewIndexBetween(Byte value1, Byte value2) {
            addCriterion("review_index between", value1, value2, "reviewIndex");
            return (Criteria) this;
        }

        public Criteria andReviewIndexNotBetween(Byte value1, Byte value2) {
            addCriterion("review_index not between", value1, value2, "reviewIndex");
            return (Criteria) this;
        }

        public Criteria andOverallEvaluationIsNull() {
            addCriterion("overall_evaluation is null");
            return (Criteria) this;
        }

        public Criteria andOverallEvaluationIsNotNull() {
            addCriterion("overall_evaluation is not null");
            return (Criteria) this;
        }

        public Criteria andOverallEvaluationEqualTo(Short value) {
            addCriterion("overall_evaluation =", value, "overallEvaluation");
            return (Criteria) this;
        }

        public Criteria andOverallEvaluationNotEqualTo(Short value) {
            addCriterion("overall_evaluation <>", value, "overallEvaluation");
            return (Criteria) this;
        }

        public Criteria andOverallEvaluationGreaterThan(Short value) {
            addCriterion("overall_evaluation >", value, "overallEvaluation");
            return (Criteria) this;
        }

        public Criteria andOverallEvaluationGreaterThanOrEqualTo(Short value) {
            addCriterion("overall_evaluation >=", value, "overallEvaluation");
            return (Criteria) this;
        }

        public Criteria andOverallEvaluationLessThan(Short value) {
            addCriterion("overall_evaluation <", value, "overallEvaluation");
            return (Criteria) this;
        }

        public Criteria andOverallEvaluationLessThanOrEqualTo(Short value) {
            addCriterion("overall_evaluation <=", value, "overallEvaluation");
            return (Criteria) this;
        }

        public Criteria andOverallEvaluationIn(List<Short> values) {
            addCriterion("overall_evaluation in", values, "overallEvaluation");
            return (Criteria) this;
        }

        public Criteria andOverallEvaluationNotIn(List<Short> values) {
            addCriterion("overall_evaluation not in", values, "overallEvaluation");
            return (Criteria) this;
        }

        public Criteria andOverallEvaluationBetween(Short value1, Short value2) {
            addCriterion("overall_evaluation between", value1, value2, "overallEvaluation");
            return (Criteria) this;
        }

        public Criteria andOverallEvaluationNotBetween(Short value1, Short value2) {
            addCriterion("overall_evaluation not between", value1, value2, "overallEvaluation");
            return (Criteria) this;
        }

        public Criteria andConfidenceIsNull() {
            addCriterion("confidence is null");
            return (Criteria) this;
        }

        public Criteria andConfidenceIsNotNull() {
            addCriterion("confidence is not null");
            return (Criteria) this;
        }

        public Criteria andConfidenceEqualTo(Byte value) {
            addCriterion("confidence =", value, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceNotEqualTo(Byte value) {
            addCriterion("confidence <>", value, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceGreaterThan(Byte value) {
            addCriterion("confidence >", value, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceGreaterThanOrEqualTo(Byte value) {
            addCriterion("confidence >=", value, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceLessThan(Byte value) {
            addCriterion("confidence <", value, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceLessThanOrEqualTo(Byte value) {
            addCriterion("confidence <=", value, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceIn(List<Byte> values) {
            addCriterion("confidence in", values, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceNotIn(List<Byte> values) {
            addCriterion("confidence not in", values, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceBetween(Byte value1, Byte value2) {
            addCriterion("confidence between", value1, value2, "confidence");
            return (Criteria) this;
        }

        public Criteria andConfidenceNotBetween(Byte value1, Byte value2) {
            addCriterion("confidence not between", value1, value2, "confidence");
            return (Criteria) this;
        }

        public Criteria andAsShortPaperIsNull() {
            addCriterion("as_short_paper is null");
            return (Criteria) this;
        }

        public Criteria andAsShortPaperIsNotNull() {
            addCriterion("as_short_paper is not null");
            return (Criteria) this;
        }

        public Criteria andAsShortPaperEqualTo(Byte value) {
            addCriterion("as_short_paper =", value, "asShortPaper");
            return (Criteria) this;
        }

        public Criteria andAsShortPaperNotEqualTo(Byte value) {
            addCriterion("as_short_paper <>", value, "asShortPaper");
            return (Criteria) this;
        }

        public Criteria andAsShortPaperGreaterThan(Byte value) {
            addCriterion("as_short_paper >", value, "asShortPaper");
            return (Criteria) this;
        }

        public Criteria andAsShortPaperGreaterThanOrEqualTo(Byte value) {
            addCriterion("as_short_paper >=", value, "asShortPaper");
            return (Criteria) this;
        }

        public Criteria andAsShortPaperLessThan(Byte value) {
            addCriterion("as_short_paper <", value, "asShortPaper");
            return (Criteria) this;
        }

        public Criteria andAsShortPaperLessThanOrEqualTo(Byte value) {
            addCriterion("as_short_paper <=", value, "asShortPaper");
            return (Criteria) this;
        }

        public Criteria andAsShortPaperIn(List<Byte> values) {
            addCriterion("as_short_paper in", values, "asShortPaper");
            return (Criteria) this;
        }

        public Criteria andAsShortPaperNotIn(List<Byte> values) {
            addCriterion("as_short_paper not in", values, "asShortPaper");
            return (Criteria) this;
        }

        public Criteria andAsShortPaperBetween(Byte value1, Byte value2) {
            addCriterion("as_short_paper between", value1, value2, "asShortPaper");
            return (Criteria) this;
        }

        public Criteria andAsShortPaperNotBetween(Byte value1, Byte value2) {
            addCriterion("as_short_paper not between", value1, value2, "asShortPaper");
            return (Criteria) this;
        }

        public Criteria andActiveStatusIsNull() {
            addCriterion("active_status is null");
            return (Criteria) this;
        }

        public Criteria andActiveStatusIsNotNull() {
            addCriterion("active_status is not null");
            return (Criteria) this;
        }

        public Criteria andActiveStatusEqualTo(Byte value) {
            addCriterion("active_status =", value, "activeStatus");
            return (Criteria) this;
        }

        public Criteria andActiveStatusNotEqualTo(Byte value) {
            addCriterion("active_status <>", value, "activeStatus");
            return (Criteria) this;
        }

        public Criteria andActiveStatusGreaterThan(Byte value) {
            addCriterion("active_status >", value, "activeStatus");
            return (Criteria) this;
        }

        public Criteria andActiveStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("active_status >=", value, "activeStatus");
            return (Criteria) this;
        }

        public Criteria andActiveStatusLessThan(Byte value) {
            addCriterion("active_status <", value, "activeStatus");
            return (Criteria) this;
        }

        public Criteria andActiveStatusLessThanOrEqualTo(Byte value) {
            addCriterion("active_status <=", value, "activeStatus");
            return (Criteria) this;
        }

        public Criteria andActiveStatusIn(List<Byte> values) {
            addCriterion("active_status in", values, "activeStatus");
            return (Criteria) this;
        }

        public Criteria andActiveStatusNotIn(List<Byte> values) {
            addCriterion("active_status not in", values, "activeStatus");
            return (Criteria) this;
        }

        public Criteria andActiveStatusBetween(Byte value1, Byte value2) {
            addCriterion("active_status between", value1, value2, "activeStatus");
            return (Criteria) this;
        }

        public Criteria andActiveStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("active_status not between", value1, value2, "activeStatus");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Byte value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Byte value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Byte value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Byte value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Byte value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Byte> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Byte> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Byte value1, Byte value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}