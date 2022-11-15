package com.worldline.codetest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.worldline.codetest.rest.DocumentResource;
import com.worldline.codetest.rest.ProfileResource;

import io.swagger.jaxrs.config.BeanConfig;

/**
 * Application Configuration Class which Load Resources/Rest enpoints and Swagger UI generator classes
 * @author Mutaz Abdelgadir
 *
 */
public class FatJarApplication extends Application {

    HashSet<Object> singletons = new HashSet<Object>();

    public FatJarApplication() {
        configureSwagger();
    }

    @Override
    public Set<Class<?>> getClasses() {

		HashSet<Class<?>> set = new HashSet<Class<?>>();
        //end-points
		set.add(ProfileResource.class);
		set.add(DocumentResource.class);
        //Swagger classes
		set.add(io.swagger.jaxrs.listing.ApiListingResource.class);
		set.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
		return set;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

    private void configureSwagger() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[] { "http" });
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/api");
        beanConfig.setResourcePackage(ProfileResource.class.getPackage().getName());
        beanConfig.setTitle("Worldline Code Test, Fat JAR, Swagger UI ");
        beanConfig.setDescription("RESTful API built using RESTEasy, Swagger and Swagger UI.");
        beanConfig.setScan(true);
    }
}
