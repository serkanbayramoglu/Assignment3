package com.SportyShoes.repository;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.SportyShoes.entity.Categories;

@Repository
public class CategoriesRepository {

	@Autowired 
	EntityManager em;
	
	public Categories findById(Long id){
		return em.find(Categories.class, id);
	}

	public List<Categories> findAll(){
		TypedQuery<Categories> query = em.createQuery("Select c from Categories c",Categories.class);
		List<Categories> result = query.getResultList();
		return result;	
	}
	
	public List<Categories> findByCategoryname(String categoryname){
		TypedQuery<Categories> query = em.createQuery("Select c from Categories c Where categoryname = ?1",Categories.class);
		query.setParameter(1, categoryname);
		List<Categories> result = query.getResultList();
		return result;	
	}
	
	public String save(Long id, String newCategoryname){
		Categories data = new Categories();
		String response = null;
		if (findByCategoryname(newCategoryname).size() == 0) {
			if (findById(id) == null) {
				data.setCategoryname(newCategoryname);			
				em.persist(data);
				response = "The category name, " + newCategoryname + " is added!";
			} else {
				data = findById(id);
				data.setCategoryname(newCategoryname);
				em.merge(data);
				response = "The category name under ID, " + id + " is changed to " + newCategoryname + "!";
			}
		} else {
			response = "The category name, " + newCategoryname + " already exists!";
		}
		return response;
	}
	
	public String deleteById(Long id) {
		Categories data = findById(id);
		String response = null;
		if (findById(id) !=null) {
			if (data.getShoes().size() == 0) {
				em.remove(data);
				response = "Deleted successfully";
			} else {
				response =  "This category is used in Products table, therefore cannot be deleted. Please amend the Products table first";
			}
		} else {
			response = "This id, " + id + " does not exist!";
		}
		return response;
	}

}
