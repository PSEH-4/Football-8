package com.football.service;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.football.controller.StandingNotFoundException;
import com.football.controller.StandingsController;
import com.football.model.Satnding;

@SpringBootTest(classes=StandingsController.class)
public class StandingServiceTest {
	
	@MockBean
	private StandingsController standingsController;

	@Mock
	StandingsService standingsService;

	
	@Test
    public void getStandingsServiceTest() throws StandingNotFoundException 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Satnding s = new Satnding(41,"England",148,"Premier League",2626,"Manchester City",2);
         
        when(standingsService.getStanding("England", "Premier League", "Manchester City")).thenReturn(s);
         
    }
}
