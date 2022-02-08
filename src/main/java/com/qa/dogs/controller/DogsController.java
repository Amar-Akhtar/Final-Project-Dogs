package com.qa.dogs.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	//Read
	@GetMapping("/getDog")
	public ResponseEntity<List<Dogs>> getDog(){
		return new ResponseEntity<List<Dogs>>(this.dogService.getAll(), HttpStatus.OK);
		
	}
	
	//Read-by-Id
	@GetMapping("/getId")
	public ResponseEntity<Dogs> getId(@PathVariable Long id){
		return new ResponseEntity<Dogs>(this.dogService.getById(id), HttpStatus.FOUND);
		
	}
	
	//update
	@PutMapping("/updatedog/{id}")
	public ResponseEntity<Dogs> updateDog(@PathVariable Long id, @RequestBody Dogs b){
		return new ResponseEntity<Dogs>(this.dogService.update(id, b), HttpStatus.OK);
	}
}
