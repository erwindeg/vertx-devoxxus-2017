package nl.edegier.http2.multi;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.ext.web.Router;

/**
 * Created by Erwin on 20/01/2017.
 */
public class ServerVerticle extends AbstractVerticle {


    @Override
    public void start() throws Exception {
        final Image image = new Image(vertx, "coin.png");

        Router router = Router.router(vertx);

        router.get("/").handler(ctx -> {
            ctx.response()
                    .putHeader("Content-Type", "text/html")
                    .end(image.generateHTML(16));
        });

        router.get("/img/:x/:y").handler(ctx -> {
            ctx.response()
                    .putHeader("Content-Type", "image/png")
                    .end(image.getPixel(Integer.parseInt(ctx.pathParam("x")), Integer.parseInt(ctx.pathParam("y"))));
        });

        router.get("/version").handler(ctx ->{
            ctx.response().putHeader("content-type", "text/html").end("<html><body>" +
                    "<h1>Hello from vert.x!</h1>" +
                    "<p>version = " + ctx.request().version() + "</p>" +
                    "</body></html>");
        });

        HttpServer server =  vertx.createHttpServer(
                new HttpServerOptions()
                        .setSsl(true)
                        //Set to true to enable HTTP/2
                        .setUseAlpn(true)
                        .setPemKeyCertOptions(new PemKeyCertOptions().setKeyPath("server-key.pem").setCertPath("server-cert.pem"))).requestHandler(router::accept);


            server.listen(8443);
    }
}
