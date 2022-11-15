package com.worldline.codetest.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.worldline.codetest.domain.Document;
/**
 * Map DataStore implementation for {@code DocumentRepository}, Map consists of
 * profile-name as a key , and List of Docuemnt's Url, so each profile's name links 
 * to list of profile's documents. Profile's name also work as a foreign key from 
 * {@code ProfileRepositoryMapImp} Map, so profile should be created first before 
 * uploading documents
 * @author mutaz.Abdelgadir
 *
 */
public class DocumentRepositoryMapImp implements DocumentRepository{
	
	Map<String, List<String>> documents = new HashMap<>();

	@Override
	public void createOrUpdateDocument(Document document) {
		if(documents.get(document.getName())==null) {
			List<String> documentUrls = new ArrayList<>();
			documentUrls.add(document.getDocumentUrl());
			documents.put(document.getName(), documentUrls);
		}
		else {
			documents.get(document.getName()).add(document.getDocumentUrl());
		}
		return ;
	}

	@Override
	public List<String> getProfileDocuemnts(String name) {
	   return documents.get(name);			
	}
	
	@Override
	public List<String> deleteProfileDocuemnts(String name) {
	   return documents.remove(name);		
	}

}
