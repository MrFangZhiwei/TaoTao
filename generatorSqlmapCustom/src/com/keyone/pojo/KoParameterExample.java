package com.keyone.pojo;

import java.util.ArrayList;
import java.util.List;

public class KoParameterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public KoParameterExample() {
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

        public Criteria andTemperatureIsNull() {
            addCriterion("temperature is null");
            return (Criteria) this;
        }

        public Criteria andTemperatureIsNotNull() {
            addCriterion("temperature is not null");
            return (Criteria) this;
        }

        public Criteria andTemperatureEqualTo(String value) {
            addCriterion("temperature =", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotEqualTo(String value) {
            addCriterion("temperature <>", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureGreaterThan(String value) {
            addCriterion("temperature >", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureGreaterThanOrEqualTo(String value) {
            addCriterion("temperature >=", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureLessThan(String value) {
            addCriterion("temperature <", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureLessThanOrEqualTo(String value) {
            addCriterion("temperature <=", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureLike(String value) {
            addCriterion("temperature like", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotLike(String value) {
            addCriterion("temperature not like", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureIn(List<String> values) {
            addCriterion("temperature in", values, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotIn(List<String> values) {
            addCriterion("temperature not in", values, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureBetween(String value1, String value2) {
            addCriterion("temperature between", value1, value2, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotBetween(String value1, String value2) {
            addCriterion("temperature not between", value1, value2, "temperature");
            return (Criteria) this;
        }

        public Criteria andHumidityIsNull() {
            addCriterion("humidity is null");
            return (Criteria) this;
        }

        public Criteria andHumidityIsNotNull() {
            addCriterion("humidity is not null");
            return (Criteria) this;
        }

        public Criteria andHumidityEqualTo(String value) {
            addCriterion("humidity =", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityNotEqualTo(String value) {
            addCriterion("humidity <>", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityGreaterThan(String value) {
            addCriterion("humidity >", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityGreaterThanOrEqualTo(String value) {
            addCriterion("humidity >=", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityLessThan(String value) {
            addCriterion("humidity <", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityLessThanOrEqualTo(String value) {
            addCriterion("humidity <=", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityLike(String value) {
            addCriterion("humidity like", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityNotLike(String value) {
            addCriterion("humidity not like", value, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityIn(List<String> values) {
            addCriterion("humidity in", values, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityNotIn(List<String> values) {
            addCriterion("humidity not in", values, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityBetween(String value1, String value2) {
            addCriterion("humidity between", value1, value2, "humidity");
            return (Criteria) this;
        }

        public Criteria andHumidityNotBetween(String value1, String value2) {
            addCriterion("humidity not between", value1, value2, "humidity");
            return (Criteria) this;
        }

        public Criteria andCarbonIsNull() {
            addCriterion("carbon is null");
            return (Criteria) this;
        }

        public Criteria andCarbonIsNotNull() {
            addCriterion("carbon is not null");
            return (Criteria) this;
        }

        public Criteria andCarbonEqualTo(String value) {
            addCriterion("carbon =", value, "carbon");
            return (Criteria) this;
        }

        public Criteria andCarbonNotEqualTo(String value) {
            addCriterion("carbon <>", value, "carbon");
            return (Criteria) this;
        }

        public Criteria andCarbonGreaterThan(String value) {
            addCriterion("carbon >", value, "carbon");
            return (Criteria) this;
        }

        public Criteria andCarbonGreaterThanOrEqualTo(String value) {
            addCriterion("carbon >=", value, "carbon");
            return (Criteria) this;
        }

        public Criteria andCarbonLessThan(String value) {
            addCriterion("carbon <", value, "carbon");
            return (Criteria) this;
        }

        public Criteria andCarbonLessThanOrEqualTo(String value) {
            addCriterion("carbon <=", value, "carbon");
            return (Criteria) this;
        }

        public Criteria andCarbonLike(String value) {
            addCriterion("carbon like", value, "carbon");
            return (Criteria) this;
        }

        public Criteria andCarbonNotLike(String value) {
            addCriterion("carbon not like", value, "carbon");
            return (Criteria) this;
        }

        public Criteria andCarbonIn(List<String> values) {
            addCriterion("carbon in", values, "carbon");
            return (Criteria) this;
        }

        public Criteria andCarbonNotIn(List<String> values) {
            addCriterion("carbon not in", values, "carbon");
            return (Criteria) this;
        }

        public Criteria andCarbonBetween(String value1, String value2) {
            addCriterion("carbon between", value1, value2, "carbon");
            return (Criteria) this;
        }

        public Criteria andCarbonNotBetween(String value1, String value2) {
            addCriterion("carbon not between", value1, value2, "carbon");
            return (Criteria) this;
        }

        public Criteria andTpCreatetimeIsNull() {
            addCriterion("tp_createtime is null");
            return (Criteria) this;
        }

        public Criteria andTpCreatetimeIsNotNull() {
            addCriterion("tp_createtime is not null");
            return (Criteria) this;
        }

        public Criteria andTpCreatetimeEqualTo(String value) {
            addCriterion("tp_createtime =", value, "tpCreatetime");
            return (Criteria) this;
        }

        public Criteria andTpCreatetimeNotEqualTo(String value) {
            addCriterion("tp_createtime <>", value, "tpCreatetime");
            return (Criteria) this;
        }

        public Criteria andTpCreatetimeGreaterThan(String value) {
            addCriterion("tp_createtime >", value, "tpCreatetime");
            return (Criteria) this;
        }

        public Criteria andTpCreatetimeGreaterThanOrEqualTo(String value) {
            addCriterion("tp_createtime >=", value, "tpCreatetime");
            return (Criteria) this;
        }

        public Criteria andTpCreatetimeLessThan(String value) {
            addCriterion("tp_createtime <", value, "tpCreatetime");
            return (Criteria) this;
        }

        public Criteria andTpCreatetimeLessThanOrEqualTo(String value) {
            addCriterion("tp_createtime <=", value, "tpCreatetime");
            return (Criteria) this;
        }

        public Criteria andTpCreatetimeLike(String value) {
            addCriterion("tp_createtime like", value, "tpCreatetime");
            return (Criteria) this;
        }

        public Criteria andTpCreatetimeNotLike(String value) {
            addCriterion("tp_createtime not like", value, "tpCreatetime");
            return (Criteria) this;
        }

        public Criteria andTpCreatetimeIn(List<String> values) {
            addCriterion("tp_createtime in", values, "tpCreatetime");
            return (Criteria) this;
        }

        public Criteria andTpCreatetimeNotIn(List<String> values) {
            addCriterion("tp_createtime not in", values, "tpCreatetime");
            return (Criteria) this;
        }

        public Criteria andTpCreatetimeBetween(String value1, String value2) {
            addCriterion("tp_createtime between", value1, value2, "tpCreatetime");
            return (Criteria) this;
        }

        public Criteria andTpCreatetimeNotBetween(String value1, String value2) {
            addCriterion("tp_createtime not between", value1, value2, "tpCreatetime");
            return (Criteria) this;
        }

        public Criteria andHmCreatetimeIsNull() {
            addCriterion("hm_createtime is null");
            return (Criteria) this;
        }

        public Criteria andHmCreatetimeIsNotNull() {
            addCriterion("hm_createtime is not null");
            return (Criteria) this;
        }

        public Criteria andHmCreatetimeEqualTo(String value) {
            addCriterion("hm_createtime =", value, "hmCreatetime");
            return (Criteria) this;
        }

        public Criteria andHmCreatetimeNotEqualTo(String value) {
            addCriterion("hm_createtime <>", value, "hmCreatetime");
            return (Criteria) this;
        }

        public Criteria andHmCreatetimeGreaterThan(String value) {
            addCriterion("hm_createtime >", value, "hmCreatetime");
            return (Criteria) this;
        }

        public Criteria andHmCreatetimeGreaterThanOrEqualTo(String value) {
            addCriterion("hm_createtime >=", value, "hmCreatetime");
            return (Criteria) this;
        }

        public Criteria andHmCreatetimeLessThan(String value) {
            addCriterion("hm_createtime <", value, "hmCreatetime");
            return (Criteria) this;
        }

        public Criteria andHmCreatetimeLessThanOrEqualTo(String value) {
            addCriterion("hm_createtime <=", value, "hmCreatetime");
            return (Criteria) this;
        }

        public Criteria andHmCreatetimeLike(String value) {
            addCriterion("hm_createtime like", value, "hmCreatetime");
            return (Criteria) this;
        }

        public Criteria andHmCreatetimeNotLike(String value) {
            addCriterion("hm_createtime not like", value, "hmCreatetime");
            return (Criteria) this;
        }

        public Criteria andHmCreatetimeIn(List<String> values) {
            addCriterion("hm_createtime in", values, "hmCreatetime");
            return (Criteria) this;
        }

        public Criteria andHmCreatetimeNotIn(List<String> values) {
            addCriterion("hm_createtime not in", values, "hmCreatetime");
            return (Criteria) this;
        }

        public Criteria andHmCreatetimeBetween(String value1, String value2) {
            addCriterion("hm_createtime between", value1, value2, "hmCreatetime");
            return (Criteria) this;
        }

        public Criteria andHmCreatetimeNotBetween(String value1, String value2) {
            addCriterion("hm_createtime not between", value1, value2, "hmCreatetime");
            return (Criteria) this;
        }

        public Criteria andCbCreatetimeIsNull() {
            addCriterion("cb_createtime is null");
            return (Criteria) this;
        }

        public Criteria andCbCreatetimeIsNotNull() {
            addCriterion("cb_createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCbCreatetimeEqualTo(String value) {
            addCriterion("cb_createtime =", value, "cbCreatetime");
            return (Criteria) this;
        }

        public Criteria andCbCreatetimeNotEqualTo(String value) {
            addCriterion("cb_createtime <>", value, "cbCreatetime");
            return (Criteria) this;
        }

        public Criteria andCbCreatetimeGreaterThan(String value) {
            addCriterion("cb_createtime >", value, "cbCreatetime");
            return (Criteria) this;
        }

        public Criteria andCbCreatetimeGreaterThanOrEqualTo(String value) {
            addCriterion("cb_createtime >=", value, "cbCreatetime");
            return (Criteria) this;
        }

        public Criteria andCbCreatetimeLessThan(String value) {
            addCriterion("cb_createtime <", value, "cbCreatetime");
            return (Criteria) this;
        }

        public Criteria andCbCreatetimeLessThanOrEqualTo(String value) {
            addCriterion("cb_createtime <=", value, "cbCreatetime");
            return (Criteria) this;
        }

        public Criteria andCbCreatetimeLike(String value) {
            addCriterion("cb_createtime like", value, "cbCreatetime");
            return (Criteria) this;
        }

        public Criteria andCbCreatetimeNotLike(String value) {
            addCriterion("cb_createtime not like", value, "cbCreatetime");
            return (Criteria) this;
        }

        public Criteria andCbCreatetimeIn(List<String> values) {
            addCriterion("cb_createtime in", values, "cbCreatetime");
            return (Criteria) this;
        }

        public Criteria andCbCreatetimeNotIn(List<String> values) {
            addCriterion("cb_createtime not in", values, "cbCreatetime");
            return (Criteria) this;
        }

        public Criteria andCbCreatetimeBetween(String value1, String value2) {
            addCriterion("cb_createtime between", value1, value2, "cbCreatetime");
            return (Criteria) this;
        }

        public Criteria andCbCreatetimeNotBetween(String value1, String value2) {
            addCriterion("cb_createtime not between", value1, value2, "cbCreatetime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(String value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(String value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(String value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(String value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(String value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(String value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLike(String value) {
            addCriterion("createtime like", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotLike(String value) {
            addCriterion("createtime not like", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<String> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<String> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(String value1, String value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(String value1, String value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
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