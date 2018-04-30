package com.osms.monitoring.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "operatingSystem",
        "hardware"
})
public class SolutionSystem {

    @JsonProperty("operatingSystem")
    private OperatingSystem operatingSystem;
    @JsonProperty("hardware")
    private Hardware hardware;

    @JsonProperty("operatingSystem")
    public OperatingSystem getOperatingSystem() {
        return operatingSystem;
    }

    @JsonProperty("operatingSystem")
    public void setOperatingSystem(OperatingSystem operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    @JsonProperty("hardware")
    public Hardware getHardware() {
        return hardware;
    }

    @JsonProperty("hardware")
    public void setHardware(Hardware hardware) {
        this.hardware = hardware;
    }

    @Override
    public String toString()  {
        return "ClassPojo [operatingSystem = "+operatingSystem+", hardware = "+hardware+"]";
    }

}