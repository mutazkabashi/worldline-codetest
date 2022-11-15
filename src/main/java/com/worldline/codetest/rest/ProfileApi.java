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

import com.worldline.codetest.domain.Profile;
import com.worldline.codetest.rest.messages.MessageConstants;
import com.worldline.codetest.rest.messages.ResponseMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/profiles")
@Api(value = "Profile", description = "Endpoint for Profile specific operations")
public interface ProfileApi {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns param", notes = "Returns param", response = Profile.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = MessageConstants.PROFILE_CREATED_SUCESSFULLY, response = Profile.class),
            @ApiResponse(code = 400, message = MessageConstants.PROFILE_PARAMETERS_ARE_NOT_VALID, response = ResponseMessage.class) })
    Response createProfile(Profile profile);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns param", notes = "Returns param", response = Profile.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = MessageConstants.PROFILE_UPDATED_SUCESSFULLY, response = Profile.class),
            @ApiResponse(code = 400, message = MessageConstants.PROFILE_NOT_EXIST, response = ResponseMessage.class) })
    Response updateProfile(Profile profile);

    @Path("/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns param", notes = "Returns param", response = Profile.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Profile.class),
    @ApiResponse(code = 400, message = MessageConstants.PROFILE_PARAMETER_NAME_IS_NOT_VALID, response = ResponseMessage.class) })
    Response getProfile(@PathParam("name") String name);

}