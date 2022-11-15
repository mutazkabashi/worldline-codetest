package com.worldline.codetest.repository;

import com.worldline.codetest.domain.Profile;
import com.worldline.codetest.exception.ProfileIsAlreadyExistException;
/**
 * Database Repository /Database Adaptor which is responsible for interacting with the Database/Data-source
 * You could implement the interface to interact with DB, Map, collection or any dataSource you want
 * @author mutaz.Abdelgadir
 *
 */
public interface ProfileRepository {
	
	/**
	 * Add Profile to the data store
	 * @param document
	 */
	public Profile createProfile(Profile profile) throws ProfileIsAlreadyExistException;
	/**
	 * update Profile's Country by profile's name 
	 * @param name
	 * @return
	 */
	public Profile updateProfile(Profile profile);
		
	/**
	 * Retrive/Return Profile's info by profile's name 
	 * @param name
	 * @return
	 */
	public Profile getProfile(String name);

}
