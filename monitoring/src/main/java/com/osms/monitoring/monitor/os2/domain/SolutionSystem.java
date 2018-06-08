package com.osms.monitoring.monitor.os2.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "platform",
        "operatingSystem",
        "hardware"
})
public class SolutionSystem {

    @JsonProperty("platform")
    private String platform;
    @JsonProperty("operatingSystem")
    private OperatingSystem operatingSystem;
    @JsonProperty("hardware")
    private Hardware hardware;

    @JsonProperty("platform")
    public String getPlatform() {
        return platform;
    }

    @JsonProperty("platform")
    public void setPlatform(String platform) {
        this.platform = platform;
    }

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

}