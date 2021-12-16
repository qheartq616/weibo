package org.example.weibo.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LastExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LastExample() {
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

        public Criteria andLastIdIsNull() {
            addCriterion("last_id is null");
            return (Criteria) this;
        }

        public Criteria andLastIdIsNotNull() {
            addCriterion("last_id is not null");
            return (Criteria) this;
        }

        public Criteria andLastIdEqualTo(Integer value) {
            addCriterion("last_id =", value, "lastId");
            return (Criteria) this;
        }

        public Criteria andLastIdNotEqualTo(Integer value) {
            addCriterion("last_id <>", value, "lastId");
            return (Criteria) this;
        }

        public Criteria andLastIdGreaterThan(Integer value) {
            addCriterion("last_id >", value, "lastId");
            return (Criteria) this;
        }

        public Criteria andLastIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("last_id >=", value, "lastId");
            return (Criteria) this;
        }

        public Criteria andLastIdLessThan(Integer value) {
            addCriterion("last_id <", value, "lastId");
            return (Criteria) this;
        }

        public Criteria andLastIdLessThanOrEqualTo(Integer value) {
            addCriterion("last_id <=", value, "lastId");
            return (Criteria) this;
        }

        public Criteria andLastIdIn(List<Integer> values) {
            addCriterion("last_id in", values, "lastId");
            return (Criteria) this;
        }

        public Criteria andLastIdNotIn(List<Integer> values) {
            addCriterion("last_id not in", values, "lastId");
            return (Criteria) this;
        }

        public Criteria andLastIdBetween(Integer value1, Integer value2) {
            addCriterion("last_id between", value1, value2, "lastId");
            return (Criteria) this;
        }

        public Criteria andLastIdNotBetween(Integer value1, Integer value2) {
            addCriterion("last_id not between", value1, value2, "lastId");
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

        public Criteria andLattimeIsNull() {
            addCriterion("lattime is null");
            return (Criteria) this;
        }

        public Criteria andLattimeIsNotNull() {
            addCriterion("lattime is not null");
            return (Criteria) this;
        }

        public Criteria andLattimeEqualTo(Date value) {
            addCriterion("lattime =", value, "lattime");
            return (Criteria) this;
        }

        public Criteria andLattimeNotEqualTo(Date value) {
            addCriterion("lattime <>", value, "lattime");
            return (Criteria) this;
        }

        public Criteria andLattimeGreaterThan(Date value) {
            addCriterion("lattime >", value, "lattime");
            return (Criteria) this;
        }

        public Criteria andLattimeGreaterThanOrEqualTo(Date value) {
            addCriterion("lattime >=", value, "lattime");
            return (Criteria) this;
        }

        public Criteria andLattimeLessThan(Date value) {
            addCriterion("lattime <", value, "lattime");
            return (Criteria) this;
        }

        public Criteria andLattimeLessThanOrEqualTo(Date value) {
            addCriterion("lattime <=", value, "lattime");
            return (Criteria) this;
        }

        public Criteria andLattimeIn(List<Date> values) {
            addCriterion("lattime in", values, "lattime");
            return (Criteria) this;
        }

        public Criteria andLattimeNotIn(List<Date> values) {
            addCriterion("lattime not in", values, "lattime");
            return (Criteria) this;
        }

        public Criteria andLattimeBetween(Date value1, Date value2) {
            addCriterion("lattime between", value1, value2, "lattime");
            return (Criteria) this;
        }

        public Criteria andLattimeNotBetween(Date value1, Date value2) {
            addCriterion("lattime not between", value1, value2, "lattime");
            return (Criteria) this;
        }

        public Criteria andLftimeIsNull() {
            addCriterion("lftime is null");
            return (Criteria) this;
        }

        public Criteria andLftimeIsNotNull() {
            addCriterion("lftime is not null");
            return (Criteria) this;
        }

        public Criteria andLftimeEqualTo(Date value) {
            addCriterion("lftime =", value, "lftime");
            return (Criteria) this;
        }

        public Criteria andLftimeNotEqualTo(Date value) {
            addCriterion("lftime <>", value, "lftime");
            return (Criteria) this;
        }

        public Criteria andLftimeGreaterThan(Date value) {
            addCriterion("lftime >", value, "lftime");
            return (Criteria) this;
        }

        public Criteria andLftimeGreaterThanOrEqualTo(Date value) {
            addCriterion("lftime >=", value, "lftime");
            return (Criteria) this;
        }

        public Criteria andLftimeLessThan(Date value) {
            addCriterion("lftime <", value, "lftime");
            return (Criteria) this;
        }

        public Criteria andLftimeLessThanOrEqualTo(Date value) {
            addCriterion("lftime <=", value, "lftime");
            return (Criteria) this;
        }

        public Criteria andLftimeIn(List<Date> values) {
            addCriterion("lftime in", values, "lftime");
            return (Criteria) this;
        }

        public Criteria andLftimeNotIn(List<Date> values) {
            addCriterion("lftime not in", values, "lftime");
            return (Criteria) this;
        }

        public Criteria andLftimeBetween(Date value1, Date value2) {
            addCriterion("lftime between", value1, value2, "lftime");
            return (Criteria) this;
        }

        public Criteria andLftimeNotBetween(Date value1, Date value2) {
            addCriterion("lftime not between", value1, value2, "lftime");
            return (Criteria) this;
        }

        public Criteria andLctimeIsNull() {
            addCriterion("lctime is null");
            return (Criteria) this;
        }

        public Criteria andLctimeIsNotNull() {
            addCriterion("lctime is not null");
            return (Criteria) this;
        }

        public Criteria andLctimeEqualTo(Date value) {
            addCriterion("lctime =", value, "lctime");
            return (Criteria) this;
        }

        public Criteria andLctimeNotEqualTo(Date value) {
            addCriterion("lctime <>", value, "lctime");
            return (Criteria) this;
        }

        public Criteria andLctimeGreaterThan(Date value) {
            addCriterion("lctime >", value, "lctime");
            return (Criteria) this;
        }

        public Criteria andLctimeGreaterThanOrEqualTo(Date value) {
            addCriterion("lctime >=", value, "lctime");
            return (Criteria) this;
        }

        public Criteria andLctimeLessThan(Date value) {
            addCriterion("lctime <", value, "lctime");
            return (Criteria) this;
        }

        public Criteria andLctimeLessThanOrEqualTo(Date value) {
            addCriterion("lctime <=", value, "lctime");
            return (Criteria) this;
        }

        public Criteria andLctimeIn(List<Date> values) {
            addCriterion("lctime in", values, "lctime");
            return (Criteria) this;
        }

        public Criteria andLctimeNotIn(List<Date> values) {
            addCriterion("lctime not in", values, "lctime");
            return (Criteria) this;
        }

        public Criteria andLctimeBetween(Date value1, Date value2) {
            addCriterion("lctime between", value1, value2, "lctime");
            return (Criteria) this;
        }

        public Criteria andLctimeNotBetween(Date value1, Date value2) {
            addCriterion("lctime not between", value1, value2, "lctime");
            return (Criteria) this;
        }

        public Criteria andLltimeIsNull() {
            addCriterion("lltime is null");
            return (Criteria) this;
        }

        public Criteria andLltimeIsNotNull() {
            addCriterion("lltime is not null");
            return (Criteria) this;
        }

        public Criteria andLltimeEqualTo(Date value) {
            addCriterion("lltime =", value, "lltime");
            return (Criteria) this;
        }

        public Criteria andLltimeNotEqualTo(Date value) {
            addCriterion("lltime <>", value, "lltime");
            return (Criteria) this;
        }

        public Criteria andLltimeGreaterThan(Date value) {
            addCriterion("lltime >", value, "lltime");
            return (Criteria) this;
        }

        public Criteria andLltimeGreaterThanOrEqualTo(Date value) {
            addCriterion("lltime >=", value, "lltime");
            return (Criteria) this;
        }

        public Criteria andLltimeLessThan(Date value) {
            addCriterion("lltime <", value, "lltime");
            return (Criteria) this;
        }

        public Criteria andLltimeLessThanOrEqualTo(Date value) {
            addCriterion("lltime <=", value, "lltime");
            return (Criteria) this;
        }

        public Criteria andLltimeIn(List<Date> values) {
            addCriterion("lltime in", values, "lltime");
            return (Criteria) this;
        }

        public Criteria andLltimeNotIn(List<Date> values) {
            addCriterion("lltime not in", values, "lltime");
            return (Criteria) this;
        }

        public Criteria andLltimeBetween(Date value1, Date value2) {
            addCriterion("lltime between", value1, value2, "lltime");
            return (Criteria) this;
        }

        public Criteria andLltimeNotBetween(Date value1, Date value2) {
            addCriterion("lltime not between", value1, value2, "lltime");
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