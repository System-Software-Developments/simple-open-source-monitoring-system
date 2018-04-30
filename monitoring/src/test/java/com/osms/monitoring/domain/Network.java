package com.osms.monitoring.domain;

public class Networks {

    private String bytesSent;

    private String bytesRecv;

    private String[] ipv4;

    private String packetsSent;

    private String packetsRecv;

    private String name;

    private String mac;

    private String displayName;

    private String inErrors;

    public String getBytesSent ()
    {
        return bytesSent;
    }

    public void setBytesSent (String bytesSent)
    {
        this.bytesSent = bytesSent;
    }

    public String getBytesRecv ()
    {
        return bytesRecv;
    }

    public void setBytesRecv (String bytesRecv)
    {
        this.bytesRecv = bytesRecv;
    }

    public String[] getIpv4 ()
    {
        return ipv4;
    }

    public void setIpv4 (String[] ipv4)
    {
        this.ipv4 = ipv4;
    }

    public String getPacketsSent ()
    {
        return packetsSent;
    }

    public void setPacketsSent (String packetsSent)
    {
        this.packetsSent = packetsSent;
    }

    public String getPacketsRecv ()
    {
        return packetsRecv;
    }

    public void setPacketsRecv (String packetsRecv)
    {
        this.packetsRecv = packetsRecv;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getMac ()
    {
        return mac;
    }

    public void setMac (String mac)
    {
        this.mac = mac;
    }

    public String getDisplayName ()
    {
        return displayName;
    }

    public void setDisplayName (String displayName)
    {
        this.displayName = displayName;
    }

    public String getInErrors ()
    {
        return inErrors;
    }

    public void setInErrors (String inErrors)
    {
        this.inErrors = inErrors;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [bytesSent = "+bytesSent+", bytesRecv = "+bytesRecv+", ipv4 = "+ipv4+", packetsSent = "+packetsSent+", packetsRecv = "+packetsRecv+", name = "+name+", mac = "+mac+", displayName = "+displayName+", inErrors = "+inErrors+"]";
    }

}
