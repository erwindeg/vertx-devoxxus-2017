package nl.edegier.products;

import io.vertx.core.AbstractVerticle;

/**
 * Created by Erwin on 26/11/2016.
 */
public class ProductsVerticle extends AbstractVerticle{
    @Override
    public void start() throws Exception {
        vertx.eventBus().consumer("channel1", m -> System.out.println(m.body()));
    }


}
