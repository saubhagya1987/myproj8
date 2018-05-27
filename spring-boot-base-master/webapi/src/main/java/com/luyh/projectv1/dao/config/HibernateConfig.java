package com.luyh.projectv1.dao.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" })
@ComponentScan("com.luyh")
public class HibernateConfig {

	@Autowired
	private Environment environment;

	@Bean
	@Primary
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());		
		sessionFactory.setPackagesToScan(environment.getProperty("package.name"));
		sessionFactory.setHibernateProperties(additionalProperties());
		
		return sessionFactory;
	}

	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("datasource.driver"));
		dataSource.setUrl(environment.getProperty("datasource.url"));
		dataSource.setUsername(environment.getProperty("datasource.username"));
		dataSource.setPassword(environment.getProperty("datasource.password"));


		Properties properties = new Properties();
		properties.setProperty("useUnicode", "true");
		properties.setProperty("characterEncoding", "UTF-8");
		properties.setProperty("charSet", "UTF-8");
		properties.setProperty("hibernate.default_schema",environment.getProperty("hibernate.default_schema"));
		
		dataSource.setConnectionProperties(properties);
		

		return dataSource;
	}
    
	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
		properties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
		properties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
		properties.setProperty("hibernate.connection.useUnicode", "true");
		properties.setProperty("hibernate.connection.characterEncoding", "UTF-8");
		properties.setProperty("hibernate.connection.charSet", "UTF-8");
		properties.setProperty("hibernate.default_schema",environment.getProperty("hibernate.default_schema"));
		properties.setProperty("hibernate.format_sql",environment.getProperty("hibernate.format_sql"));		
		properties.setProperty("hibernate.event.merge.entity_copy_observer","allow");
		//properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
		

		return properties;
	}
	
	@Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate;
    }

}


/*@Configuration
public class MyBatisConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        return new org.apache.tomcat.jdbc.pool.DataSource();
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }
}*/
