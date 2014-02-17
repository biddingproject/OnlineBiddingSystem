package com.bidding.data;

import com.bidding.model.User;
import com.bidding.model.UserRole;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by kavi on 2/13/14.
 */
@Stateless
public class UserRoleRepository {

    @Inject
    @PersistenceContext
    private EntityManager entityManager;

    public UserRole findByUserRoleId(Long userRoleId) {
        return entityManager.find(UserRole.class, userRoleId);
    }

    public UserRole findByUserRoleName(String userRoleName) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserRole> criteria = cb.createQuery(UserRole.class);
        Root<UserRole> userRole = criteria.from(UserRole.class);
        criteria.select(userRole).where(cb.equal(userRole.get("roleName"), userRoleName));
        return entityManager.createQuery(criteria).getSingleResult();
    }
}
