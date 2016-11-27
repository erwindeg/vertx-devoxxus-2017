package nl.edegier.shop;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by Erwin on 26/11/2016.
 */
public class ShopVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router.route("/hello").handler(this::helloWorld);
        server.requestHandler(router::accept).listen(8080);

        vertx.setPeriodic(1000, t -> {
            vertx.eventBus().send("channel1", "hello");
            System.out.println("send");
        });
    }

    private void helloWorld(RoutingContext routingContext) {
        routingContext.response().end("hello world erwin");
    }
}
