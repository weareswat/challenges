package com.rupeal.challenge.bahamas.client.interceptors;

import io.vertx.core.http.HttpServerRequest;
import org.jboss.logging.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class LogInterceptor implements ContainerRequestFilter {
    private static final Logger LOG = Logger.getLogger(LogInterceptor.class);

    @Context
    UriInfo uriInfo;

    @Context
    HttpServerRequest httpServerRequest;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        final String method = requestContext.getMethod();
        final String path = uriInfo.getPath();
        final String address = httpServerRequest.remoteAddress().toString();

        LOG.infof("Request %s %s from IP %s", method, path, address);
    }
}
