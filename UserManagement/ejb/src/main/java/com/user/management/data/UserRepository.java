package com.user.management.data;

import com.user.management.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kavi on 2/10/14.
 */
@ApplicationScoped
public class UserRepository {

    @Inject
    private EntityManager entityManager;

    public User getUserByUserId(Long userId) {
        User user = null;
        user = entityManager.find(User.class, userId);
        return user;
    }

    public User getUserByEmail(String email) {
        User user = null;
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> getUser = criteriaQuery.from(User.class);
        criteriaQuery.select(getUser).where(criteriaBuilder.equal(getUser.get("email"), email));
        user = entityManager.createQuery(criteriaQuery).getSingleResult();
        return user;
    }

    public List<User> getUsersByFirstName(String firstName) {
        List<User> users;
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> getUser = criteriaQuery.from(User.class);
        criteriaQuery.select(getUser).where(criteriaBuilder.equal(getUser.get("firstName"), firstName));
        users = entityManager.createQuery(criteriaQuery).getResultList();
        return users;
    }
}
