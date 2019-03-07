package main;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import resources.*;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
public class Main {
    public static Datastore datastore;
    public static void main(String[] args){
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        ResourceConfig config = new ResourceConfig()
                .packages("resources")
                .register(DeclarativeLinkingFeature.class)
                .register(DateParamConverterProvider.class)
                .register(CustomHeaders.class);
        final Morphia morphia = new Morphia();
        morphia.mapPackage("tpsi");
        datastore = morphia.createDatastore(new MongoClient("localhost", 8004), "tpsi");
        datastore.ensureIndexes();
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
    }
}
