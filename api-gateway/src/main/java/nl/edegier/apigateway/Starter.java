package nl.edegier.apigateway;

import io.vertx.core.Vertx;

import java.net.UnknownHostException;

/**
 * Created by Erwin on 26/11/2016.
 */
public class Starter {
    public static void main(String[] args) throws UnknownHostException {
//        String dockerIp = InetAddress.getLocalHost().getHostAddress();
//        VertxOptions options = new VertxOptions();
//        System.out.println("Bind to "+dockerIp);
//        options.setClusterHost(dockerIp);
//        Vertx.clusteredVertx(options, result -> {
            Vertx vertx = Vertx.vertx();
            vertx.deployVerticle(new CircuitBreakerVerticle());

        //});

    }
}
