package com.pe.ventas.back.rest;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.options;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pe.ventas.back.dtos.rest.ResultResponse;
import com.pe.ventas.back.rest.usuarios.IUsuarioRest;

import spark.Request;
import spark.Response;
import spark.servlet.SparkApplication;

public class UsuarioApp implements SparkApplication {

    private static final Logger LOG = LogManager.getLogger(UsuarioApp.class);

    private final AbstractApplicationContext context = new ClassPathXmlApplicationContext("app-config.xml");

    @Override
    public void init() {
        final IUsuarioRest usuarioRest = (IUsuarioRest) context.getBean("usuarioRest");

        enableCORS("*", "*", "*");
        beforeServer();
        get("/hello", (request, response) -> new ResultResponse.Builder().code(200)
                .message("Works!! " + UUID.randomUUID().toString()).build());

        usuarioRest.routers();

        afterServer();

    }

    @Override
    public void destroy() {
        context.close();
        context.registerShutdownHook();
    }

    private void beforeServer() {
        before((request, response) -> {
            if (LOG.isDebugEnabled()) {
                LOG.debug(requestInfoToString(request));
            }
        });
    }

    // Enables CORS on requests. This method is an initialization method and should
    // be called once.
    private void enableCORS(final String origin, final String methods, final String headers) {

        options("/*", (request, response) -> {

            final String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            final String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
            // Note: this may or may not be necessary in your particular application
            // response.type("application/json");
        });
    }

    private void afterServer() {
        after((request, response) -> {
            response.header("Content-Encoding", "gzip");
            if (LOG.isDebugEnabled()) {
                LOG.debug(responseInfoToString(response));
            }
        });
    }

    private String responseInfoToString(final Response response) {
        final StringBuilder sb = new StringBuilder();
        sb.append("RESPONSE [");
        sb.append("\n");
        sb.append("\n");
        // sb.append("Headers: " + response.);
        sb.append("\n");
        sb.append("Status: " + response.status());
        sb.append("\n");
        sb.append("Body. " + response.toString());
        sb.append("\n");
        sb.append("]");
        return sb.toString();
    }

    private String requestInfoToString(final Request request) {

        final UUID uuid = UUID.randomUUID();
        final StringBuilder sb = new StringBuilder();
        sb.append("REQUEST [");
        sb.append("\n");
        sb.append("UUID: " + uuid.toString());
        sb.append("\n");
        sb.append("Headers: [\n");
        for (final String header : request.headers()) {
            sb.append(" Header: [ " + header);
            sb.append(": " + request.headers(header) + " ]\n");
        }
        sb.append("]");
        sb.append("Method: " + request.requestMethod());
        sb.append("\n");
        sb.append("URL: " + request.url());
        sb.append("\n");
        sb.append("Body. " + request.body());
        sb.append("\n");
        sb.append("]");

        return sb.toString();
    }

}
