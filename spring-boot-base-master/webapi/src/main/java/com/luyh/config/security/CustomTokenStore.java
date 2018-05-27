package com.luyh.config.security;



import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

public class CustomTokenStore extends JdbcTokenStore{
	Logger LOG=Logger.getLogger(CustomTokenStore.class);
	private static final String DEFAULT_ACCESS_TOKEN_SELECT_STATEMENT = "select token_id, token from oauth_access_token where token_id = ?";
	
	private String selectAccessTokenSql = DEFAULT_ACCESS_TOKEN_SELECT_STATEMENT;
	
	public CustomTokenStore(DataSource dataSource) {
		super(dataSource);
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		// TODO Auto-generated constructor stub
	}





	private final JdbcTemplate jdbcTemplate;
	
	
	public OAuth2AccessToken readAccessToken(String tokenValue) {
				OAuth2AccessToken accessToken = null;	     	
				    System.out.println("tokenValue"+tokenValue);
				    String base64String=tokenValue;
		        	byte[] valueDecoded= Base64.decodeBase64(base64String );
		        	System.out.println("base64String"+base64String);
		        	String sdecode= new String(valueDecoded);
		        	System.out.println("sdecode"+sdecode);
		
				try {
					if(tokenValue!=null){
						
						/*System.out.println("if block");
						accessToken = jdbcTemplate.queryForObject(selectAccessTokenSql, new RowMapper<OAuth2AccessToken>() {
							public OAuth2AccessToken mapRow(ResultSet rs, int rowNum) throws SQLException {
								return deserializeAccessToken(rs.getBytes(2));
							}
						}, extractTokenKey(sdecode));*/	
						
						System.out.println("if block");
						accessToken = jdbcTemplate.queryForObject(selectAccessTokenSql, new RowMapper<OAuth2AccessToken>() {
							public OAuth2AccessToken mapRow(ResultSet rs, int rowNum) throws SQLException {
								return deserializeAccessToken(rs.getBytes(2));
							}
						}, extractTokenKey(tokenValue));
						
					}
					else{
						System.out.println("else block");
						accessToken = jdbcTemplate.queryForObject(selectAccessTokenSql, new RowMapper<OAuth2AccessToken>() {
							public OAuth2AccessToken mapRow(ResultSet rs, int rowNum) throws SQLException {
								return deserializeAccessToken(rs.getBytes(2));
							}
						}, extractTokenKey(tokenValue));
						
					}
					
				}
				catch (EmptyResultDataAccessException e) {
					removeAccessToken(tokenValue);
					if (LOG.isInfoEnabled()) {
						LOG.info("Failed to find access token for token " + sdecode);
					}
				}
				catch (IllegalArgumentException e) {
					LOG.warn("Failed to deserialize access token for " + sdecode);
					removeAccessToken(tokenValue);
				}
		
				return accessToken;
			}

}