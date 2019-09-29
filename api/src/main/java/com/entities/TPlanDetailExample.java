package com.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TPlanDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TPlanDetailExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPlanIdIsNull() {
            addCriterion("plan_id is null");
            return (Criteria) this;
        }

        public Criteria andPlanIdIsNotNull() {
            addCriterion("plan_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlanIdEqualTo(Integer value) {
            addCriterion("plan_id =", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotEqualTo(Integer value) {
            addCriterion("plan_id <>", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdGreaterThan(Integer value) {
            addCriterion("plan_id >", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("plan_id >=", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdLessThan(Integer value) {
            addCriterion("plan_id <", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdLessThanOrEqualTo(Integer value) {
            addCriterion("plan_id <=", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdIn(List<Integer> values) {
            addCriterion("plan_id in", values, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotIn(List<Integer> values) {
            addCriterion("plan_id not in", values, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdBetween(Integer value1, Integer value2) {
            addCriterion("plan_id between", value1, value2, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotBetween(Integer value1, Integer value2) {
            addCriterion("plan_id not between", value1, value2, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanMonthIsNull() {
            addCriterion("plan_month is null");
            return (Criteria) this;
        }

        public Criteria andPlanMonthIsNotNull() {
            addCriterion("plan_month is not null");
            return (Criteria) this;
        }

        public Criteria andPlanMonthEqualTo(Integer value) {
            addCriterion("plan_month =", value, "planMonth");
            return (Criteria) this;
        }

        public Criteria andPlanMonthNotEqualTo(Integer value) {
            addCriterion("plan_month <>", value, "planMonth");
            return (Criteria) this;
        }

        public Criteria andPlanMonthGreaterThan(Integer value) {
            addCriterion("plan_month >", value, "planMonth");
            return (Criteria) this;
        }

        public Criteria andPlanMonthGreaterThanOrEqualTo(Integer value) {
            addCriterion("plan_month >=", value, "planMonth");
            return (Criteria) this;
        }

        public Criteria andPlanMonthLessThan(Integer value) {
            addCriterion("plan_month <", value, "planMonth");
            return (Criteria) this;
        }

        public Criteria andPlanMonthLessThanOrEqualTo(Integer value) {
            addCriterion("plan_month <=", value, "planMonth");
            return (Criteria) this;
        }

        public Criteria andPlanMonthIn(List<Integer> values) {
            addCriterion("plan_month in", values, "planMonth");
            return (Criteria) this;
        }

        public Criteria andPlanMonthNotIn(List<Integer> values) {
            addCriterion("plan_month not in", values, "planMonth");
            return (Criteria) this;
        }

        public Criteria andPlanMonthBetween(Integer value1, Integer value2) {
            addCriterion("plan_month between", value1, value2, "planMonth");
            return (Criteria) this;
        }

        public Criteria andPlanMonthNotBetween(Integer value1, Integer value2) {
            addCriterion("plan_month not between", value1, value2, "planMonth");
            return (Criteria) this;
        }

        public Criteria andPlanQuarterIsNull() {
            addCriterion("plan_quarter is null");
            return (Criteria) this;
        }

        public Criteria andPlanQuarterIsNotNull() {
            addCriterion("plan_quarter is not null");
            return (Criteria) this;
        }

        public Criteria andPlanQuarterEqualTo(Integer value) {
            addCriterion("plan_quarter =", value, "planQuarter");
            return (Criteria) this;
        }

        public Criteria andPlanQuarterNotEqualTo(Integer value) {
            addCriterion("plan_quarter <>", value, "planQuarter");
            return (Criteria) this;
        }

        public Criteria andPlanQuarterGreaterThan(Integer value) {
            addCriterion("plan_quarter >", value, "planQuarter");
            return (Criteria) this;
        }

        public Criteria andPlanQuarterGreaterThanOrEqualTo(Integer value) {
            addCriterion("plan_quarter >=", value, "planQuarter");
            return (Criteria) this;
        }

        public Criteria andPlanQuarterLessThan(Integer value) {
            addCriterion("plan_quarter <", value, "planQuarter");
            return (Criteria) this;
        }

        public Criteria andPlanQuarterLessThanOrEqualTo(Integer value) {
            addCriterion("plan_quarter <=", value, "planQuarter");
            return (Criteria) this;
        }

        public Criteria andPlanQuarterIn(List<Integer> values) {
            addCriterion("plan_quarter in", values, "planQuarter");
            return (Criteria) this;
        }

        public Criteria andPlanQuarterNotIn(List<Integer> values) {
            addCriterion("plan_quarter not in", values, "planQuarter");
            return (Criteria) this;
        }

        public Criteria andPlanQuarterBetween(Integer value1, Integer value2) {
            addCriterion("plan_quarter between", value1, value2, "planQuarter");
            return (Criteria) this;
        }

        public Criteria andPlanQuarterNotBetween(Integer value1, Integer value2) {
            addCriterion("plan_quarter not between", value1, value2, "planQuarter");
            return (Criteria) this;
        }

        public Criteria andPlanAmountIsNull() {
            addCriterion("plan_amount is null");
            return (Criteria) this;
        }

        public Criteria andPlanAmountIsNotNull() {
            addCriterion("plan_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPlanAmountEqualTo(Double value) {
            addCriterion("plan_amount =", value, "planAmount");
            return (Criteria) this;
        }

        public Criteria andPlanAmountNotEqualTo(Double value) {
            addCriterion("plan_amount <>", value, "planAmount");
            return (Criteria) this;
        }

        public Criteria andPlanAmountGreaterThan(Double value) {
            addCriterion("plan_amount >", value, "planAmount");
            return (Criteria) this;
        }

        public Criteria andPlanAmountGreaterThanOrEqualTo(Double value) {
            addCriterion("plan_amount >=", value, "planAmount");
            return (Criteria) this;
        }

        public Criteria andPlanAmountLessThan(Double value) {
            addCriterion("plan_amount <", value, "planAmount");
            return (Criteria) this;
        }

        public Criteria andPlanAmountLessThanOrEqualTo(Double value) {
            addCriterion("plan_amount <=", value, "planAmount");
            return (Criteria) this;
        }

        public Criteria andPlanAmountIn(List<Double> values) {
            addCriterion("plan_amount in", values, "planAmount");
            return (Criteria) this;
        }

        public Criteria andPlanAmountNotIn(List<Double> values) {
            addCriterion("plan_amount not in", values, "planAmount");
            return (Criteria) this;
        }

        public Criteria andPlanAmountBetween(Double value1, Double value2) {
            addCriterion("plan_amount between", value1, value2, "planAmount");
            return (Criteria) this;
        }

        public Criteria andPlanAmountNotBetween(Double value1, Double value2) {
            addCriterion("plan_amount not between", value1, value2, "planAmount");
            return (Criteria) this;
        }

        public Criteria andPlanRateIsNull() {
            addCriterion("plan_rate is null");
            return (Criteria) this;
        }

        public Criteria andPlanRateIsNotNull() {
            addCriterion("plan_rate is not null");
            return (Criteria) this;
        }

        public Criteria andPlanRateEqualTo(Double value) {
            addCriterion("plan_rate =", value, "planRate");
            return (Criteria) this;
        }

        public Criteria andPlanRateNotEqualTo(Double value) {
            addCriterion("plan_rate <>", value, "planRate");
            return (Criteria) this;
        }

        public Criteria andPlanRateGreaterThan(Double value) {
            addCriterion("plan_rate >", value, "planRate");
            return (Criteria) this;
        }

        public Criteria andPlanRateGreaterThanOrEqualTo(Double value) {
            addCriterion("plan_rate >=", value, "planRate");
            return (Criteria) this;
        }

        public Criteria andPlanRateLessThan(Double value) {
            addCriterion("plan_rate <", value, "planRate");
            return (Criteria) this;
        }

        public Criteria andPlanRateLessThanOrEqualTo(Double value) {
            addCriterion("plan_rate <=", value, "planRate");
            return (Criteria) this;
        }

        public Criteria andPlanRateIn(List<Double> values) {
            addCriterion("plan_rate in", values, "planRate");
            return (Criteria) this;
        }

        public Criteria andPlanRateNotIn(List<Double> values) {
            addCriterion("plan_rate not in", values, "planRate");
            return (Criteria) this;
        }

        public Criteria andPlanRateBetween(Double value1, Double value2) {
            addCriterion("plan_rate between", value1, value2, "planRate");
            return (Criteria) this;
        }

        public Criteria andPlanRateNotBetween(Double value1, Double value2) {
            addCriterion("plan_rate not between", value1, value2, "planRate");
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

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andActualUseRateIsNull() {
            addCriterion("actual_use_rate is null");
            return (Criteria) this;
        }

        public Criteria andActualUseRateIsNotNull() {
            addCriterion("actual_use_rate is not null");
            return (Criteria) this;
        }

        public Criteria andActualUseRateEqualTo(Double value) {
            addCriterion("actual_use_rate =", value, "actualUseRate");
            return (Criteria) this;
        }

        public Criteria andActualUseRateNotEqualTo(Double value) {
            addCriterion("actual_use_rate <>", value, "actualUseRate");
            return (Criteria) this;
        }

        public Criteria andActualUseRateGreaterThan(Double value) {
            addCriterion("actual_use_rate >", value, "actualUseRate");
            return (Criteria) this;
        }

        public Criteria andActualUseRateGreaterThanOrEqualTo(Double value) {
            addCriterion("actual_use_rate >=", value, "actualUseRate");
            return (Criteria) this;
        }

        public Criteria andActualUseRateLessThan(Double value) {
            addCriterion("actual_use_rate <", value, "actualUseRate");
            return (Criteria) this;
        }

        public Criteria andActualUseRateLessThanOrEqualTo(Double value) {
            addCriterion("actual_use_rate <=", value, "actualUseRate");
            return (Criteria) this;
        }

        public Criteria andActualUseRateIn(List<Double> values) {
            addCriterion("actual_use_rate in", values, "actualUseRate");
            return (Criteria) this;
        }

        public Criteria andActualUseRateNotIn(List<Double> values) {
            addCriterion("actual_use_rate not in", values, "actualUseRate");
            return (Criteria) this;
        }

        public Criteria andActualUseRateBetween(Double value1, Double value2) {
            addCriterion("actual_use_rate between", value1, value2, "actualUseRate");
            return (Criteria) this;
        }

        public Criteria andActualUseRateNotBetween(Double value1, Double value2) {
            addCriterion("actual_use_rate not between", value1, value2, "actualUseRate");
            return (Criteria) this;
        }

        public Criteria andActualUseAmountIsNull() {
            addCriterion("actual_use_amount is null");
            return (Criteria) this;
        }

        public Criteria andActualUseAmountIsNotNull() {
            addCriterion("actual_use_amount is not null");
            return (Criteria) this;
        }

        public Criteria andActualUseAmountEqualTo(Double value) {
            addCriterion("actual_use_amount =", value, "actualUseAmount");
            return (Criteria) this;
        }

        public Criteria andActualUseAmountNotEqualTo(Double value) {
            addCriterion("actual_use_amount <>", value, "actualUseAmount");
            return (Criteria) this;
        }

        public Criteria andActualUseAmountGreaterThan(Double value) {
            addCriterion("actual_use_amount >", value, "actualUseAmount");
            return (Criteria) this;
        }

        public Criteria andActualUseAmountGreaterThanOrEqualTo(Double value) {
            addCriterion("actual_use_amount >=", value, "actualUseAmount");
            return (Criteria) this;
        }

        public Criteria andActualUseAmountLessThan(Double value) {
            addCriterion("actual_use_amount <", value, "actualUseAmount");
            return (Criteria) this;
        }

        public Criteria andActualUseAmountLessThanOrEqualTo(Double value) {
            addCriterion("actual_use_amount <=", value, "actualUseAmount");
            return (Criteria) this;
        }

        public Criteria andActualUseAmountIn(List<Double> values) {
            addCriterion("actual_use_amount in", values, "actualUseAmount");
            return (Criteria) this;
        }

        public Criteria andActualUseAmountNotIn(List<Double> values) {
            addCriterion("actual_use_amount not in", values, "actualUseAmount");
            return (Criteria) this;
        }

        public Criteria andActualUseAmountBetween(Double value1, Double value2) {
            addCriterion("actual_use_amount between", value1, value2, "actualUseAmount");
            return (Criteria) this;
        }

        public Criteria andActualUseAmountNotBetween(Double value1, Double value2) {
            addCriterion("actual_use_amount not between", value1, value2, "actualUseAmount");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(Integer value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(Integer value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(Integer value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(Integer value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(Integer value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<Integer> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<Integer> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(Integer value1, Integer value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(Integer value1, Integer value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
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