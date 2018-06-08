package com.osms.monitoring.monitor.os2.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "manufacturer",
        "family",
        "version",
        "fileSystem",
        "processes"
})
public class OperatingSystem {

    @JsonProperty("manufacturer")
    private String manufacturer;
    @JsonProperty("family")
    private String family;
    @JsonProperty("version")
    private Version version;
    @JsonProperty("fileSystem")
    private FileSystem fileSystem;
    @JsonProperty("processes")
    private List<Process> processes = null;

    @JsonProperty("manufacturer")
    public String getManufacturer() {
        return manufacturer;
    }

    @JsonProperty("manufacturer")
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @JsonProperty("family")
    public String getFamily() {
        return family;
    }

    @JsonProperty("family")
    public void setFamily(String family) {
        this.family = family;
    }

    @JsonProperty("version")
    public Version getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(Version version) {
        this.version = version;
    }

    @JsonProperty("fileSystem")
    public FileSystem getFileSystem() {
        return fileSystem;
    }

    @JsonProperty("fileSystem")
    public void setFileSystem(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    @JsonProperty("processes")
    public List<Process> getProcesses() {
        return processes;
    }

    @JsonProperty("processes")
    public void setProcesses(List<Process> processes) {
        this.processes = processes;
    }

}