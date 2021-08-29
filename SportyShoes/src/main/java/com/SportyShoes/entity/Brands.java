package com.SportyShoes.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Brands {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique = true)
	private String brandname;
	
	@OneToMany(mappedBy="brand")
	private List<Shoes> shoes = new ArrayList();
	
	public Brands() {
		
	}

	public Brands(String brandname) {
		super();
		this.brandname = brandname;
	}

	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	public Long getId() {
		return id;
	}

	public List<Shoes> getShoes() {
		return shoes;
	}

	public void addShoe(Shoes shoes) {
		this.shoes.add(shoes);
	}

	public void removeShoe(Shoes shoes) {
		this.shoes.remove(shoes);
	}

	@Override
	public String toString() {
		return "Brands [id=" + id + ", brandname=" + brandname + "]";
	}
	
	
}
