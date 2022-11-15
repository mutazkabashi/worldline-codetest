package com.worldline.codetest.domain;

import java.util.Objects;

public class Document {

	// It is best practice to have non business PK Field (e.g id), but to keep it
	// simple we just use name as FK here
	// (name is PK in Profile Object)
	String name;
	String documentUrl;

	public Document() {

	}

	public Document(String name, String documentUrl) {
		super();
		this.name = name;
		this.documentUrl = documentUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDocumentUrl() {
		return documentUrl;
	}

	public void setDocumentUrl(String documentUrl) {
		this.documentUrl = documentUrl;
	}

	@Override
	public int hashCode() {
		return Objects.hash(documentUrl, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Document other = (Document) obj;
		return Objects.equals(documentUrl, other.documentUrl) && Objects.equals(name, other.name);
	}
}