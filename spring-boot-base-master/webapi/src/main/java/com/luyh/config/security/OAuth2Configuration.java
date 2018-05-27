package com.luyh.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.luyh.application.utils.Constantes;

@Configuration
public class OAuth2Configuration {
	
	@Configuration
	@EnableAuthorizationServer
	protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
		
		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;
		
		@Autowired
		private DataSource dataSource;
		
		@Autowired
		@Qualifier("customClientDetailsService")
		private ClientDetailsService clientDetailService;
		
		@Autowired
		@Qualifier("customUserDetailsService")
		private UserDetailsService userDetailsService;
		
		/*@Bean
		public TokenStore tokenStore() {
				return new JdbcTokenStore(dataSource);
		}*/
		@Bean
		public CustomTokenStore tokenStore() {
				return new CustomTokenStore(dataSource);
		}
		
		@Bean
		protected AuthorizationCodeServices authorizationCodeServices() {
			return new JdbcAuthorizationCodeServices(dataSource);
		}
		
		@Override
		public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		   oauthServer.allowFormAuthenticationForClients();
		}
		
		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints
				.tokenStore(tokenStore())
				.authenticationManager(this.authenticationManager)
				.userDetailsService(this.userDetailsService);
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			// Con los datos del cliente en base de datos
			clients.withClientDetails(clientDetailService);
			
			// Con los datos del cliente en memoria
			/*clients
				.inMemory()
					.withClient("clientapp")
						.authorizedGrantTypes("password", "refresh_token", "client_credentials")
						.authorities("USER")
						.scopes("read", "write")
						.resourceIds(RESOURCE_ID)
						.secret("8649168")
						.accessTokenValiditySeconds(300)
						.refreshTokenValiditySeconds(600)
						.autoApprove(false);*/
			
		}

		@Bean
		@Primary
		public DefaultTokenServices tokenServices() {
			DefaultTokenServices tokenServices = new DefaultTokenServices();
			tokenServices.setSupportRefreshToken(true);
			tokenServices.setTokenStore(tokenStore());
			return tokenServices;
		}

	}
	
	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
		
		@Autowired
        private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
		
		@Override
		public void configure(ResourceServerSecurityConfigurer resources) {
			resources
				.resourceId(Constantes.RESOURCE_ID);
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {
			
				http
			   .exceptionHandling()
			   .authenticationEntryPoint(customAuthenticationEntryPoint)
			  .and() 
               .csrf()
               .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
               .disable()
               .headers()
               .frameOptions().disable()
             /* .and()
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)*/
              .and() 
               .authorizeRequests()
               .antMatchers("api/v1.0/secure").hasRole("ADMIN")
               .antMatchers("/api/v1.0/**").authenticated().and().csrf().disable().logout()
              .and()
               .logout()
               .logoutUrl("/oauth/logout");
			
			/*http
            .exceptionHandling()
            .authenticationEntryPoint(customAuthenticationEntryPoint)
            .and()
            .logout()
            .logoutUrl("/oauth/logout")
            
            .and()
            .csrf()
            .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
            .disable()
            .headers()
            .frameOptions().disable();
			
			
			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("api/v1.0/secure").permitAll()
            .antMatchers("/api/v1.0/**").authenticated();*/
			
			
		}

	}
	
}
