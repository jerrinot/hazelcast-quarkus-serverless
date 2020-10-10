package guides.hazelcast.quarkus;

import com.hazelcast.core.HazelcastInstance;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.ConcurrentMap;

@Path("/")
public class CommandController {

    @Inject
    HazelcastInstance hazelcastInstance;

    private ConcurrentMap<String, String> retrieveMap() {
        return hazelcastInstance.getMap("map");
    }

    @POST
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_FORM_URLENCODED})
    public CommandResponse put(@PathParam("id") String id, String value) {
        retrieveMap().put(id, value);
        return new CommandResponse(value, "dummy");
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CommandResponse get(@PathParam("id") String id) {
        String value = retrieveMap().get(id);
        return new CommandResponse(value, "dummy");
    }
}
