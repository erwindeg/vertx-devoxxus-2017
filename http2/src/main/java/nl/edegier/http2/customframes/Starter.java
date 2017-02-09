package nl.edegier.http2.customframes;

import io.vertx.core.Vertx;

import java.net.UnknownHostException;

/**
 * Created by Erwin on 26/11/2016.
 */
public class Starter {
    public static void main(String[] args) throws UnknownHostException {
        Vertx.vertx().deployVerticle(new Server());
        Vertx.vertx().deployVerticle(new Client());
    }
}
