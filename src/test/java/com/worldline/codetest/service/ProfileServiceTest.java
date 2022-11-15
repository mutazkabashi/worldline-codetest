package com.worldline.codetest.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.worldline.codetest.domain.Profile;
import com.worldline.codetest.exception.ProfileIsAlreadyExistException;
import com.worldline.codetest.exception.ProfileIsNotExistException;
import com.worldline.codetest.repository.ProfileRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProfileServiceTest {
	
	@Mock
	ProfileRepository profileRepository;
	@InjectMocks
	ProfileService profileService;

   @Test
   public void testCreateProfileSuccess() throws ProfileIsAlreadyExistException {
	   //Given
	   Profile profile = new Profile("Mutaz","Sudan");
	   //when
	   when(profileRepository.createProfile(any())).thenReturn(profile);
	   //then
	   Profile resultProfile = profileService.createProfile(profile);
	   assertEquals(profile, resultProfile);
	   
	   //verify
	   verify(profileRepository,times(1)).createProfile(profile);
   }
   
   @Test(expected = ProfileIsAlreadyExistException.class)
   public void testCreateAnExistedProfileFail() throws ProfileIsAlreadyExistException {
	 //Given
	   Profile profile = new Profile("Mutaz","Sudan");
	   //when
	   when(profileRepository.createProfile(any())).thenThrow(new ProfileIsAlreadyExistException("Profile is already Exist"));
	   //then
	   profileService.createProfile(profile);
	  
   }
   
   @Test
   public void testUpdateProfileSuccess() throws ProfileIsNotExistException {
	   //Given
	   Profile profile = new Profile("Mutaz","Sudan");
	   //when
	   when(profileRepository.updateProfile(any())).thenReturn(profile);
	   //then
	   Profile resultProfile = profileService.updateProfile(profile);
	   assertEquals(profile, resultProfile);
	   
	   //verify
	   verify(profileRepository,times(1)).updateProfile(profile);
   }
   
   @Test(expected = ProfileIsNotExistException.class)
   public void testUpdateNonExistedProfileFail() throws ProfileIsNotExistException {
	 //Given
	   Profile profile = new Profile("Mutaz","Sudan");
	   //when
	   when(profileRepository.updateProfile(any())).thenReturn(null);
	   //then
	   profileService.updateProfile(profile);
   }
   
   @Test
   public void testGetExisitngProfileSuccess() {
	   //Given
	   Profile profile = new Profile("Mutaz","Sudan");
	   //when
	   when(profileRepository.getProfile(any())).thenReturn(profile);
	   //then
	   Profile resultProfile = profileService.getProfile("Mutaz");
	   assertEquals(profile, resultProfile);
	   
	   //verify
	   verify(profileRepository,times(1)).getProfile("Mutaz");
   }

}
