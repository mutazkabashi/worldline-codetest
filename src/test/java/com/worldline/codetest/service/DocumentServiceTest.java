package com.worldline.codetest.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.worldline.codetest.exception.DocumentDeleteException;
import com.worldline.codetest.exception.DocumentUploadException;
import com.worldline.codetest.exception.ProfileIsNotExistException;
import com.worldline.codetest.repository.DocumentRepository;

@RunWith(MockitoJUnitRunner.class)
public class DocumentServiceTest {
	
	@Mock
	DocumentRepository documentRepository;
	@Mock
	ProfileService profileService;

	@InjectMocks
	DocumentService documentService;
	

   @Test(expected = ProfileIsNotExistException.class)
   public void testUploadDocuemntForNonExitedProfileFail() throws ProfileIsNotExistException, DocumentUploadException, IOException {
	   //when
	   when(profileService.getProfile(any())).thenReturn(null);
	   //then
	   documentService.createOrUpdateDocuemnt("fileName", "Mutaz", null);
	   
   }
   
   @Test
   public void testUpdateDocument() throws  DocumentDeleteException {
	   //when
	   when(profileService.getProfile(any())).thenReturn(null);
	   //then
	   documentService.deleteProfileDocuments("Mutaz");	   
   }
}
