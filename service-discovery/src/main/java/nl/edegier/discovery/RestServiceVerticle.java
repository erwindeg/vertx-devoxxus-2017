package nl.edegier.discovery;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.types.HttpEndpoint;

/**
 * Created by Erwin on 03/02/2017.
 */
public class RestServiceVerticle extends AbstractVerticle{

    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router.route("/").handler(this::helloWorld);
        server.requestHandler(router::accept).listen(8080);


        ServiceDiscovery discovery = ServiceDiscovery.create(vertx);

// Customize the configuration
//        discovery = ServiceDiscovery.create(vertx,
//                new ServiceDiscoveryOptions()
//                        .setAnnounceAddress("service-announce")
//                        .setName("my-name"));

// Do something...
        Record record = HttpEndpoint.createRecord("example-rest-api", "localhost", 8080, "/api");
        discovery.publish(record, ar -> {
            if (ar.succeeded()) {
                // publication succeeded
                Record publishedRecord = ar.result();
            } else {
                // publication failed
            }
        });

//        discovery.close();
    }


    private void helloWorld(RoutingContext routingContext) {
        routingContext.response().end("hello world erwin");
    }
}
