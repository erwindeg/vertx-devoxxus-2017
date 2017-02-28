package nl.edegier.discovery;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.ServiceReference;

/**
 * Created by Erwin on 03/02/2017.
 */
public class RestClientVerticle extends AbstractVerticle {

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new RestClientVerticle());
    }

    @Override
    public void start() throws Exception {

        ServiceDiscovery discovery = ServiceDiscovery.create(vertx);
        // Get a record by name
        discovery.getRecord(r -> r.getName().equals("example-rest-api"), ar -> {
            if (ar.succeeded()) {
                if (ar.result() != null) {
                    ServiceReference reference = discovery.getReference(ar.result());
                    System.out.println("Service found");
                    HttpClient client = reference.get();
                    invokeClient(client);
                } else {
                    System.out.println("No Service found");
                }
            } else {
                ar.cause().printStackTrace();
            }
        });
    }

    private void invokeClient(HttpClient client) {
        HttpClientRequest request = client.get("");
        request.handler( result -> System.out.println(result.statusCode())).end();
    }
}
