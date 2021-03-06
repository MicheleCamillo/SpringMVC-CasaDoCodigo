package br.com.casadocodigo.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
public class JPAConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter(); //Adaptador do JPA que iremos utilizar
		
		factoryBean.setJpaVendorAdapter(vendorAdapter);
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource(); //Propriedades da conexao
	     dataSource.setDriverClassName("org.postgresql.Driver");
	 	dataSource.setUrl("jdbc:postgresql://localhost:5432/casadocodigo");
		dataSource.setUsername("postgres");
		dataSource.setPassword("michele0701");
	
		factoryBean.setDataSource(dataSource);
		
		Properties prop = new Properties(); //Propriedades do hibernate que iremos utilizar
		prop.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		prop.setProperty("hibernate.show_sql", "true");
		prop.setProperty("hibernate.hbm2ddl.auto", "update");
		factoryBean.setJpaProperties(prop);
		
		factoryBean.setPackagesToScan("br.com.casadocodigo.loja.model");
		
		return factoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) { //Metodo que associa o transaction manager ao entity manager 
		return new JpaTransactionManager(emf);
	}
	
}
