package com.worldline.codetest.rest;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import com.worldline.codetest.domain.Profile;
import com.worldline.codetest.exception.ProfileIsAlreadyExistException;
import com.worldline.codetest.exception.ProfileIsNotExistException;
import com.worldline.codetest.rest.messages.MessageConstants;
import com.worldline.codetest.rest.messages.ResponseMessage;
import com.worldline.codetest.service.ProfileService;

public class ProfileResource implements ProfileApi {
	
	private final ProfileService profileService;

	public ProfileResource(ProfileService profileService) {
	    this.profileService = profileService;
	}

	@Override
	public Response createProfile(Profile profile) {
		// TODO Validators
		if (!StringUtils.isAlpha(profile.getName()) || !StringUtils.isAlpha(profile.getCountry())) {
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
	
	@Override
	public Response updateProfile(Profile profile) {

		if (!StringUtils.isAlpha(profile.getName()) || !StringUtils.isAlpha(profile.getCountry())) {
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
    
	@Override
	public Response getProfile(String name) {
		if (!StringUtils.isAlpha(name)) {
			ResponseMessage responseMessage = new ResponseMessage(MessageConstants.PROFILE_PARAMETER_NAME_IS_NOT_VALID);
			return Response.status(400).entity(responseMessage).build();
		}
		Profile profile = profileService.getProfile(name);
		return Response.status(200).entity(profile).build();

	}
}