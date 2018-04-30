package com.osms.monitoring.monitor.os2.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "fileSystem"
})
public class OperatingSystem {

    @JsonProperty("fileSystem")
    private FileSystem fileSystem;

    @JsonProperty("fileSystem")
    public FileSystem getFileSystem() {
        return fileSystem;
    }

    @JsonProperty("fileSystem")
    public void setFileSystem(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public String toString() {
        return "ClassPojo [fileSystem = "+fileSystem+"]";
    }
}
