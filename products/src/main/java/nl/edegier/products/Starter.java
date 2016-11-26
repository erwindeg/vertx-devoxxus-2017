package nl.edegier.products;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * Created by Erwin on 26/11/2016.
 */
public class Starter {
    public static void main(String[] args) {
        Vertx.clusteredVertx(new VertxOptions(), result -> {
            Vertx vertx = result.result();
            vertx.deployVerticle(new ProductsVerticle());
        });

    }
}
