package edu.wmi.dpri.przychodnia.commons.rest;

/**
 * Created by lupus on 21.09.16.
 */
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class GenericRestClient<T> {
    protected WebTarget target;
    protected T resource;

    public GenericRestClient(String url, Class<T> apiClass) {
        ClientConfig cc = new ClientConfig();
        Client client = ClientBuilder.newClient(cc).register(new JacksonFeature()).property("jersey.config.client.suppressHttpComplianceValidation", Boolean.valueOf(true));
        this.target = client.target(url);
        this.resource = WebResourceFactory.newResource(apiClass, this.target);
    }
}
