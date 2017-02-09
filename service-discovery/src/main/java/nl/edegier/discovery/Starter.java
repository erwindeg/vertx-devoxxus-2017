package nl.edegier.discovery;

import io.vertx.core.Vertx;

import java.net.UnknownHostException;

/**
 * Created by Erwin on 26/11/2016.
 */
public class Starter {
    public static void main(String[] args) throws UnknownHostException {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new RestServiceVerticle(), result ->{
            vertx.deployVerticle(new RestClientVerticle());
        });

    }
}
