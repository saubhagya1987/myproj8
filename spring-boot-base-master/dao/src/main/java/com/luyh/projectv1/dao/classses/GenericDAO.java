package com.luyh.projectv1.dao.classses;

import java.util.List;

public interface GenericDAO<T, ID extends java.io.Serializable> {
	
	public List<T> getAllRows();
	
	public T getById(ID id);
	
	public List<T> getByDescription(String field, String ipParam, String desc);
	
	public T save(T entity);
	
	public void delete(ID id);
	
	public T update(T entity);
	
	public void flush();
	
}