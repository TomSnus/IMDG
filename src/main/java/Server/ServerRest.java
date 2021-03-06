package Server;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.ReplicatedMap;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import de.othr.vs.data.entity.Student;
import de.othr.vs.data.entity.Veranstaltung;
import org.glassfish.jersey.server.ResourceConfig;
import rest.StudentResource;
import rest.VeranstaltungResource;


import javax.swing.*;
import javax.ws.rs.ext.RuntimeDelegate;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.util.Map;

/**
 * Created by stt44293 on 20.06.2017.
 */
public class ServerRest {
    public static Config cfg = new Config();
    public static HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
    public static ReplicatedMap<Integer, Student> mapStudents = instance.getReplicatedMap("students");
    public static IMap<String, Veranstaltung> mapVeranstaltungen = instance.getMap("veranstaltungen");

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException {


        ResourceConfig config = new ResourceConfig();
        config.register(StudentResource.class);
        config.register(VeranstaltungResource.class);
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(config, HttpHandler.class);
        server.createContext("/webresources", handler);
        server.start();

        JOptionPane.showMessageDialog(null, "Server stoppen...");
        server.stop(0);
    }


}
