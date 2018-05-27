package com.luyh.projectv1.dao.classses;

import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.luyh.projectv1.model.User;

@Repository("userDao")
@Transactional
public class UserDaoImpl extends GenericDAOImpl<User, Long> implements UserDao{

	public UserDaoImpl() {
		super(User.class);
	}

	@Override
	public boolean isUserAvailable(String login) {
		Assert.notNull(login);
		
		StringBuilder sQuery = new StringBuilder();
		
		sQuery.append("SELECT count(*) \n");
		sQuery.append("FROM ");
		sQuery.append(getClazz().getSimpleName());
		sQuery.append(" u \n");
		sQuery.append("WHERE u.login = :ipLogin");
		
		Query query = getSessionFactory().getCurrentSession().createQuery(sQuery.toString());
		query.setParameter("ipLogin", login);
		
		Long count = (Long) query.list().get(0);
		
		return count < 1;
	}

	@Override
	//@InfoLogger(origen = "loadUserByUsername")
	public User loadUserByUsername(String username) {
		Assert.notNull(username);
		
		User user = null;
		
		StringBuilder sQuery = new StringBuilder();
		
		sQuery.append("SELECT u \n");
		sQuery.append("FROM ");
		sQuery.append(getClazz().getSimpleName());
		sQuery.append(" u \n");
		sQuery.append("WHERE u.login = :ipLogin");
		
		System.out.println("* * * * * * * * * * * * * * * * * * * * * * * USERNAME: " + username + sQuery.toString());
		
		try{
			user = (User) getSessionFactory()
					                    .getCurrentSession()
					                    .createQuery(sQuery.toString())
					                    .setParameter("ipLogin", username)
					                    .uniqueResult();			
		}catch (NoResultException nre){
				 System.out.println("No se encontro el usuario --> " + nre.toString());
		}
		
		return user;
	}

}