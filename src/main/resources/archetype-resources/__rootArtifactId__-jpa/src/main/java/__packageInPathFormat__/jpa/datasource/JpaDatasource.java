package ${package}.jpa.datasource;
#set($pound='#')
#set($dollar='$')
#set($escape='\' )

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = "${package}.jpa.repository",
		entityManagerFactoryRef = "jpaEntityManagerFactory",
		transactionManagerRef = "jpaTransactionManager"
)
class JpaDatasource {

	@Value("${dollar}{spring.datasource.driver-class-name:com.mysql.cj.jdbc.Driver}")
	private String driver;

	@Value("${dollar}{spring.datasource.url}")
	private String url;

	@Value("${dollar}{spring.datasource.username}")
	private String username;

	@Value("${dollar}{spring.datasource.password}")
	private String password;

	@Value("${dollar}{hibernate.dialect:org.hibernate.dialect.MySQL5InnoDBDialect}")
	private String dialect;

	@Value("${dollar}{hibernate.show_sql:true}")
	private boolean showSQL;

	@Value("${dollar}{hibernate.format_sql:true}")
	private boolean formatSQL;

	@Value("${dollar}{entities:${package}.jpa.entity}")
	private String packageScan;

	@Value("${dollar}{connection.release_mode:auto}")
	private String releaseMode;

	@Bean(name = "jpaDataSource")
	public DataSource jpaDataSource() {
		return DataSourceBuilder.create()
				.driverClassName(driver)
				.url(url)
				.username(username)
				.password(password)
				.build();
	}

	@Bean(name = "jpaEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean jpaEntityManager(@Qualifier("jpaDataSource") DataSource jpaDataSource) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(jpaDataSource);
		em.setPackagesToScan(packageScan);
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(hibernateProperties());
		return em;
	}

	@Bean(name = "jpaTransactionManager")
	public PlatformTransactionManager jpaTransactionManager(@Qualifier("jpaEntityManagerFactory") LocalContainerEntityManagerFactoryBean jpaEntityManager) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(jpaEntityManager.getObject());
		return transactionManager;
	}

	@Primary
	@Bean(name = "jpaSessionFactory")
	public LocalSessionFactoryBean jpaSessionFactory(@Qualifier("jpaDataSource") DataSource jpaDataSource) {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(jpaDataSource);
		sessionFactoryBean.setPackagesToScan(packageScan);
		sessionFactoryBean.setHibernateProperties(hibernateProperties());
		return sessionFactoryBean;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", dialect);
		properties.put("hibernate.temp.use_jdbc_metadata_defaults", "false");
		properties.put("hibernate.show_sql", showSQL);
		properties.put("hibernate.format_sql", formatSQL);
		properties.put("entitymanager.packages.to.scan", packageScan);
		properties.put("connection.release_mode", releaseMode);
		return properties;
	}

}