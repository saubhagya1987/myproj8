package com.luyh.projectv1.dao.classses;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import com.luyh.projectv1.model.Cliente;

public interface ClienteDao extends GenericDAO<Cliente, BigDecimal> {
	
	public Cliente getClienteByCedula(BigInteger cedula);
	
	public boolean isExist(Cliente cliente);
	
	public boolean isExistByCedula(BigInteger cedula);
	
	public boolean deleteClienteByCedula(BigInteger cedula);
	
	public List<Cliente> getClientes(Integer offset, Integer limit);
	
}