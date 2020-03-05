package com.football;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.football.controller.StandingNotFoundException;
import com.football.controller.StandingsController;
import com.football.model.Satnding;
import com.football.service.StandingsService;


@SpringBootTest(classes= StandingsController.class)
@WebAppConfiguration
public class StandingsControllerTest1 {

	@MockBean
	private StandingsController standingController;
	
	@Mock
	StandingsService standingsService;
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
    WebApplicationContext webApplicationContext;

	   protected void setUp() {
	      mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	   }
	
	@Test
	public void getStandingsPositive() throws Exception {
		Satnding s = new Satnding(41,"England",148,"Premier League",2626,"Manchester City",2);
		String uri = "http://localhost:9001/football?countryName=England&leagueName=Premier League&teamName=Manchester City";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		assertTrue(mvcResult.equals(s));
	}
	
	@Test
    public void getStandingsServiceTest() throws StandingNotFoundException 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Model model;
        Satnding s = new Satnding(41,"England",148,"Premier League",2626,"Manchester City",2);
         
        when(standingsService.getStanding("England", "Premier League", "Manchester City")).thenReturn(s);
         
    }
}
