package com.osms.monitoring.monitor.os2.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "version",
        "codeName",
        "build"
})
public class Version {

    @JsonProperty("version")
    private String version;
    @JsonProperty("codeName")
    private String codeName;
    @JsonProperty("build")
    private String build;

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("codeName")
    public String getCodeName() {
        return codeName;
    }

    @JsonProperty("codeName")
    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    @JsonProperty("build")
    public String getBuild() {
        return build;
    }

    @JsonProperty("build")
    public void setBuild(String build) {
        this.build = build;
    }

}