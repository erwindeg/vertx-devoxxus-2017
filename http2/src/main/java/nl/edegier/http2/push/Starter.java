package nl.edegier.http2.push;

import io.vertx.core.Vertx;

import java.net.UnknownHostException;

/**
 * Created by Erwin on 26/11/2016.
 */
public class Starter {
    public static void main(String[] args) throws UnknownHostException {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new Server(), stringAsyncResult -> vertx.deployVerticle(new Client()));

    }
}
