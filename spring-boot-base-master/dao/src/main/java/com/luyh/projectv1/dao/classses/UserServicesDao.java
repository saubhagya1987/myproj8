package com.luyh.projectv1.dao.classses;

import java.util.List;

import com.luyh.projectv1.model.Role;
import com.luyh.projectv1.model.User;

public interface UserServicesDao {
	
	public void createUser(User u, List<Role> role);
	
	public boolean isUserAvailable(String username);
	
	public User getUserByLogin(String login);
	
	public User actualizar(User u);
	
	public void eliminar(User u);
	
}