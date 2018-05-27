package com.luyh.projectv1.dao.classses;

import com.luyh.projectv1.model.OauthClientDetails;

public interface ClientServicesDao {
	
	public void createClient(OauthClientDetails client);
	
	public boolean isUserAvailable(String clientId);
	
	public OauthClientDetails getClientById(String clientId);
	
	public OauthClientDetails actualizar(OauthClientDetails client);
	
	public void eliminar(OauthClientDetails client);
	
}
