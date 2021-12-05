/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.example.Main;

import com.example.Main.Repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MainTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	@BeforeEach
	public void deleteAllBeforeTests() throws Exception {
		userRepository.deleteAll();
	}

	@Test
	public void shouldReturnRepositoryIndex() throws Exception {

		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(
				jsonPath("$._links.users").exists());
	}

	@Test
	public void shouldCreateEntity() throws Exception {

		mockMvc.perform(post("/users").content(
				"{\"login\": \"Frodo\", \"password\":\"Baggins\"}")).andExpect(
						status().isCreated()).andExpect(
								header().string("Location", containsString("users/")));
	}

	@Test
	public void shouldRetrieveEntity() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/users").content(
				"{\"login\": \"Frodo\", \"password\":\"Baggins\"}")).andExpect(
						status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
				jsonPath("$.login").value("Frodo")).andExpect(
						jsonPath("$.password").value("Baggins"));
	}

	@Test
	public void shouldQueryEntity() throws Exception {

		mockMvc.perform(post("/users").content(
				"{ \"login\": \"Frodo\", \"password\":\"Baggins\"}")).andExpect(
						status().isCreated());

		mockMvc.perform(
				get("/users/search/findByLastName?name={login}", "Baggins")).andExpect(
						status().isOk()).andExpect(
								jsonPath("$._embedded.users[0].login").value(
										"Frodo"));
	}

	@Test
	public void shouldUpdateEntity() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/users").content(
				"{\"login\": \"Frodo\", \"password\":\"Baggins\"}")).andExpect(
						status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(put(location).content(
				"{\"login\": \"Bilbo\", \"password\":\"Baggins\"}")).andExpect(
						status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
				jsonPath("$.login").value("Bilbo")).andExpect(
						jsonPath("$.password").value("Baggins"));
	}

	@Test
	public void shouldPartiallyUpdateEntity() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/users").content(
				"{\"login\": \"Frodo\", \"password\":\"Baggins\"}")).andExpect(
						status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(
				patch(location).content("{\"login\": \"Bilbo Jr.\"}")).andExpect(
						status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
				jsonPath("$.login").value("Bilbo Jr.")).andExpect(
						jsonPath("$.password").value("Baggins"));
	}

	@Test
	public void shouldDeleteEntity() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/users").content(
				"{ \"login\": \"Bilbo\", \"password\":\"Baggins\"}")).andExpect(
						status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(delete(location)).andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isNotFound());
	}
}
