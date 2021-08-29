package com.SportyShoes.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Shoes {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique = true)
	private String serialno;
	
	private Double currentprice;

	@Column(nullable = false)
	private boolean displayshoe;

	@ManyToOne
	private Categories category;
	
	@ManyToOne
	private Brands brand;
	
	@OneToMany(mappedBy="product")
	private List<Purchases> purchases;
	
	public Shoes() {
		
	}

	public Shoes(String serialno, Double currentprice, boolean displayshoe, Categories category, Brands brand,
			List<Purchases> purchases) {
		super();
		this.serialno = serialno;
		this.currentprice = currentprice;
		this.displayshoe = displayshoe;
		this.category = category;
		this.brand = brand;
		this.purchases = purchases;
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public Double getCurrentprice() {
		return currentprice;
	}

	public void setCurrentprice(Double currentprice) {
		this.currentprice = currentprice;
	}

	public Categories getCategory() {
		return category;
	}

	public void setCategory(Categories category) {
		this.category = category;
	}

	public Brands getBrand() {
		return brand;
	}

	public void setBrand(Brands brand) {
		this.brand = brand;
	}

	public Long getId() {
		return id;
	}

	public boolean isDisplayshoe() {
		return displayshoe;
	}

	public void setDisplay(boolean displayshoe) {
		this.displayshoe = displayshoe;
	}

	public List<Purchases> getPurchases() {
		return purchases;
	}

	public void addPurchase(Purchases purchase) {
		this.purchases.add(purchase);
	}
	
	public void removePurchase(Purchases purchase) {
		this.purchases.remove(purchase);
	}

	@Override
	public String toString() {
		return "Shoes [id=" + id + ", serialno=" + serialno + ", currentprice=" + currentprice + ", displayshoe="
				+ displayshoe + ", category=" + category + ", brand=" + brand + "]";
	}


	
}





