package com.football.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
	private int country_id;
	private String country_name;
	
	
	public Country(int country_id, String country_name) {
		super();
		this.country_id = country_id;
		this.country_name = country_name;
	}
	
	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCountry_id() {
		return country_id;
	}
	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}
	public String getCountry_name() {
		return country_name;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	
	public int getCountryIdByCountryName(String country_name) {
		return country_id;
	}

	@Override
	public String toString() {
		return "Country {country_id=" + country_id + ", country_name=" + country_name +"}";
	}

	
	
}
