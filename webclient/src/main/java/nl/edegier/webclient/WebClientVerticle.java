package nl.edegier.webclient;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;

import static nl.edegier.runner.Runner.run;

/**
 * Created by Erwin on 01/02/2017.
 */
public class WebClientVerticle extends AbstractVerticle {

    public static void main(String[] args) {
        run(new WebClientVerticle());
    }

    @Override
    public void start() throws Exception {
        WebClient client = WebClient.create(vertx);
        get(client);
        post(client);
    }

    private void get(WebClient client) {
        client
                .get(80, "jsonplaceholder.typicode.com", "")
                .send(result -> {
                    if (result.succeeded()) {
                        System.out.println(result.result().statusCode());
                    } else {
                        result.cause().printStackTrace();
                    }
                });
    }

    private void getHandleBody(WebClient client){
//        client
//                .get(8080, "localhost", "/something")
//                .send(BodyCodec.jsonObject(),result -> {});
    }

    private void post(WebClient client) {
        client
                .post(80, "jsonplaceholder.typicode.com", "/posts")
                .sendJsonObject(new JsonObject().put("title", "test").put("body", "bodytest").put("userId", 123), result -> {
                    if (result.succeeded()) {
                        System.out.println(result.result().statusCode());
                    } else {
                        result.cause().printStackTrace();
                    }
                });
    }

    private void sendForm(WebClient client){
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.set("firstName", "Dale");
        form.set("lastName", "Cooper");

        // Submit the form as a form URL encoded body
        client
                .post(8080, "myserver.mycompany.com", "/some-uri")
                .sendForm(form, ar -> {
                    if (ar.succeeded()) {
                        // Ok
                    }
                });
    }

}
