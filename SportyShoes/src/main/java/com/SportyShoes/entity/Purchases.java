package com.SportyShoes.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;

@Entity
public class Purchases {

	@Id
	@GeneratedValue
	private Long id;
	
	private int size;
	private String gender;
	private double saleprice;
	
	@CreatedDate
	private LocalDateTime saledate;

	@ManyToOne
	private Shoes product;
	
	@ManyToOne
	private Users user;
	
	
	public Purchases() {
	}


	public Purchases(int size, String gender, double saleprice, LocalDateTime saledate, Shoes product, Users user) {
		super();
		this.size = size;
		this.gender = gender;
		this.saleprice = saleprice;
		this.saledate = saledate;
		this.product = product;
		this.user = user;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public double getSaleprice() {
		return saleprice;
	}


	public void setSaleprice(double saleprice) {
		this.saleprice = saleprice;
	}


	public LocalDateTime getSaledate() {
		return saledate;
	}


	public void setSaledate(LocalDateTime saledate) {
		this.saledate = saledate;
	}


	public Shoes getProduct() {
		return product;
	}


	public void setProduct(Shoes product) {
		this.product = product;
	}


	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}


	public Long getId() {
		return id;
	}


	@Override
	public String toString() {
		return "Purchases [id=" + id + ", size=" + size + ", gender=" + gender + ", saleprice=" + saleprice
				+ ", saledate=" + saledate +  ", user=" + user + "]";
	}



	
	
	
}







