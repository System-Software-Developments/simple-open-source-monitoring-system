package com.osms.monitoring.monitor.os2.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "available",
        "total",
        "swapTotal",
        "swapUsed"
})
public class Memory {

    @JsonProperty("available")
    private Long available;

    @JsonProperty("total")
    private Long total;

    @JsonProperty("swapTotal")
    private Long swapTotal;

    @JsonProperty("swapUsed")
    private Long swapUsed;

    @JsonProperty("available")
    public Long getAvailable() {
        return available;
    }

    @JsonProperty("available")
    public void setAvailable(Long available) {
        this.available = available;
    }

    @JsonProperty("total")
    public Long getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Long total) {
        this.total = total;
    }

    @JsonProperty("swapTotal")
    public Long getSwapTotal() {
        return swapTotal;
    }

    @JsonProperty("swapTotal")
    public void setSwapTotal(Long swapTotal) {
        this.swapTotal = swapTotal;
    }

    @JsonProperty("swapUsed")
    public Long getSwapUsed() {
        return swapUsed;
    }

    @JsonProperty("swapUsed")
    public void setSwapUsed(Long swapUsed) {
        this.swapUsed = swapUsed;
    }

    @Override
    public String toString() {
        return "ClassPojo [total = "+total+", swapUsed = "+swapUsed+", swapTotal = "+swapTotal+", available = "+available+"]";
    }
}
