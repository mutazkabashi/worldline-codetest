package com.worldline.codetest.repository;

import java.util.HashMap;
import java.util.Map;

import com.worldline.codetest.domain.Profile;
import com.worldline.codetest.exception.ProfileIsAlreadyExistException;

public class ProfileRepositoryMapImp implements ProfileRepository{
	
	Map<String, Profile> profiles = new HashMap<>();

	@Override
	public Profile createProfile(Profile profile) throws ProfileIsAlreadyExistException {
		if(profiles.get(profile.getName())!=null) {
			throw new ProfileIsAlreadyExistException("Profile is already exist for "+profile.getName()+" user");
		}
		profiles.put(profile.getName(), profile);
		return profile;
	}
	
	@Override
	public Profile updateProfile(Profile profile) {
		 profiles.replace(profile.getName(), profile);
		 return profiles.get(profile.getName());		
	}

	@Override
	public Profile getProfile(String name) {
		return profiles.get(name);
				
	}

}
