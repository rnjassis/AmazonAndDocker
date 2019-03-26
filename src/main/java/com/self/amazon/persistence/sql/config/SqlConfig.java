package com.self.amazon.persistence.sql.config;

import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.self.amazon.persistence.sql.repository", entityManagerFactoryRef = "sqlEntityManager", transactionManagerRef = "sqlTransactionManager")
class CRIDBConfiguration {

	@Bean(name="datasource")
	@Primary
	@ConfigurationProperties(prefix = "spring.sql-datasource")
	public DataSource datasource() {
		return DataSourceBuilder.create().build();
	}

	@PersistenceContext(unitName = "sql-db")
	@Primary
	@Bean(name = "sqlEntityManager")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(datasource()).persistenceUnit("sql-db")
				.packages("com.self.amazon.persistence.sql.entity").build();
	}

	@Bean(name = "sqlTransactionManager")
	public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(entityManagerFactory(builder).getObject());
		tm.setDataSource(datasource());
		return tm;
	}
}