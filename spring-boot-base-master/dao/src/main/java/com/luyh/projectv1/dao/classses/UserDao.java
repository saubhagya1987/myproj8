package com.luyh.projectv1.dao.classses;

import com.luyh.projectv1.model.User;

public interface UserDao extends GenericDAO<User, Long> {
	
	public boolean isUserAvailable(String login);
	
	public User loadUserByUsername(String username);
	
}