package com.worldline.codetest.service;

import com.worldline.codetest.domain.Profile;
import com.worldline.codetest.exception.ProfileIsAlreadyExistException;
import com.worldline.codetest.exception.ProfileIsNotExistException;
import com.worldline.codetest.repository.ProfileRepository;

public class ProfileService {

	private ProfileRepository profileRepository;

	public ProfileService(ProfileRepository profileRepository) {
		super();
		this.profileRepository = profileRepository;
	}

	// Validation is already done on the rest-api end point, it is best practice to
	// make another validation
	// here but i will skip it for simplicity
	public Profile createProfile(Profile profile) throws ProfileIsAlreadyExistException {
		return profileRepository.createProfile(profile);
	}

	public Profile updateProfile(Profile profile) throws ProfileIsNotExistException {
		Profile updatedProfile = profileRepository.updateProfile(profile);
		if (updatedProfile == null) {
			throw new ProfileIsNotExistException("No Profile Found for the following User " + profile.getName());
		}
		return updatedProfile;
	}

	public Profile getProfile(String name) {
		return profileRepository.getProfile(name);
	}
}
