package com.worldline.codetest.repository;

import java.util.List;

import com.worldline.codetest.domain.Document;
/**
 * Database Repository /Database Adaptor which is responsible for interacting with the Database/Data-source
 * You could implement the interface to interact with DB, Map, collection or any dataSource you want
 * @author mutaz.Abdelgadir
 *
 */
public interface DocumentRepository {
	
	/**
	 * Add Profile's document to the data store
	 * @param document
	 */
	public void createOrUpdateDocument(Document document);
			
	/**
	 * Retrive/Return Profile's Documents by profile's name 
	 * @param name
	 * @return
	 */
	public List<String> getProfileDocuemnts(String name);
	
	/**
	 * Delete Profile's Documents by Profile's name
	 * @param name
	 * @return
	 */
	public List<String> deleteProfileDocuemnts(String name);


}
