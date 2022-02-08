package com.qa.dogs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.dogs.domain.Dogs;

@RestController
@RequestMapping("/dogs")
public class DogsController {

	private DogsService dogService;
	
	public DogsController(DogsService dogService) {
		this.dogService = dogService;
	}
	
	//Create
	@PostMapping("/createDog")
	public ResponseEntity<Dogs> createDog(@RequestBody Dogs a){
		return new ResponseEntity<Dogs>(this.dogService.create(a), HttpStatus.CREATED);
	}
}
