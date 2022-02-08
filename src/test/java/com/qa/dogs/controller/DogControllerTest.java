package com.qa.dogs.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.dogs.domain.Dogs;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:dog-schema.sql", "classpath:dog-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles
public class DogControllerTest {

	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ObjectMapper map;
	
	@Test
	void testCreate() throws Exception {
		Dogs newDog = new Dogs("Cadie", 3);
		
		String newDogJSON = this.map.writeValueAsString(newDog);
		
		RequestBuilder mockRequest = post("/createDog").contentType(MediaType.APPLICATION_JSON);
		
		Dogs savedDog = new Dogs (2L, "Cadie", 2);
		
		String savedDogJSON = this.map.writeValueAsString(savedDog);
		
		ResultMatcher matchStatus = status().isCreated();
		
		ResultMatcher matchBody = content().json(savedDogJSON);
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
	}
	
	@Test
	void testRead() throws Exception {
		Dogs readDog = new Dogs(1L, "Diego", 2);
		List<Dogs> allDogs = List.of(readDog);
		String readDogJSON = this.map.writeValueAsString(allDogs);
		
		RequestBuilder readRequest = get("/getDog");
		
		ResultMatcher status = status().isOk();
		ResultMatcher body = content().json(readDogJSON);
		
		this.mock.perform(readRequest).andExpect(status).andExpect(body);
		
	}
	
	@Test
	void testUpdate() throws Exception{
		Dogs updateDog = new Dogs("Lola", 1);
		String updateDogJSON = this.map.writeValueAsString(updateDog);
		Long IdUpdate = 1L;
		
		RequestBuilder updateRequest = put("/updatedog/" + IdUpdate).contentType(MediaType.APPLICATION_JSON).content(updateDogJSON);
		
		Dogs retUpdatedDog = new Dogs(1L, "Lola", 1);
		String retUpdatedDogJSON = this.map.writeValueAsString(retUpdatedDog);
		
		ResultMatcher retStatus = status().isOk();
		ResultMatcher retBody = content().json(retUpdatedDogJSON);
		
		this.mock.perform(updateRequest).andExpect(retStatus).andExpect(retBody);
		
	}
	
	@Test
	void testDelete() throws Exception{
		Dogs deleteDog = new Dogs(1L, "Cadie", 3);
		String deleteDogJSON = this.map.writeValueAsString(deleteDog);
		
		Long removeId = 1L;
		RequestBuilder deleteRequest = delete("/deleteDog/" + removeId);
		ResultMatcher Status = status().isOk();
		ResultMatcher Body = content().json(deleteDogJSON);
		
		this.mock.perform(deleteRequest).andExpect(Status).andExpect(Body);
	}
	
}
