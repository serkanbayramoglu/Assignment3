package com.SportyShoes.repository;

import java.time.LocalDateTime;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.SportyShoes.entity.Purchases;

@Repository
public class PurchasesRepository {
	
	@Autowired 
	EntityManager em;
	
	public Purchases findById(Long id){
		return em.find(Purchases.class, id);
	}

	public List<Purchases> findAll(){
		TypedQuery<Purchases> query = em.createQuery("Select p from Purchases p",Purchases.class);
		List<Purchases> result = query.getResultList();
		return result;	
	}
	
	
	public List<Purchases> findByCriterea(LocalDateTime saledate1, LocalDateTime saledate2, Long categoryid, Long brandid){
		String categoryTerm = categoryid==null ? "= product.category.id" : "= ?1";
		String brandTerm = brandid==null ? "= product.brand.id" : "= ?2";
		String saledate1Term = saledate1==null ? "= saledate" : ">= ?3";
		String saledate2Term = saledate2==null ? "= saledate" : "<= ?4";
		
		TypedQuery<Purchases> query = em.createQuery(("Select p from Purchases p Where product.category.id " + categoryTerm + " and product.brand.id " + brandTerm 
				+ " and saledate " + saledate1Term + " and saledate " + saledate2Term ),Purchases.class); 
		if(categoryid!=null){query.setParameter(1, categoryid);}
		if(brandid!=null){query.setParameter(2, brandid);}
		if(saledate1!=null){query.setParameter(3, saledate1);}
		if(saledate2!=null){query.setParameter(4, saledate2);}

		List<Purchases> result = query.getResultList();
		return result;
	}

	
}
