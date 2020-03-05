package com.football.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.football.model.Satnding;
import com.football.service.StandingsService;


@SpringBootTest(classes=StandingsController.class)
@AutoConfigureMockMvc
public class StandingsControllerTest {

	@MockBean
	private StandingsController standingsController;

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contexLoads() throws Exception {
		assertThat(standingsController).isNotNull();
	}
	
	@Test
	public void getStandingsTest() throws Exception {
		Satnding s = new Satnding(41,"England",148,"Premier League",2626,"Manchester City",2);
	
		String uri = "http://localhost:8081/football?countryName=England&leagueName=Premier League&teamName=Manchester City";
	
		assertThat(this.restTemplate.getForObject(uri,String.class)).contains("football");
	}
}
