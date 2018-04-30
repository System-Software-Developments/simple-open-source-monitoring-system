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
public class FileStores {

    @JsonProperty("name")
    private String name;
    @JsonProperty("volume")
    private String volume;
    @JsonProperty("usableSpace")
    private Integer usableSpace;
    @JsonProperty("totalSpace")
    private Integer totalSpace;

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
    public Integer getUsableSpace() {
        return usableSpace;
    }

    @JsonProperty("usableSpace")
    public void setUsableSpace(Integer usableSpace) {
        this.usableSpace = usableSpace;
    }

    @JsonProperty("totalSpace")
    public Integer getTotalSpace() {
        return totalSpace;
    }

    @JsonProperty("totalSpace")
    public void setTotalSpace(Integer totalSpace) {
        this.totalSpace = totalSpace;
    }

    @Override
    public String toString(){
        return "ClassPojo [totalSpace = "+totalSpace+", usableSpace = "+usableSpace+", name = "+name+", volume = "+volume+"]";
    }
}
