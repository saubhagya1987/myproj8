package com.luyh.projectv1.dao.classses;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luyh.projectv1.model.Cliente;

@Repository("clienteDao")
@Transactional
public class ClienteDaoImpl extends GenericDAOImpl<Cliente, BigDecimal> implements ClienteDao {

	public ClienteDaoImpl() {
		super(Cliente.class);
	}
	
	@Override
	public Cliente getClienteByCedula(BigInteger cedula) {
		try {
			if (cedula != null){
				Query query = getSessionFactory().getCurrentSession().getNamedQuery("Cliente.findByCedula");
				query.setParameter("cedula", cedula);
				
				Cliente cliente = null;
				
				if (query.list().size() > 0){
					cliente = (Cliente) query.list().get(0);
				}
				return cliente;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;		
	}

	@Override
	public boolean isExist(Cliente cliente) {
		
		try {
			if (cliente != null){
				Query query = getSessionFactory().getCurrentSession().getNamedQuery("Cliente.findByCedula");
				query.setParameter("cedula", cliente.getCedula());
				
				int numClientes = query.list().size();
				
				if (numClientes != 0)				
					return true;
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean isExistByCedula(BigInteger cedula) {
		try {
			if (cedula != null){
				Query query = getSessionFactory().getCurrentSession().getNamedQuery("Cliente.findByCedula");
				query.setParameter("cedula", cedula);
				
				int numClientes = query.list().size();
				
				if (numClientes != 0)				
					return true;
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deleteClienteByCedula(BigInteger cedula) {
		try {
			if (cedula != null){
				Query query = getSessionFactory().getCurrentSession().getNamedQuery("Cliente.findByCedula");
				query.setParameter("cedula", cedula);
				
				if (query.list().size() > 0){
					Cliente cliente = (Cliente) query.list().get(0);
					
					getSessionFactory().getCurrentSession().delete(cliente);
					
					return true;
				}
				
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<Cliente> getClientes(Integer offset, Integer limit) {
		try{
			Query query = getSessionFactory().getCurrentSession().getNamedQuery("Cliente.findAll");
			
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			
			if (query.list().size() > 0) {
				
				List<Cliente> list = new ArrayList<Cliente>();
				
				for (Object cliente : query.list()){
					if (cliente instanceof Cliente){
						list.add((Cliente)cliente);
					}
				}				
				
				return list;
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
}