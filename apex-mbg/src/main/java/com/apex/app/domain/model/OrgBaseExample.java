package com.apex.app.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrgBaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrgBaseExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
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

        public Criteria andActiveStatusEqualTo(Integer value) {
            addCriterion("active_status =", value, "activeStatus");
            return (Criteria) this;
        }

        public Criteria andActiveStatusNotEqualTo(Integer value) {
            addCriterion("active_status <>", value, "activeStatus");
            return (Criteria) this;
        }

        public Criteria andActiveStatusGreaterThan(Integer value) {
            addCriterion("active_status >", value, "activeStatus");
            return (Criteria) this;
        }

        public Criteria andActiveStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("active_status >=", value, "activeStatus");
            return (Criteria) this;
        }

        public Criteria andActiveStatusLessThan(Integer value) {
            addCriterion("active_status <", value, "activeStatus");
            return (Criteria) this;
        }

        public Criteria andActiveStatusLessThanOrEqualTo(Integer value) {
            addCriterion("active_status <=", value, "activeStatus");
            return (Criteria) this;
        }

        public Criteria andActiveStatusIn(List<Integer> values) {
            addCriterion("active_status in", values, "activeStatus");
            return (Criteria) this;
        }

        public Criteria andActiveStatusNotIn(List<Integer> values) {
            addCriterion("active_status not in", values, "activeStatus");
            return (Criteria) this;
        }

        public Criteria andActiveStatusBetween(Integer value1, Integer value2) {
            addCriterion("active_status between", value1, value2, "activeStatus");
            return (Criteria) this;
        }

        public Criteria andActiveStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("active_status not between", value1, value2, "activeStatus");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andSubmissionDdlIsNull() {
            addCriterion("submission_ddl is null");
            return (Criteria) this;
        }

        public Criteria andSubmissionDdlIsNotNull() {
            addCriterion("submission_ddl is not null");
            return (Criteria) this;
        }

        public Criteria andSubmissionDdlEqualTo(Date value) {
            addCriterion("submission_ddl =", value, "submissionDdl");
            return (Criteria) this;
        }

        public Criteria andSubmissionDdlNotEqualTo(Date value) {
            addCriterion("submission_ddl <>", value, "submissionDdl");
            return (Criteria) this;
        }

        public Criteria andSubmissionDdlGreaterThan(Date value) {
            addCriterion("submission_ddl >", value, "submissionDdl");
            return (Criteria) this;
        }

        public Criteria andSubmissionDdlGreaterThanOrEqualTo(Date value) {
            addCriterion("submission_ddl >=", value, "submissionDdl");
            return (Criteria) this;
        }

        public Criteria andSubmissionDdlLessThan(Date value) {
            addCriterion("submission_ddl <", value, "submissionDdl");
            return (Criteria) this;
        }

        public Criteria andSubmissionDdlLessThanOrEqualTo(Date value) {
            addCriterion("submission_ddl <=", value, "submissionDdl");
            return (Criteria) this;
        }

        public Criteria andSubmissionDdlIn(List<Date> values) {
            addCriterion("submission_ddl in", values, "submissionDdl");
            return (Criteria) this;
        }

        public Criteria andSubmissionDdlNotIn(List<Date> values) {
            addCriterion("submission_ddl not in", values, "submissionDdl");
            return (Criteria) this;
        }

        public Criteria andSubmissionDdlBetween(Date value1, Date value2) {
            addCriterion("submission_ddl between", value1, value2, "submissionDdl");
            return (Criteria) this;
        }

        public Criteria andSubmissionDdlNotBetween(Date value1, Date value2) {
            addCriterion("submission_ddl not between", value1, value2, "submissionDdl");
            return (Criteria) this;
        }

        public Criteria andReviewProcessIsNull() {
            addCriterion("review_process is null");
            return (Criteria) this;
        }

        public Criteria andReviewProcessIsNotNull() {
            addCriterion("review_process is not null");
            return (Criteria) this;
        }

        public Criteria andReviewProcessEqualTo(Byte value) {
            addCriterion("review_process =", value, "reviewProcess");
            return (Criteria) this;
        }

        public Criteria andReviewProcessNotEqualTo(Byte value) {
            addCriterion("review_process <>", value, "reviewProcess");
            return (Criteria) this;
        }

        public Criteria andReviewProcessGreaterThan(Byte value) {
            addCriterion("review_process >", value, "reviewProcess");
            return (Criteria) this;
        }

        public Criteria andReviewProcessGreaterThanOrEqualTo(Byte value) {
            addCriterion("review_process >=", value, "reviewProcess");
            return (Criteria) this;
        }

        public Criteria andReviewProcessLessThan(Byte value) {
            addCriterion("review_process <", value, "reviewProcess");
            return (Criteria) this;
        }

        public Criteria andReviewProcessLessThanOrEqualTo(Byte value) {
            addCriterion("review_process <=", value, "reviewProcess");
            return (Criteria) this;
        }

        public Criteria andReviewProcessIn(List<Byte> values) {
            addCriterion("review_process in", values, "reviewProcess");
            return (Criteria) this;
        }

        public Criteria andReviewProcessNotIn(List<Byte> values) {
            addCriterion("review_process not in", values, "reviewProcess");
            return (Criteria) this;
        }

        public Criteria andReviewProcessBetween(Byte value1, Byte value2) {
            addCriterion("review_process between", value1, value2, "reviewProcess");
            return (Criteria) this;
        }

        public Criteria andReviewProcessNotBetween(Byte value1, Byte value2) {
            addCriterion("review_process not between", value1, value2, "reviewProcess");
            return (Criteria) this;
        }

        public Criteria andBiddingDdlIsNull() {
            addCriterion("bidding_ddl is null");
            return (Criteria) this;
        }

        public Criteria andBiddingDdlIsNotNull() {
            addCriterion("bidding_ddl is not null");
            return (Criteria) this;
        }

        public Criteria andBiddingDdlEqualTo(Date value) {
            addCriterion("bidding_ddl =", value, "biddingDdl");
            return (Criteria) this;
        }

        public Criteria andBiddingDdlNotEqualTo(Date value) {
            addCriterion("bidding_ddl <>", value, "biddingDdl");
            return (Criteria) this;
        }

        public Criteria andBiddingDdlGreaterThan(Date value) {
            addCriterion("bidding_ddl >", value, "biddingDdl");
            return (Criteria) this;
        }

        public Criteria andBiddingDdlGreaterThanOrEqualTo(Date value) {
            addCriterion("bidding_ddl >=", value, "biddingDdl");
            return (Criteria) this;
        }

        public Criteria andBiddingDdlLessThan(Date value) {
            addCriterion("bidding_ddl <", value, "biddingDdl");
            return (Criteria) this;
        }

        public Criteria andBiddingDdlLessThanOrEqualTo(Date value) {
            addCriterion("bidding_ddl <=", value, "biddingDdl");
            return (Criteria) this;
        }

        public Criteria andBiddingDdlIn(List<Date> values) {
            addCriterion("bidding_ddl in", values, "biddingDdl");
            return (Criteria) this;
        }

        public Criteria andBiddingDdlNotIn(List<Date> values) {
            addCriterion("bidding_ddl not in", values, "biddingDdl");
            return (Criteria) this;
        }

        public Criteria andBiddingDdlBetween(Date value1, Date value2) {
            addCriterion("bidding_ddl between", value1, value2, "biddingDdl");
            return (Criteria) this;
        }

        public Criteria andBiddingDdlNotBetween(Date value1, Date value2) {
            addCriterion("bidding_ddl not between", value1, value2, "biddingDdl");
            return (Criteria) this;
        }

        public Criteria andReviewDdlIsNull() {
            addCriterion("review_ddl is null");
            return (Criteria) this;
        }

        public Criteria andReviewDdlIsNotNull() {
            addCriterion("review_ddl is not null");
            return (Criteria) this;
        }

        public Criteria andReviewDdlEqualTo(Date value) {
            addCriterion("review_ddl =", value, "reviewDdl");
            return (Criteria) this;
        }

        public Criteria andReviewDdlNotEqualTo(Date value) {
            addCriterion("review_ddl <>", value, "reviewDdl");
            return (Criteria) this;
        }

        public Criteria andReviewDdlGreaterThan(Date value) {
            addCriterion("review_ddl >", value, "reviewDdl");
            return (Criteria) this;
        }

        public Criteria andReviewDdlGreaterThanOrEqualTo(Date value) {
            addCriterion("review_ddl >=", value, "reviewDdl");
            return (Criteria) this;
        }

        public Criteria andReviewDdlLessThan(Date value) {
            addCriterion("review_ddl <", value, "reviewDdl");
            return (Criteria) this;
        }

        public Criteria andReviewDdlLessThanOrEqualTo(Date value) {
            addCriterion("review_ddl <=", value, "reviewDdl");
            return (Criteria) this;
        }

        public Criteria andReviewDdlIn(List<Date> values) {
            addCriterion("review_ddl in", values, "reviewDdl");
            return (Criteria) this;
        }

        public Criteria andReviewDdlNotIn(List<Date> values) {
            addCriterion("review_ddl not in", values, "reviewDdl");
            return (Criteria) this;
        }

        public Criteria andReviewDdlBetween(Date value1, Date value2) {
            addCriterion("review_ddl between", value1, value2, "reviewDdl");
            return (Criteria) this;
        }

        public Criteria andReviewDdlNotBetween(Date value1, Date value2) {
            addCriterion("review_ddl not between", value1, value2, "reviewDdl");
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