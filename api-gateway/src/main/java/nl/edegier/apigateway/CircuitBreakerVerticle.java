package nl.edegier.apigateway;

import io.vertx.circuitbreaker.CircuitBreaker;
import io.vertx.circuitbreaker.CircuitBreakerOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by Erwin on 14/01/2017.
 */
public class CircuitBreakerVerticle extends AbstractVerticle {

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new CircuitBreakerVerticle());
    }

    CircuitBreaker breaker;

    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router.route("/*").handler(this::handle);
        server.requestHandler(router::accept).listen(8000);
        breaker = CircuitBreaker.create("hello-breaker", vertx,
                new CircuitBreakerOptions()
                        .setMaxFailures(3)
                        .setTimeout(2000));

    }

    private void handle(RoutingContext routingContext) {
        System.out.println("request");

        breaker.openHandler(v -> {
            System.out.println("Circuit opened");
        }).closeHandler(v -> {
            System.out.println("Circuit closed");
        });

        breaker.execute(future -> {
            vertx.createHttpClient().getNow(8080, "localhost", "/hello", response -> {
                if (response.statusCode() != 200) {
                    future.fail("HTTP Error");
                    System.out.println("failed");
                } else {
                    response.exceptionHandler(future::fail)
                            .bodyHandler(buffer -> {
                                future.complete(buffer.toString());
                            });
                }
            });
        }).setHandler(ar -> {
            if (ar.succeeded()) {
                routingContext.response().end(ar.result().toString());
            } else {
                routingContext.response().setStatusCode(500).end();
            }
        });
    }
}
