package com.osms.monitoring.monitor.os2.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "fileStores"
})
public class FileSystem {

    @JsonProperty("fileStores")
    private List<FileStore> fileStores = null;

    @JsonProperty("fileStores")
    public List<FileStore> getFileStores() {
        return fileStores;
    }

    @JsonProperty("fileStores")
    public void setFileStores(List<FileStore> fileStores) {
        this.fileStores = fileStores;
    }

    @Override
    public String toString() {
        return "ClassPojo [fileStores = "+fileStores+"]";
    }

}