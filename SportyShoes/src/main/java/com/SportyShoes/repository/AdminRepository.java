package com.SportyShoes.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.SportyShoes.entity.Admin;

@Repository
public class AdminRepository {
	
	@Autowired 
	EntityManager em;
	
	public Admin findById(Long id){
		return em.find(Admin.class, id);
	}

	public Admin findByUsername(String username){
		TypedQuery<Admin> query = em.createQuery("Select a from Admin a Where username is ?1",Admin.class);
		query.setParameter(1, username);
		List<Admin> result1 = query.getResultList();
		Admin result = new Admin();
		if (result1.size() > 0) {
			result = result1.get(0);
		} 	
		return result;
	}
	
	public Admin save(String username, String password){
		Admin data = findByUsername(username);
		data.setPassword(password);
		em.merge(data);
		return data;
	}
	
}
