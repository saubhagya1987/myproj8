package com.luyh.application.service.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.luyh.application.utils.Constantes;
import com.luyh.projectv1.dao.classses.ClientServicesDao;
import com.luyh.projectv1.dao.classses.OAuthClientDAO;
import com.luyh.projectv1.model.OauthClientDetails;

@Service("customClientDetailsService")
@Transactional
public class CustomClientDetailService implements ClientDetailsService, ClientServicesDao {
	
	@Autowired
	private OAuthClientDAO oauthClientDAO;
	
	@Override
	//@InfoLogger(origen = "loadClientByClientId")
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		
		if (!oauthClientDAO.isClientAvailable(clientId)){
			throw new ClientRegistrationException(String.format(Constantes.MSG_ERROR_CLIENTE_NO_REGISTRADO, clientId));
		}
		
		OauthClientDetails client = oauthClientDAO.loadClientById(clientId);
		
		BaseClientDetails clientDetails = new BaseClientDetails();
		
		List<String> authorities = Arrays.asList(client.getAuthorizedGrantTypes().split(","));
		
		List<GrantedAuthority> authoritiesList = new ArrayList<GrantedAuthority>();
		
		for (String s : authorities){
			authoritiesList.add(new SimpleGrantedAuthority(s));
		}
		
		Set<String> uris = new HashSet<String>(Arrays.asList(client.getWebServerRedirectUri().split(",")));
		
		clientDetails.setClientId(client.getClientId());
		clientDetails.setScope(Arrays.asList(client.getScope().split(",")));
		clientDetails.setAuthorizedGrantTypes(Arrays.asList(client.getAuthorizedGrantTypes().split(",")));
		clientDetails.setAuthorities(authoritiesList);
		clientDetails.setAccessTokenValiditySeconds(client.getAccessTokenValidity().intValue());
		clientDetails.setClientSecret(client.getClientSecret());
		clientDetails.setRegisteredRedirectUri(uris);
		clientDetails.setResourceIds(Arrays.asList(client.getResourceIds().split(",")));
		//clientDetails.setAdditionalInformation();
												
		String approve = client.getAutoapprove() == null ? "false" : "true";
		
		if(approve.equalsIgnoreCase("true"))
            clientDetails.setAutoApproveScopes(StringUtils.commaDelimitedListToSet(client.getAutoapprove()));
		else
            clientDetails.setAutoApproveScopes(new HashSet<String>());
				
		return clientDetails;
	}

	@Override
	public void createClient(OauthClientDetails client) {
		if (client != null){
			oauthClientDAO.save(client);
		}
	}

	@Override
	public boolean isUserAvailable(String clientId) {
		if (clientId != null){
			oauthClientDAO.getById(clientId);
		}
		
		return false;
	}

	@Override
	public OauthClientDetails getClientById(String clientId) {
		
		OauthClientDetails client = null;
		
		if (!clientId.equalsIgnoreCase("")){
			client = oauthClientDAO.getById(clientId);
		}
		
		return client;
	}

	@Override
	public OauthClientDetails actualizar(OauthClientDetails client) {
		
		if (client != null){
			oauthClientDAO.update(client);
		}
		
		return client;
	}

	@Override
	public void eliminar(OauthClientDetails client) {
		if (client != null){
			oauthClientDAO.delete(client.getClientId());
		}
	}

}