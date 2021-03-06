package com.bidding.data;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.bidding.model.Customer;
import com.bidding.model.Seller;
import com.bidding.model.User;
import com.bidding.model.UserRole;

import java.util.Iterator;
import java.util.List;

@Stateless
public class UserRepository {

	@Inject
	@PersistenceContext
	private EntityManager em;

	public User findById(Long id) {
		return em.find(User.class, id);
	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	public User findByEmail(String email) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		Root<User> user = criteria.from(User.class);
		criteria.select(user).where(cb.equal(user.get("email"), email));
		return em.createQuery(criteria).getSingleResult();
	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	public User getUserByEmail(String email) {
		User user = null;
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder
				.createQuery(User.class);
		Root<User> getUser = criteriaQuery.from(User.class);
		criteriaQuery.select(getUser).where(
				criteriaBuilder.equal(getUser.get("email"), email));
		user = em.createQuery(criteriaQuery).getSingleResult();
		System.out.println("user name is " + user.getEmail());
		return user;
	}

	/**
	 * 
	 * @param firstName
	 * @return
	 */
	public List<User> getUsersByUserName(String firstName) {
		List<User> users;
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder
				.createQuery(User.class);
		Root<User> getUser = criteriaQuery.from(User.class);
		criteriaQuery.select(getUser).where(
				criteriaBuilder.equal(getUser.get("userName"), firstName));
		users = em.createQuery(criteriaQuery).getResultList();
		return users;
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	public boolean saveUser(User user) {

		List<UserRole> roles = user.getUserRoles();
		for (Iterator iterator = roles.iterator(); iterator.hasNext();) {
			UserRole userRole = (UserRole) iterator.next();
			userRole.setUser(user);
		}
		user.setUserRoles(user.getUserRoles());
		try {
			
			Seller seller = new Seller();
			Customer customer = new Customer();
			
			em.persist(seller);
			em.persist(customer);
			em.persist(user);
			
			customer.setUser(user);
			seller.setUser(user);
			
			user.setCustomer(customer);
			user.setSeller(seller);
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 
	 * @param user
	 */
	public void remove(User user) {
		user = em.find(User.class, user.getId());
		Customer cutomer = user.getCustomer();
		cutomer.setUser(null);
		user.setCustomer(null);
		em.remove(cutomer);
		em.remove(user);

	}

	/**
	 * 
	 * @param imageBytes
	 * @param email
	 */
	public void updateProfilePicture(byte[] imageBytes, String email) {
		User user = null;
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder
				.createQuery(User.class);
		Root<User> getUser = criteriaQuery.from(User.class);
		criteriaQuery.select(getUser).where(
				criteriaBuilder.equal(getUser.get("email"), email));
		user = em.createQuery(criteriaQuery).getSingleResult();
		user.setProfilePicture(imageBytes);
	}

	/**
	 * 
	 * @param id
	 * @param newPassword
	 */
	public void changePassword(Long id, String newPassword) {
		User user = em.find(User.class, id);
		user.setPassword(newPassword);
	}

	/**
	 * 
	 * @param userTmp
	 */
	public void updateUserInformation(User userTmp) {
		
		User user = em.find(User.class,userTmp.getId());
		user.setAddress(userTmp.getAddress());
		user.setFirstName(userTmp.getFirstName());
		user.setLastName(userTmp.getLastName());
		user.setPhoneNumber(userTmp.getPhoneNumber());
	}

}
