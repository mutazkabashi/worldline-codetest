package com.worldline.codetest.rest;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

import com.worldline.codetest.domain.Profile;
import com.worldline.codetest.repository.ProfileRepository;
import com.worldline.codetest.repository.ProfileRepositoryMapImp;
import com.worldline.codetest.service.ProfileService;

/**
 * 1- Since we have static service({@code ProfileService} we don't need to create mock here (It is anti pattern and coupling Rest-api with the service, but it is the simplest solution,
 *  and also because the @singelton annotation doesn't work)
 * 2-  Database /Map will hold the data during all the tests , which is not best practices, DB/Map should be cleaned after each unit test but that could lead to other
 * anit pattern issue such as creating another Method in {@code UserResource} to cleanup the map private (static variable profileService). i will keep it as it is for simplicity
 * @author mutaz Abdelgadir
 *
 */
public class ProfileResourceTest {
	
	ProfileApi userResource;
	
	@Before
    public void setup() {
		 ProfileRepository pr = new ProfileRepositoryMapImp();
        ProfileService ps = new ProfileService(pr );
        userResource = new ProfileResource(ps );
    }
	
	@Test
    public void testCreateValidProfileSuccess() {
	   Profile profile = new Profile("Mutaz", "Sudan");
       Response response = userResource.createProfile(profile);
       
       assertEquals(response.getStatus(), 201);

    }
	
	@Test
    public void testCreateInvalidProfileFail() {
	   Profile profile = new Profile("Mutaz", null);
       Response response = userResource.createProfile(profile);
       
       assertEquals(response.getStatus(), 400 );

    }
	
	@Test
    public void testCreateDuplicateProfilesFail() {
	   Profile profile1 = new Profile("Muaaz", "Sudan");
       Response response1 = userResource.createProfile(profile1);
       assertEquals(response1.getStatus(), 201 );
       
       Profile profile2 = new Profile("Muaaz", "Egypt");
       Response response2 = userResource.createProfile(profile2);
       assertEquals(response2.getStatus(), 400 );

    }
	
	@Test
    public void testUpdateExistingProfileSuccess() {
	   Profile profile = new Profile("Ahmed", "Sweden");
       Response createResponse = userResource.createProfile(profile);
       
       assertEquals(createResponse.getStatus(), 201);
       
       Profile updateProfile = new Profile("Ahmed", "Norway");
       Response updateResponse = userResource.updateProfile(updateProfile);
       assertEquals(updateResponse.getStatus(), 201);
       

    }
	
	@Test
    public void testUpdateNonExistingProfileFail() {
	   Profile profile = new Profile("Sameh", "UK");
       Response createResponse = userResource.createProfile(profile);
       
       assertEquals(createResponse.getStatus(), 201);
       
       Profile updateProfile = new Profile("Samir", "France");
       Response updateResponse = userResource.updateProfile(updateProfile);
       assertEquals(updateResponse.getStatus(), 400);
       

    }
	
	@Test
    public void testGetExistingProfileSuccess() {
	   Profile profile = new Profile("Omer", "UK");
       Response createResponse = userResource.createProfile(profile);
       
       assertEquals(createResponse.getStatus(), 201);
       
       Response getResponse = userResource.getProfile("Omer");
       assertEquals(getResponse.getStatus(), 200);
       assertEquals("Omer", ((Profile)getResponse.getEntity()).getName());
       assertEquals("UK", ((Profile)getResponse.getEntity()).getCountry());
       

    }
	
	@Test
    public void testGetNonExistingProfileSuccess() {
       
       Response getResponse = userResource.getProfile("Ammar");
       assertEquals(getResponse.getStatus(), 200);
       assertEquals(null, getResponse.getEntity());

    }

}