package com.worldline.codetest.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.worldline.codetest.domain.Document;
import com.worldline.codetest.exception.ProfileIsAlreadyExistException;

@RunWith(MockitoJUnitRunner.class)
public class DocumentRepositoryMapImpTest {

	@InjectMocks
	DocumentRepositoryMapImp documentRepositoryMapImp;

	@Test
	public void testCreateValidDocumentSuccess() throws ProfileIsAlreadyExistException {
		// Given
		Document document  = new Document("Mutaz", "/home/user/documents/img.jpg");
		// then
		documentRepositoryMapImp.createOrUpdateDocument(document);

	}

	public void testGetValidDocumentsByProfileName() throws ProfileIsAlreadyExistException {
		// Given
		Document document = new Document("Mutaz", "/home/user/documents/img.jpg");
		// then
		documentRepositoryMapImp.createOrUpdateDocument(document);
		List<String> documents= documentRepositoryMapImp.getProfileDocuemnts(document.getName());
		assertEquals(documents.size(), 1);
		assertEquals(documents.get(0), document.getDocumentUrl());
	}

}
