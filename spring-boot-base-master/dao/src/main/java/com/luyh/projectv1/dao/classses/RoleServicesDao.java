package com.luyh.projectv1.dao.classses;

import java.util.List;

import com.luyh.projectv1.model.Role;


public interface RoleServicesDao {
	
	public List<Role> getAllRoles();
	
	public Role getInfoRole();
	
	public void deleteRole();
	
	public void updateRole();
	
}