package nl.edegier.runner;

import io.vertx.core.Verticle;
import io.vertx.core.Vertx;

import java.util.Arrays;

/**
 * Created by Erwin on 26/11/2016.
 */
public class Runner {

    public static void run(Verticle... verticles) {
        Vertx vertx = Vertx.vertx();
        Arrays.stream(verticles).forEach(vertx::deployVerticle);
    }
}
