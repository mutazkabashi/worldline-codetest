package com.worldline.codetest.rest.dto;

import java.util.List;

public class DocumentDto {
	
	public DocumentDto(List<String> documentUrls) {
		super();
		this.documentUrls = documentUrls;
	}

	List<String> documentUrls;

	public List<String> getDocumentUrls() {
		return documentUrls;
	}

	public void setDocumentUrls(List<String> documentUrls) {
		this.documentUrls = documentUrls;
	}

}
