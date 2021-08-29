package com.SportyShoes.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.SportyShoes.entity.Brands;
import com.SportyShoes.entity.Categories;
import com.SportyShoes.entity.Shoes;

@Repository
public class ShoesRepository {

	@Autowired 
	EntityManager em;
	
	@Autowired 
	BrandsRepository brandsRepository;
	
	@Autowired
	CategoriesRepository categoriesRepository;
	
	public Shoes findById(Long id){
		return em.find(Shoes.class, id);
	}

	public List<Shoes> findAll(){
		TypedQuery<Shoes> query = em.createQuery("Select s from Shoes s",Shoes.class);
		List<Shoes> result = query.getResultList();
		return result;	
	}
	
	public List<Shoes> findBySerialno(String serialno){
		TypedQuery<Shoes> query = em.createQuery("Select s from Shoes s Where serialno = ?1",Shoes.class);
		query.setParameter(1, serialno);
		List<Shoes> result = query.getResultList();
		return result;	
	}
	
	public String save(Long id, String serialno, boolean entdisplay, Double currentprice, Long brandid, Long categoryid){
		Shoes data = new Shoes();
		String response = null;

			if (findById(id) == null) {
				
				if (findBySerialno(serialno).size() == 0) {

					data.setSerialno(serialno);	
					data.setBrand(brandsRepository.findById(brandid));
					data.setCategory(categoriesRepository.findById(categoryid));
					data.setCurrentprice(currentprice);
					data.setDisplay(entdisplay);
					em.persist(data);
					response = "The the product under serial number, " + serialno + " is added!";
				} else {
					response = "A product with the serial number, " + serialno + " already exists!";
				}
			} else {
				
				if ((findBySerialno(serialno).size() == 0) || findById(id).getSerialno().equals(serialno)) {

				data = findById(id);
				data.setSerialno(serialno);	
				data.setBrand(brandsRepository.findById(brandid));
				data.setCategory(categoriesRepository.findById(categoryid));
				data.setCurrentprice(currentprice);
				data.setDisplay(entdisplay);
				em.merge(data);
				response = "The product entry under ID, " + id + " is updated!";
				} else {
					response = "A product with the serial number, " + serialno + " already exists!";
				}
			}

		return response;
	}
	
	public String deleteById(Long id) {
		Shoes data = findById(id);
		String response = null;
		if (findById(id) !=null) {
			if (data.getPurchases().size() == 0) {
				em.remove(data);
				response = "Deleted successfully";
			} else {
				response =  "This category is used in Purchases table, therefore cannot be deleted. You can set the display to false instead of deleting.";
			}
		} else {
			response = "This id, " + id + " does not exist!";
		}
		return response;
	}
}
