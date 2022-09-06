package com.apex.app.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReviewTaskOverallExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReviewTaskOverallExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNull() {
            addCriterion("org_id is null");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNotNull() {
            addCriterion("org_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrgIdEqualTo(String value) {
            addCriterion("org_id =", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotEqualTo(String value) {
            addCriterion("org_id <>", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThan(String value) {
            addCriterion("org_id >", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("org_id >=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThan(String value) {
            addCriterion("org_id <", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThanOrEqualTo(String value) {
            addCriterion("org_id <=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLike(String value) {
            addCriterion("org_id like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotLike(String value) {
            addCriterion("org_id not like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIn(List<String> values) {
            addCriterion("org_id in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotIn(List<String> values) {
            addCriterion("org_id not in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdBetween(String value1, String value2) {
            addCriterion("org_id between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotBetween(String value1, String value2) {
            addCriterion("org_id not between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdIsNull() {
            addCriterion("submission_id is null");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdIsNotNull() {
            addCriterion("submission_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdEqualTo(String value) {
            addCriterion("submission_id =", value, "submissionId");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdNotEqualTo(String value) {
            addCriterion("submission_id <>", value, "submissionId");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdGreaterThan(String value) {
            addCriterion("submission_id >", value, "submissionId");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdGreaterThanOrEqualTo(String value) {
            addCriterion("submission_id >=", value, "submissionId");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdLessThan(String value) {
            addCriterion("submission_id <", value, "submissionId");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdLessThanOrEqualTo(String value) {
            addCriterion("submission_id <=", value, "submissionId");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdLike(String value) {
            addCriterion("submission_id like", value, "submissionId");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdNotLike(String value) {
            addCriterion("submission_id not like", value, "submissionId");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdIn(List<String> values) {
            addCriterion("submission_id in", values, "submissionId");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdNotIn(List<String> values) {
            addCriterion("submission_id not in", values, "submissionId");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdBetween(String value1, String value2) {
            addCriterion("submission_id between", value1, value2, "submissionId");
            return (Criteria) this;
        }

        public Criteria andSubmissionIdNotBetween(String value1, String value2) {
            addCriterion("submission_id not between", value1, value2, "submissionId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andDeadlineIsNull() {
            addCriterion("deadline is null");
            return (Criteria) this;
        }

        public Criteria andDeadlineIsNotNull() {
            addCriterion("deadline is not null");
            return (Criteria) this;
        }

        public Criteria andDeadlineEqualTo(Date value) {
            addCriterion("deadline =", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineNotEqualTo(Date value) {
            addCriterion("deadline <>", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineGreaterThan(Date value) {
            addCriterion("deadline >", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineGreaterThanOrEqualTo(Date value) {
            addCriterion("deadline >=", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineLessThan(Date value) {
            addCriterion("deadline <", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineLessThanOrEqualTo(Date value) {
            addCriterion("deadline <=", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineIn(List<Date> values) {
            addCriterion("deadline in", values, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineNotIn(List<Date> values) {
            addCriterion("deadline not in", values, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineBetween(Date value1, Date value2) {
            addCriterion("deadline between", value1, value2, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineNotBetween(Date value1, Date value2) {
            addCriterion("deadline not between", value1, value2, "deadline");
            return (Criteria) this;
        }

        public Criteria andDecisionIsNull() {
            addCriterion("decision is null");
            return (Criteria) this;
        }

        public Criteria andDecisionIsNotNull() {
            addCriterion("decision is not null");
            return (Criteria) this;
        }

        public Criteria andDecisionEqualTo(Byte value) {
            addCriterion("decision =", value, "decision");
            return (Criteria) this;
        }

        public Criteria andDecisionNotEqualTo(Byte value) {
            addCriterion("decision <>", value, "decision");
            return (Criteria) this;
        }

        public Criteria andDecisionGreaterThan(Byte value) {
            addCriterion("decision >", value, "decision");
            return (Criteria) this;
        }

        public Criteria andDecisionGreaterThanOrEqualTo(Byte value) {
            addCriterion("decision >=", value, "decision");
            return (Criteria) this;
        }

        public Criteria andDecisionLessThan(Byte value) {
            addCriterion("decision <", value, "decision");
            return (Criteria) this;
        }

        public Criteria andDecisionLessThanOrEqualTo(Byte value) {
            addCriterion("decision <=", value, "decision");
            return (Criteria) this;
        }

        public Criteria andDecisionIn(List<Byte> values) {
            addCriterion("decision in", values, "decision");
            return (Criteria) this;
        }

        public Criteria andDecisionNotIn(List<Byte> values) {
            addCriterion("decision not in", values, "decision");
            return (Criteria) this;
        }

        public Criteria andDecisionBetween(Byte value1, Byte value2) {
            addCriterion("decision between", value1, value2, "decision");
            return (Criteria) this;
        }

        public Criteria andDecisionNotBetween(Byte value1, Byte value2) {
            addCriterion("decision not between", value1, value2, "decision");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("created_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("created_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(Date value) {
            addCriterion("created_time =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(Date value) {
            addCriterion("created_time <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(Date value) {
            addCriterion("created_time >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("created_time >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(Date value) {
            addCriterion("created_time <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("created_time <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<Date> values) {
            addCriterion("created_time in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<Date> values) {
            addCriterion("created_time not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(Date value1, Date value2) {
            addCriterion("created_time between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("created_time not between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andBlindModeIsNull() {
            addCriterion("blind_mode is null");
            return (Criteria) this;
        }

        public Criteria andBlindModeIsNotNull() {
            addCriterion("blind_mode is not null");
            return (Criteria) this;
        }

        public Criteria andBlindModeEqualTo(Boolean value) {
            addCriterion("blind_mode =", value, "blindMode");
            return (Criteria) this;
        }

        public Criteria andBlindModeNotEqualTo(Boolean value) {
            addCriterion("blind_mode <>", value, "blindMode");
            return (Criteria) this;
        }

        public Criteria andBlindModeGreaterThan(Boolean value) {
            addCriterion("blind_mode >", value, "blindMode");
            return (Criteria) this;
        }

        public Criteria andBlindModeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("blind_mode >=", value, "blindMode");
            return (Criteria) this;
        }

        public Criteria andBlindModeLessThan(Boolean value) {
            addCriterion("blind_mode <", value, "blindMode");
            return (Criteria) this;
        }

        public Criteria andBlindModeLessThanOrEqualTo(Boolean value) {
            addCriterion("blind_mode <=", value, "blindMode");
            return (Criteria) this;
        }

        public Criteria andBlindModeIn(List<Boolean> values) {
            addCriterion("blind_mode in", values, "blindMode");
            return (Criteria) this;
        }

        public Criteria andBlindModeNotIn(List<Boolean> values) {
            addCriterion("blind_mode not in", values, "blindMode");
            return (Criteria) this;
        }

        public Criteria andBlindModeBetween(Boolean value1, Boolean value2) {
            addCriterion("blind_mode between", value1, value2, "blindMode");
            return (Criteria) this;
        }

        public Criteria andBlindModeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("blind_mode not between", value1, value2, "blindMode");
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