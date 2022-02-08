package com.qa.dogs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "dogs")
public class Dogs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NotBlank(message = "Please enter a name")
	private String name;

	@Min(0)
	@Max(20)
	@Column
	private int age;

	// Empty Constructor
	public Dogs() {
		super();
	}

	// Customer-View
	public Dogs(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	// Over-Loading
	public Dogs(Long id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
