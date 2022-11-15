package com.worldline.codetest.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import com.worldline.codetest.MainApp;
import com.worldline.codetest.domain.Profile;
import com.worldline.codetest.exception.ProfileIsAlreadyExistException;
import com.worldline.codetest.exception.ProfileIsNotExistException;
import com.worldline.codetest.rest.messages.MessageConstants;
import com.worldline.codetest.rest.messages.ResponseMessage;
import com.worldline.codetest.service.ProfileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.inject.Singleton;


@Path("/profiles")
@Api(value = "Profile", description = "Endpoint for Profile specific operations")
@Singleton
public class ProfileResource {
	
	//TODO this is anti pattern, we should use constructor to inject/inject class's fields , but @singelton dosent work , i dont know why
	// thats why i make this property/field as static so the DB/Map will hold the data during the application life time, other wise 
	//new map will be created every time we call this class
	private static ProfileService profileService = MainApp.profileService;
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns param", notes = "Returns param", response = Profile.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = MessageConstants.PROFILE_CREATED_SUCESSFULLY, response = Profile.class),
            @ApiResponse(code = 400, message = MessageConstants.PROFILE_PARAMETERS_ARE_NOT_VALID, response = ResponseMessage.class)})
    public Response createProfile(Profile profile) {

        //TODO Validators
		if(!StringUtils.isAlpha(profile.getName()) || !StringUtils.isAlpha(profile.getCountry())) {
			ResponseMessage responseMessage = new ResponseMessage(MessageConstants.PROFILE_PARAMETERS_ARE_NOT_VALID);
        	return Response.status(400).entity(responseMessage).build();
        }
        Profile result;
		try {
			result = profileService.createProfile(profile);
			return Response.status(201).entity(result).build();
		} catch (ProfileIsAlreadyExistException e) {
			ResponseMessage responseMessage = new ResponseMessage(MessageConstants.PROFILE_IS_ALREADY_EXIST);
			 return Response.status(400).entity(responseMessage).build();
		}
        

    }
	
	@PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns param", notes = "Returns param", response = Profile.class)
	@ApiResponses(value = {
			 @ApiResponse(code = 201, message = MessageConstants.PROFILE_UPDATED_SUCESSFULLY, response = Profile.class),
	         @ApiResponse(code = 400, message = MessageConstants.PROFILE_NOT_EXIST, response = ResponseMessage.class)})
    public Response updateProfile(Profile profile) {

		if(!StringUtils.isAlpha(profile.getName()) || !StringUtils.isAlpha(profile.getCountry())) {
			ResponseMessage responseMessage = new ResponseMessage(MessageConstants.PROFILE_PARAMETERS_ARE_NOT_VALID);
        	return Response.status(400).entity(responseMessage).build();
        }
        
		try {
			 profileService.updateProfile(profile);
			 return Response.status(201).entity(profile).build();
		} catch (ProfileIsNotExistException e) {
			ResponseMessage responseMessage = new ResponseMessage(MessageConstants.PROFILE_NOT_EXIST);
			 return Response.status(400).entity(responseMessage).build();
		}

    }
    

    @Path("/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns param", notes = "Returns param", response = Profile.class)
    @ApiResponses(value = {
			 @ApiResponse(code = 200, message = "OK", response = Profile.class),
	         @ApiResponse(code = 400, message = MessageConstants.PROFILE_PARAMETER_NAME_IS_NOT_VALID, response = ResponseMessage.class)})
    public Response getProfile(@PathParam("name") String name) {
    	if(!StringUtils.isAlpha(name)) {
			ResponseMessage responseMessage = new ResponseMessage(MessageConstants.PROFILE_PARAMETER_NAME_IS_NOT_VALID);
        	return Response.status(400).entity(responseMessage).build();
        }
       Profile profile = profileService.getProfile(name);
       return Response.status(200).entity(profile).build();

    }

}