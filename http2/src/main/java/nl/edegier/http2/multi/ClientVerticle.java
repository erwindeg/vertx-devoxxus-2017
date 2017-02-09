package nl.edegier.http2.multi;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpVersion;

/**
 * Created by Erwin on 22/01/2017.
 */
public class ClientVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        // Note! in real-life you wouldn't often set trust all to true as it could leave you open to man in the middle attacks.

        HttpClientOptions options = new HttpClientOptions().
                setSsl(true).
                setUseAlpn(true).
                setProtocolVersion(HttpVersion.HTTP_2).
                setTrustAll(true);

        HttpClientRequest request = vertx.createHttpClient(options
        ).get(8443, "localhost", "/");

        request.handler(resp -> {

            // Print custom frames received from server

            resp.customFrameHandler(frame -> {
                System.out.println("Got frame from server " + frame.payload().toString("UTF-8"));
            });
        });

        request.sendHead(version -> {

            // Once head has been sent we can send custom frames

            vertx.setPeriodic(1000, timerID -> {

                System.out.println("Sending ping frame to server");
                request.writeCustomFrame(10, 0, Buffer.buffer("ping"));
            });
        });
    }
}
