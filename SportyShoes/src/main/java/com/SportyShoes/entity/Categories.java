package com.SportyShoes.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categories {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique = true)
	private String categoryname;
	
	@OneToMany(mappedBy="category")
	private List<Shoes> shoes = new ArrayList();
	
	
	public Categories() {
		
	}

	public Categories(String categoryname) {
		super();
		this.categoryname = categoryname;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
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
		return "Categories [id=" + id + ", categoryname=" + categoryname + "]";
	}
	
}
