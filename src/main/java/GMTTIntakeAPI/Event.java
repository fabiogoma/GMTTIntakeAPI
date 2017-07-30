package GMTTIntakeAPI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.net.InetAddress;

    @Path("/event")
public class Event {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Host getMessage() {
        Host host = new Host();
        try {
            host.setHostname(InetAddress.getLocalHost().getHostName());
            host.setIpAddress(InetAddress.getLocalHost().getHostAddress().toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return host;
    }
}