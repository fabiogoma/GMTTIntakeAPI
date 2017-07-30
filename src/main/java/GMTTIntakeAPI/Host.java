package GMTTIntakeAPI;

import javax.xml.bind.annotation.XmlRootElement;

public class Host {
    private String hostname;
    private String ipAddress;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
