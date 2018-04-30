package com.osms.monitoring.monitor.os2.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "processor",
        "memory",
        "networks"
})
public class Hardware {

    @JsonProperty("processor")
    private Processor processor;
    @JsonProperty("memory")
    private Memory memory;
    @JsonProperty("networks")
    private List<Network> networks = null;

    @JsonProperty("processor")
    public Processor getProcessor() {
        return processor;
    }

    @JsonProperty("processor")
    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    @JsonProperty("memory")
    public Memory getMemory() {
        return memory;
    }

    @JsonProperty("memory")
    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    @JsonProperty("networks")
    public List<Network> getNetworks() {
        return networks;
    }

    @JsonProperty("networks")
    public void setNetworks(List<Network> networks) {
        this.networks = networks;
    }

    @Override
    public String toString()  {
        return "ClassPojo [networks = "+networks+", processor = "+processor+", memory = "+memory+"]";
    }
}