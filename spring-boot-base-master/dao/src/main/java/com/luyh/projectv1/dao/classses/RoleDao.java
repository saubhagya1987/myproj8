package com.luyh.projectv1.dao.classses;

import java.util.List;

import com.luyh.projectv1.model.Role;


public interface RoleDao extends GenericDAO<Role, Long> {
	
	public List<Role> getRoleListNq();
	
}