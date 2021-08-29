package com.SportyShoes.repository;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.SportyShoes.entity.Brands;

@Repository
public class BrandsRepository {

	@Autowired 
	EntityManager em;
	
	public Brands findById(Long id){
		return em.find(Brands.class, id);
	}

	public List<Brands> findAll(){
		TypedQuery<Brands> query = em.createQuery("Select b from Brands b",Brands.class);
		List<Brands> result = query.getResultList();
		return result;	
	}
	
	public List<Brands> findByBrandname(String brandname){
		TypedQuery<Brands> query = em.createQuery("Select b from Brands b Where brandname = ?1",Brands.class);
		query.setParameter(1, brandname);
		List<Brands> result = query.getResultList();
		return result;	
	}
	
	public String save(Long id, String newBrandname){
		Brands data = new Brands();
		String response = null;
		if (findByBrandname(newBrandname).size() == 0) {
			if (findById(id) == null) {
				data.setBrandname(newBrandname);			
				em.persist(data);
				response = "The brandname, " + newBrandname + " is added!";
			} else {
				data = findById(id);
				data.setBrandname(newBrandname);
				em.merge(data);
				response = "The brandname under ID, " + id + " is changed to " + newBrandname + "!";
			}
		} else {
			response = "The brandname, " + newBrandname + " already exists!";
		}
		return response;
	}
	
	public String deleteById(Long id) {
		Brands data = findById(id);
		String response = null;
		if (findById(id) !=null) {
			if (data.getShoes().size() == 0) {
				em.remove(data);
				response =  "Deleted successfully";
			} else {
				response =  "This brand is used in Products table, therefore cannot be deleted. Please amend the Products table first";
			}
		} else {
			response = "This id, " + id + " does not exist!";
		}
		return response;
	}
	
}
