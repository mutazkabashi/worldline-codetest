package com.worldline.codetest.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.worldline.codetest.domain.Profile;
import com.worldline.codetest.exception.ProfileIsAlreadyExistException;

@RunWith(MockitoJUnitRunner.class)
public class ProfileRepositoryMapImpTest {

	@InjectMocks
	ProfileRepositoryMapImp profileRepositoryMapImp;

	@Test
	public void testCreateProfileSuccess() throws ProfileIsAlreadyExistException {
		// Given
		Profile profile = new Profile("Mutaz", "Sudan");
		// then
		Profile resultProfile = profileRepositoryMapImp.createProfile(profile);
		assertEquals(profile, resultProfile);

	}

	@Test(expected = ProfileIsAlreadyExistException.class)
	public void testCreateAnExistingProfileFail() throws ProfileIsAlreadyExistException {
		// Given
		Profile profile = new Profile("Mutaz", "Sudan");
		// then
		profileRepositoryMapImp.createProfile(profile);
		profileRepositoryMapImp.createProfile(profile);
	}

	@Test
	public void testGetAnExistingProfileReturnProfile() throws ProfileIsAlreadyExistException {
		// Given
		Profile profile = new Profile("Mutaz", "Sudan");
		// when
		profileRepositoryMapImp.createProfile(profile);
		Profile resultProfile = profileRepositoryMapImp.getProfile("Mutaz");

		// then
		assertEquals(profile, resultProfile);
	}

	@Test
	public void testGetNonExistingProfileReturnNull() throws ProfileIsAlreadyExistException {
		// Given
		Profile profile = new Profile("Mutaz", "Sudan");
		// when
		profileRepositoryMapImp.createProfile(profile);
		Profile resultProfile = profileRepositoryMapImp.getProfile("Ahmed");

		// then
		assertEquals(null, resultProfile);
	}

	@Test
	public void testUpdateAnExistingProfileReturnUpdatedProfile() throws ProfileIsAlreadyExistException {
		// Given
		Profile profile = new Profile("Mutaz", "Sudan");
		Profile updatedProfile = new Profile("Mutaz", "Sweden");
		// when
		profileRepositoryMapImp.createProfile(profile);
		Profile resultProfile = profileRepositoryMapImp.updateProfile(updatedProfile);

		// then
		assertEquals(updatedProfile, resultProfile);
	}

	@Test
	public void testUpdateNonExistingProfileReturnUpdatedProfile() throws ProfileIsAlreadyExistException {
		// Given
		Profile profile = new Profile("Mutaz", "Sudan");
		// when
		Profile resultProfile = profileRepositoryMapImp.updateProfile(profile);

		// then
		assertEquals(null, resultProfile);
	}
}
