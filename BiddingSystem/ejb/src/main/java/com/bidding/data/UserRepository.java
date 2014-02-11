package com.bidding.data;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.bidding.model.User;

@Stateless
public class UserRepository {
	
	@Inject
	@PersistenceContext
    private EntityManager em;
	
	public User findById(Long id) {
        return em.find(User.class, id);
    }
	
    public User findByEmail(String email) {
    	
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> user = criteria.from(User.class);
        criteria.select(user).where(cb.equal(user.get("email"), email));
        return em.createQuery(criteria).getSingleResult();
    }
    
    public void saveUser(User user){
    	em.persist(user);
    }

}
