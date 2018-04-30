package com.osms.monitoring.monitor.os2.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "processorID",
        "processorCpuLoadBetweenTicks"
})
public class Processor {

    @JsonProperty("processorID")
    private String processorID;

    @JsonProperty("processorCpuLoadBetweenTicks")
    private List<Float> processorCpuLoadBetweenTicks = null;

    @JsonProperty("processorID")
    public String getProcessorID() {
        return processorID;
    }

    @JsonProperty("processorID")
    public void setProcessorID(String processorID) {
        this.processorID = processorID;
    }

    @JsonProperty("processorCpuLoadBetweenTicks")
    public List<Float> getProcessorCpuLoadBetweenTicks() {
        return processorCpuLoadBetweenTicks;
    }

    @JsonProperty("processorCpuLoadBetweenTicks")
    public void setProcessorCpuLoadBetweenTicks(List<Float> processorCpuLoadBetweenTicks) {
        this.processorCpuLoadBetweenTicks = processorCpuLoadBetweenTicks;
    }

    @Override
    public String toString() {
        return "ClassPojo [processorCpuLoadBetweenTicks = "+processorCpuLoadBetweenTicks+", processorID = "+processorID+"]";
    }
}