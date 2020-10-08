package guides.hazelcast.quarkus;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.properties.ClientProperty;
import com.hazelcast.core.HazelcastInstance;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import static com.hazelcast.client.properties.ClientProperty.HAZELCAST_CLOUD_DISCOVERY_TOKEN;
import static com.hazelcast.client.properties.ClientProperty.STATISTICS_ENABLED;

@ApplicationScoped
public class HazelcastClientConfig {

    @ConfigProperty(name = "CLOUD_TOKEN")
    private String cloudToken;

    @Produces
    HazelcastInstance createInstance() {
        ClientConfig config = new ClientConfig();
        config.setProperty(STATISTICS_ENABLED.getName(), "true");
        config.setProperty(HAZELCAST_CLOUD_DISCOVERY_TOKEN.getName(), cloudToken);
        config.setProperty(ClientProperty.IO_INPUT_THREAD_COUNT.getName(), "1");
        config.setProperty(ClientProperty.IO_OUTPUT_THREAD_COUNT.getName(), "1");
        config.setProperty(ClientProperty.RESPONSE_THREAD_COUNT.getName(), "1");
        config.setProperty(ClientProperty.EVENT_THREAD_COUNT.getName(), "1");
        config.setProperty("hazelcast.client.internal.executor.pool.size", "1");
        config.setClusterName("hz4");
        return HazelcastClient.newHazelcastClient(config);
    }
}
