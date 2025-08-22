package com.example.hiborm.model;

import jakarta.persistence.*;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column(unique=true)
	private String email;
	
	//No args constructor
	public Employee() {
	    super();
	    // TODO Auto-generated constructor stub
	}

	//Args based constructor
	public Employee(Long id, String name, String email) {
	    super();
	    this.id = id;
	    this.name = name;
	    this.email = email;
	}
	
	public Employee(String name, String email) {
	    this.name = name;
	    this.email = email;
	}
	
	//getters & setters
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	

	
}