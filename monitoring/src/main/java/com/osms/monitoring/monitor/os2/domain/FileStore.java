package com.osms.monitoring.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "volume",
        "usableSpace",
        "totalSpace"
})
public class FileStore {

    @JsonProperty("name")
    private String name;
    @JsonProperty("volume")
    private String volume;
    @JsonProperty("usableSpace")
    private Long usableSpace;
    @JsonProperty("totalSpace")
    private Long totalSpace;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("volume")
    public String getVolume() {
        return volume;
    }

    @JsonProperty("volume")
    public void setVolume(String volume) {
        this.volume = volume;
    }

    @JsonProperty("usableSpace")
    public Long getUsableSpace() {
        return usableSpace;
    }

    @JsonProperty("usableSpace")
    public void setUsableSpace(Long usableSpace) {
        this.usableSpace = usableSpace;
    }

    @JsonProperty("totalSpace")
    public Long getTotalSpace() {
        return totalSpace;
    }

    @JsonProperty("totalSpace")
    public void setTotalSpace(Long totalSpace) {
        this.totalSpace = totalSpace;
    }

    @Override
    public String toString(){
        return "ClassPojo [totalSpace = "+totalSpace+", usableSpace = "+usableSpace+", name = "+name+", volume = "+volume+"]";
    }
}
