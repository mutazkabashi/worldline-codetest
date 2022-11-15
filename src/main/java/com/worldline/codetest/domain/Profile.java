package com.worldline.codetest.domain;

import java.util.Objects;

public class Profile {

	// It is best practice to have non business PK Field (e.g id), but to keep it
	// simple we just use name as PK here
	String name;
	String country;

	public Profile() {

	}

	public Profile(String name, String country) {
		super();
		this.name = name;
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		return Objects.hash(country, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profile other = (Profile) obj;
		return Objects.equals(country, other.country) && Objects.equals(name, other.name);
	}
	
	
}