package com.football.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.football.controller.StandingNotFoundException;
import com.football.model.Country;
import com.football.model.Satnding;

@Service
public class StandingsService {

	
	Logger logger = LoggerFactory.getLogger(StandingsService.class);
	
	@Autowired
	RestTemplate restTemplate;

	public Satnding getStanding(String countryName, String leagueName, String teamName) 
			throws StandingNotFoundException {

		
		String url = "https://apiv2.apifootball.com/?action=get_standings&league_id=148&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978";
		
		Satnding[] standings = restTemplate.getForObject(url, Satnding[].class);

		for (Satnding s : standings) {
			if (s.getCountry_name().equals(countryName) && s.getLeague_name().equals(leagueName)
					&& s.getTeam_name().equals(teamName)) {

				s.setCountry_id(getCountry(countryName));
				System.out.println(s);
				return s;
			}

		}
		//return null;
		throw new StandingNotFoundException("No matching standing record found");
	}
	
	
	public int getCountry(String countryName) throws StandingNotFoundException {

		Country[] countries = restTemplate.getForObject(
				"https://apiv2.apifootball.com/?action=get_countries&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978",
				Country[].class);
		int countryId = 0;

		for (Country country : countries) {
			if (country.getCountry_name().equals(countryName))
				countryId = country.getCountryIdByCountryName(countryName);
			return countryId;
		}
		throw new StandingNotFoundException("Requested country not found");
		//return 0;
	}
}