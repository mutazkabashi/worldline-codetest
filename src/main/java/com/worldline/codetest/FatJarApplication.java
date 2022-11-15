package com.worldline.codetest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.worldline.codetest.repository.DocumentRepositoryMapImp;
import com.worldline.codetest.repository.ProfileRepositoryMapImp;
import com.worldline.codetest.rest.DocumentResource;
import com.worldline.codetest.rest.ProfileApi;
import com.worldline.codetest.rest.ProfileResource;
import com.worldline.codetest.service.DocumentService;
import com.worldline.codetest.service.ProfileService;

import io.swagger.jaxrs.config.BeanConfig;

/**
 * Application Configuration Class which Load Resources/Rest enpoints and Swagger UI generator classes
 * @author Mutaz Abdelgadir
 *
 */
public class FatJarApplication extends Application {

    private static final HashSet<Object> singletons = new HashSet<Object>();
    private static final HashSet<Class<?>> classes = new HashSet<Class<?>>();

    public FatJarApplication() {
        ProfileService profileService = new ProfileService(new ProfileRepositoryMapImp());
        DocumentService documentService = new DocumentService(new DocumentRepositoryMapImp(), profileService);
        
        ProfileApi profileResource = new ProfileResource(profileService);
        DocumentResource documentResource = new DocumentResource(documentService);
        singletons.add(profileResource);
        singletons.add(documentResource);
        configureSwagger();
    }

    @Override
    public Set<Class<?>> getClasses() {
        //Swagger classes
		classes.add(io.swagger.jaxrs.listing.ApiListingResource.class);
		classes.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
		return classes;
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
