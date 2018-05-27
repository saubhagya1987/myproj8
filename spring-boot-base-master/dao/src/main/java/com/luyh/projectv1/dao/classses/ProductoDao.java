package com.luyh.projectv1.dao.classses;

import java.math.BigDecimal;
import java.util.List;

import com.luyh.projectv1.model.Producto;

public interface ProductoDao extends GenericDAO<Producto, BigDecimal>{
	
	public List<Producto> getProductos();
	
	public List<Producto> getByNombre(String nombreProducto);
	
	public Producto getByCodigo(String codigo);
	
	public boolean isProductExist(Producto producto);
	
	public boolean isProductExist(String codigo);
	
	public boolean deleteByCode(String codigo);
	
}