package org.example.weibo.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostLikeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PostLikeExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        public Criteria andLidIsNull() {
            addCriterion("lid is null");
            return (Criteria) this;
        }

        public Criteria andLidIsNotNull() {
            addCriterion("lid is not null");
            return (Criteria) this;
        }

        public Criteria andLidEqualTo(Integer value) {
            addCriterion("lid =", value, "lid");
            return (Criteria) this;
        }

        public Criteria andLidNotEqualTo(Integer value) {
            addCriterion("lid <>", value, "lid");
            return (Criteria) this;
        }

        public Criteria andLidGreaterThan(Integer value) {
            addCriterion("lid >", value, "lid");
            return (Criteria) this;
        }

        public Criteria andLidGreaterThanOrEqualTo(Integer value) {
            addCriterion("lid >=", value, "lid");
            return (Criteria) this;
        }

        public Criteria andLidLessThan(Integer value) {
            addCriterion("lid <", value, "lid");
            return (Criteria) this;
        }

        public Criteria andLidLessThanOrEqualTo(Integer value) {
            addCriterion("lid <=", value, "lid");
            return (Criteria) this;
        }

        public Criteria andLidIn(List<Integer> values) {
            addCriterion("lid in", values, "lid");
            return (Criteria) this;
        }

        public Criteria andLidNotIn(List<Integer> values) {
            addCriterion("lid not in", values, "lid");
            return (Criteria) this;
        }

        public Criteria andLidBetween(Integer value1, Integer value2) {
            addCriterion("lid between", value1, value2, "lid");
            return (Criteria) this;
        }

        public Criteria andLidNotBetween(Integer value1, Integer value2) {
            addCriterion("lid not between", value1, value2, "lid");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Integer value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Integer value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Integer> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUpidIsNull() {
            addCriterion("upid is null");
            return (Criteria) this;
        }

        public Criteria andUpidIsNotNull() {
            addCriterion("upid is not null");
            return (Criteria) this;
        }

        public Criteria andUpidEqualTo(String value) {
            addCriterion("upid =", value, "upid");
            return (Criteria) this;
        }

        public Criteria andUpidNotEqualTo(String value) {
            addCriterion("upid <>", value, "upid");
            return (Criteria) this;
        }

        public Criteria andUpidGreaterThan(String value) {
            addCriterion("upid >", value, "upid");
            return (Criteria) this;
        }

        public Criteria andUpidGreaterThanOrEqualTo(String value) {
            addCriterion("upid >=", value, "upid");
            return (Criteria) this;
        }

        public Criteria andUpidLessThan(String value) {
            addCriterion("upid <", value, "upid");
            return (Criteria) this;
        }

        public Criteria andUpidLessThanOrEqualTo(String value) {
            addCriterion("upid <=", value, "upid");
            return (Criteria) this;
        }

        public Criteria andUpidLike(String value) {
            addCriterion("upid like", value, "upid");
            return (Criteria) this;
        }

        public Criteria andUpidNotLike(String value) {
            addCriterion("upid not like", value, "upid");
            return (Criteria) this;
        }

        public Criteria andUpidIn(List<String> values) {
            addCriterion("upid in", values, "upid");
            return (Criteria) this;
        }

        public Criteria andUpidNotIn(List<String> values) {
            addCriterion("upid not in", values, "upid");
            return (Criteria) this;
        }

        public Criteria andUpidBetween(String value1, String value2) {
            addCriterion("upid between", value1, value2, "upid");
            return (Criteria) this;
        }

        public Criteria andUpidNotBetween(String value1, String value2) {
            addCriterion("upid not between", value1, value2, "upid");
            return (Criteria) this;
        }

        public Criteria andLikeTimeIsNull() {
            addCriterion("like_time is null");
            return (Criteria) this;
        }

        public Criteria andLikeTimeIsNotNull() {
            addCriterion("like_time is not null");
            return (Criteria) this;
        }

        public Criteria andLikeTimeEqualTo(Date value) {
            addCriterion("like_time =", value, "likeTime");
            return (Criteria) this;
        }

        public Criteria andLikeTimeNotEqualTo(Date value) {
            addCriterion("like_time <>", value, "likeTime");
            return (Criteria) this;
        }

        public Criteria andLikeTimeGreaterThan(Date value) {
            addCriterion("like_time >", value, "likeTime");
            return (Criteria) this;
        }

        public Criteria andLikeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("like_time >=", value, "likeTime");
            return (Criteria) this;
        }

        public Criteria andLikeTimeLessThan(Date value) {
            addCriterion("like_time <", value, "likeTime");
            return (Criteria) this;
        }

        public Criteria andLikeTimeLessThanOrEqualTo(Date value) {
            addCriterion("like_time <=", value, "likeTime");
            return (Criteria) this;
        }

        public Criteria andLikeTimeIn(List<Date> values) {
            addCriterion("like_time in", values, "likeTime");
            return (Criteria) this;
        }

        public Criteria andLikeTimeNotIn(List<Date> values) {
            addCriterion("like_time not in", values, "likeTime");
            return (Criteria) this;
        }

        public Criteria andLikeTimeBetween(Date value1, Date value2) {
            addCriterion("like_time between", value1, value2, "likeTime");
            return (Criteria) this;
        }

        public Criteria andLikeTimeNotBetween(Date value1, Date value2) {
            addCriterion("like_time not between", value1, value2, "likeTime");
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