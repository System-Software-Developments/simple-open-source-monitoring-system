package com.osms.monitoring.monitor.os2.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "path",
        "commandLine",
        "currentWorkingDirectory",
        "user",
        "userID",
        "group",
        "groupID",
        "state",
        "processID",
        "parentProcessID",
        "threadCount",
        "priority",
        "virtualSize",
        "residentSetSize",
        "kernelTime",
        "userTime",
        "upTime",
        "startTime",
        "bytesRead",
        "bytesWritten",
        "handles"
})
public class Process {

    @JsonProperty("name")
    private String name;
    @JsonProperty("path")
    private String path;
    @JsonProperty("commandLine")
    private String commandLine;
    @JsonProperty("currentWorkingDirectory")
    private String currentWorkingDirectory;
    @JsonProperty("user")
    private String user;
    @JsonProperty("userID")
    private String userID;
    @JsonProperty("group")
    private String group;
    @JsonProperty("groupID")
    private String groupID;
    @JsonProperty("state")
    private String state;
    @JsonProperty("processID")
    private Long processID;
    @JsonProperty("parentProcessID")
    private Long parentProcessID;
    @JsonProperty("threadCount")
    private Long threadCount;
    @JsonProperty("priority")
    private Long priority;
    @JsonProperty("virtualSize")
    private Long virtualSize;
    @JsonProperty("residentSetSize")
    private Long residentSetSize;
    @JsonProperty("kernelTime")
    private Long kernelTime;
    @JsonProperty("userTime")
    private Long userTime;
    @JsonProperty("upTime")
    private Long upTime;
    @JsonProperty("startTime")
    private Long startTime;
    @JsonProperty("bytesRead")
    private Long bytesRead;
    @JsonProperty("bytesWritten")
    private Long bytesWritten;
    @JsonProperty("handles")
    private Long handles;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("path")
    public String getPath() {
        return path;
    }

    @JsonProperty("path")
    public void setPath(String path) {
        this.path = path;
    }

    @JsonProperty("commandLine")
    public String getCommandLine() {
        return commandLine;
    }

    @JsonProperty("commandLine")
    public void setCommandLine(String commandLine) {
        this.commandLine = commandLine;
    }

    @JsonProperty("currentWorkingDirectory")
    public String getCurrentWorkingDirectory() {
        return currentWorkingDirectory;
    }

    @JsonProperty("currentWorkingDirectory")
    public void setCurrentWorkingDirectory(String currentWorkingDirectory) {
        this.currentWorkingDirectory = currentWorkingDirectory;
    }

    @JsonProperty("user")
    public String getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(String user) {
        this.user = user;
    }

    @JsonProperty("userID")
    public String getUserID() {
        return userID;
    }

    @JsonProperty("userID")
    public void setUserID(String userID) {
        this.userID = userID;
    }

    @JsonProperty("group")
    public String getGroup() {
        return group;
    }

    @JsonProperty("group")
    public void setGroup(String group) {
        this.group = group;
    }

    @JsonProperty("groupID")
    public String getGroupID() {
        return groupID;
    }

    @JsonProperty("groupID")
    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("processID")
    public Long getProcessID() {
        return processID;
    }

    @JsonProperty("processID")
    public void setProcessID(Long processID) {
        this.processID = processID;
    }

    @JsonProperty("parentProcessID")
    public Long getParentProcessID() {
        return parentProcessID;
    }

    @JsonProperty("parentProcessID")
    public void setParentProcessID(Long parentProcessID) {
        this.parentProcessID = parentProcessID;
    }

    @JsonProperty("threadCount")
    public Long getThreadCount() {
        return threadCount;
    }

    @JsonProperty("threadCount")
    public void setThreadCount(Long threadCount) {
        this.threadCount = threadCount;
    }

    @JsonProperty("priority")
    public Long getPriority() {
        return priority;
    }

    @JsonProperty("priority")
    public void setPriority(Long priority) {
        this.priority = priority;
    }

    @JsonProperty("virtualSize")
    public Long getVirtualSize() {
        return virtualSize;
    }

    @JsonProperty("virtualSize")
    public void setVirtualSize(Long virtualSize) {
        this.virtualSize = virtualSize;
    }

    @JsonProperty("residentSetSize")
    public Long getResidentSetSize() {
        return residentSetSize;
    }

    @JsonProperty("residentSetSize")
    public void setResidentSetSize(Long residentSetSize) {
        this.residentSetSize = residentSetSize;
    }

    @JsonProperty("kernelTime")
    public Long getKernelTime() {
        return kernelTime;
    }

    @JsonProperty("kernelTime")
    public void setKernelTime(Long kernelTime) {
        this.kernelTime = kernelTime;
    }

    @JsonProperty("userTime")
    public Long getUserTime() {
        return userTime;
    }

    @JsonProperty("userTime")
    public void setUserTime(Long userTime) {
        this.userTime = userTime;
    }

    @JsonProperty("upTime")
    public Long getUpTime() {
        return upTime;
    }

    @JsonProperty("upTime")
    public void setUpTime(Long upTime) {
        this.upTime = upTime;
    }

    @JsonProperty("startTime")
    public Long getStartTime() {
        return startTime;
    }

    @JsonProperty("startTime")
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    @JsonProperty("bytesRead")
    public Long getBytesRead() {
        return bytesRead;
    }

    @JsonProperty("bytesRead")
    public void setBytesRead(Long bytesRead) {
        this.bytesRead = bytesRead;
    }

    @JsonProperty("bytesWritten")
    public Long getBytesWritten() {
        return bytesWritten;
    }

    @JsonProperty("bytesWritten")
    public void setBytesWritten(Long bytesWritten) {
        this.bytesWritten = bytesWritten;
    }

    @JsonProperty("handles")
    public Long getHandles() {
        return handles;
    }

    @JsonProperty("handles")
    public void setHandles(Long handles) {
        this.handles = handles;
    }

}
