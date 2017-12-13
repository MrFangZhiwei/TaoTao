package com.keyone.pojo;

public class KoParameter {
    private Integer id;

    private String temperature;

    private String humidity;

    private String carbon;

    private String tpCreatetime;

    private String hmCreatetime;

    private String cbCreatetime;

    private String createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature == null ? null : temperature.trim();
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity == null ? null : humidity.trim();
    }

    public String getCarbon() {
        return carbon;
    }

    public void setCarbon(String carbon) {
        this.carbon = carbon == null ? null : carbon.trim();
    }

    public String getTpCreatetime() {
        return tpCreatetime;
    }

    public void setTpCreatetime(String tpCreatetime) {
        this.tpCreatetime = tpCreatetime == null ? null : tpCreatetime.trim();
    }

    public String getHmCreatetime() {
        return hmCreatetime;
    }

    public void setHmCreatetime(String hmCreatetime) {
        this.hmCreatetime = hmCreatetime == null ? null : hmCreatetime.trim();
    }

    public String getCbCreatetime() {
        return cbCreatetime;
    }

    public void setCbCreatetime(String cbCreatetime) {
        this.cbCreatetime = cbCreatetime == null ? null : cbCreatetime.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }
}