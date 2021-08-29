package com.SportyShoes.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.SportyShoes.entity.Admin;
import com.SportyShoes.entity.Users;

@Repository
public class UsersRepository {

	@Autowired 
	EntityManager em;
	
	public Users findById(Long id){
		return em.find(Users.class, id);
	}

	public List<Users> findAll(){
		TypedQuery<Users> query = em.createQuery("Select u from Users u",Users.class);
		List<Users> result = query.getResultList();
		return result;	
	}
	
	public List<Users> findByUsername(String username){
		TypedQuery<Users> query = em.createQuery("Select u from Users u Where username is ?1",Users.class);
		query.setParameter(1, username);
		List<Users> result = query.getResultList();
		return result;	
	}
	
	public List<Users> findByName(String firstname, String lastname){
		TypedQuery<Users> query = em.createQuery("Select u from Users u Where firstname is ?1 and lastname is ?2",Users.class);
		query.setParameter(1, firstname);
		query.setParameter(2, lastname);
		if (firstname == "") {
			query = em.createQuery("Select u from Users u Where lastname is ?2",Users.class);
			query.setParameter(2, lastname);
		} else {
			if (lastname == "") {
				query = em.createQuery("Select u from Users u Where firstname is ?1",Users.class);
				query.setParameter(1, firstname);
			} 
		}
		List<Users> result = query.getResultList();
		return result;
	}
	
}
