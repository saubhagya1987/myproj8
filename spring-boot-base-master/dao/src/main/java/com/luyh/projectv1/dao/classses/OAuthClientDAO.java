package com.luyh.projectv1.dao.classses;

import com.luyh.projectv1.model.OauthClientDetails;

public interface OAuthClientDAO  extends GenericDAO<OauthClientDetails, String>{
	
	public boolean isClientAvailable(String clientId);
	public OauthClientDetails loadClientById(String clientId);
	
}
