package com.worldline.codetest.rest;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

/**
 * 1- Since we have static service({@code ProfileService} we don't need to create mock here (It is anti pattern and coupling Rest-api with the service, but it is the simplest solution,
 *  and also because the @singelton annotation doesn't work)
 * 2-  Database /Map will hold the data during all the tests , which is not best practices, DB/Map should be cleaned after each unit test but that could lead to other
 * anit pattern issue such as creating another Method in {@code UserResource} to cleanup the map private (static variable profileService). i will keep it as it is for simplicity
 * @author mutaz Abdelgadir
 *
 */
public class DocumentResourceTest {
	
	DocumentResource documentResource;
	
	@Before
    public void setup() {
		 documentResource = new DocumentResource();
    }
	
	@Test
	public void testGetProfileDocuemntsByValidNameSuccess() {
		Response response = documentResource.getProfileDocuemnts("Mutaz");
		assertEquals(response.getStatus(), 200);

	}

	@Test
	public void testGetProfileDocuemntsByNullNameFail() {
		Response response = documentResource.getProfileDocuemnts(null);
		assertEquals(response.getStatus(), 400);
	}
	
	@Test
	public void testGetProfileDocuemntsByBlankNameFail() {
		Response response = documentResource.getProfileDocuemnts("");
		assertEquals(response.getStatus(), 400);
	}
	
	@Test
	public void testDeleteProfileDocuemntsByNullNameFail() {
		Response response = documentResource.deleteProfileDocuemnts(null);
		assertEquals(response.getStatus(), 400);

	}
	
	@Test
	public void testDeleteProfileDocuemntsByBlankNameFail() {
		Response response = documentResource.deleteProfileDocuemnts("");
		assertEquals(response.getStatus(), 400);

	}
}