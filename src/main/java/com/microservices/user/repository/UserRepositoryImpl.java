package com.microservices.user.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Query;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import com.microservices.user.entity.Users;
@Repository
public class UserRepositoryImpl implements UserRepository{
	//@Autowired
	//private SessionFactory sessionFactory;
	
	@Autowired
    @Qualifier("mysqlSessionFactory")
    private SessionFactory mysqlSessionFactory;

	
	@Autowired
    @Qualifier("postgresSessionFactory")
    private SessionFactory postgresSessionFactoryNew;
	
	public void saveUserDetails(Users user) {
        Session session = mysqlSessionFactory.getCurrentSession();
        try {
            session.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
	public Integer updateUserDetails(Users user) {
	    Session session = mysqlSessionFactory.getCurrentSession();
	    session.beginTransaction();
	    Users existingUser = session.get(Users.class, user.getId());   
	    if (existingUser != null) {
	        Users updatedUser = (Users) session.merge(user);
	        session.getTransaction().commit();
	        return updatedUser.getId();
	    } 
	    session.getTransaction().rollback();
	    return null;
	}

	public List<Users> getUsers() {
	    Session session = mysqlSessionFactory.openSession();
	    Transaction tx = session.beginTransaction(); // Start transaction

	    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	    CriteriaQuery<Users> criteriaQuery = criteriaBuilder.createQuery(Users.class);
	    Root<Users> root = criteriaQuery.from(Users.class);
	    criteriaQuery.select(root);
	    Query<Users> query = session.createQuery(criteriaQuery);
	    
	    List<Users> users = query.getResultList();
	    tx.commit(); // Commit transaction
	    session.close(); // Close session

	    return users;
	}


	    @Override
	    public Optional<Users> findByUserName(String userName) {
	        try (Session session = mysqlSessionFactory.openSession()) {
	            Query<Users> query = session.createQuery("FROM Users WHERE userName = :userName", Users.class);
	            query.setParameter("userName", userName);
	            return Optional.ofNullable(query.uniqueResult());
	        }
	    }

		@Override
		public boolean checkUserExistOrNot(String userName) {
			Session session  = mysqlSessionFactory.getCurrentSession();
			String sql ="select count(*) from users where user_name = :userName";
			Long count =((Number)session.createNativeQuery(sql).setParameter("userName", userName).getSingleResult()).longValue();
			return count>0;
		}

	    /*@Override
	    public boolean checkUserExistOrNot(String userName) {
	        Session session = mysqlSessionFactory.getCurrentSession();
	        String sql = "SELECT COUNT(*) FROM users WHERE user_name = :userName";

	        Long count = ((Number) session.createNativeQuery(sql)
	                        .setParameter("userName", userName)
	                        .getSingleResult()).longValue();

	        return count > 0;
	    }*/
	    
	    

	    
	    



}
