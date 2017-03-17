package com.websystique.springmvc.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.websystique.springmvc.configuration.CORSFilter;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.service.UserService;


public class HelloWorldRestControllerTest {

	private MockMvc mockMvc;

	@Mock
	private UserService userService;

	@InjectMocks
	private HelloWorldRestController helloWorldRestController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(helloWorldRestController).addFilters(new CORSFilter()).build();
	}

	@Test
	public void test_get_all_success() throws Exception {
		List<User> users = Arrays.asList(new User(1, "Shridevi", "KK", "shri@abc.com"),
				new User(2, "Rupali", "Pune", "rupali@abc.com"));

		when(userService.findAllUsers()).thenReturn(users);
		mockMvc.perform(get("/user/")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[0].username", is("Shridevi")))
				.andExpect(jsonPath("$[0].address", is("KK"))).andExpect(jsonPath("$[0].email", is("shri@abc.com")))
				.andExpect(jsonPath("$[1].id", is(2))).andExpect(jsonPath("$[1].username", is("Rupali")))
				.andExpect(jsonPath("$[1].address", is("Pune")))
				.andExpect(jsonPath("$[1].email", is("rupali@abc.com")));

		verify(userService, times(1)).findAllUsers();
		verifyNoMoreInteractions(userService);
	}
}
