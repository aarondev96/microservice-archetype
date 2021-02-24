package ${package}.jpa.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = "${package}.jpa.repository",
		entityManagerFactoryRef = "jpaEntityManagerFactory",
		transactionManagerRef = "jpaTransactionManager"
)
class JpaDatasource {

	@Value("${spring.datasource.driver-class-name:com.mysql.jdbc.Driver}")
	private String driver;

	@Value("${datasource.url:jdbc:mysql://localhost:3306/{}}")
	private String url;

	@Value("${datasource.username:root}")
	private String username;

	@Value("${datasource.password:root}")
	private String password;

	@Value("${hibernate.dialect:org.hibernate.dialect.MySQL5InnoDBDialect}")
	private String dialect;

	@Value("${hibernate.show_sql:true}")
	private boolean showSQL;

	@Value("${hibernate.format_sql:true}")
	private boolean formatSQL;

	@Value("${entities:${package}.jpa.entity}")
	private String packageScan;

	@Value("${connection.release_mode:auto}")
	private String releaseMode;

	@Value("${hibernate.c3p0.min_size:1}")
	private String connPoolMinSize;

	@Value("${hibernate.c3p0.max_size:10}")
	private String connPoollMaxSize;

	@Value("${hibernate.c3p0.idle_test_period:10}")
	private String connPoolIddlePeriod;

	@Bean(name = "jpaDataSource")
	public DataSource jpaDataSource() throws PropertyVetoException {
		DataSourceBuilder dataSourceBuilder = DatasourceBuilder.create();
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(driver);
		dataSource.setJdbcUrl(url + "?useSSL=false");
		dataSource.setUser(username);
		dataSource.setPassword(password);
		dataSource.setMinPoolSize(Integer.parseInt(connPoolMinSize));
		dataSource.setMaxPoolSize(Integer.parseInt(connPoollMaxSize));
		dataSource.setMaxIdleTime(Integer.parseInt(connPoolIddlePeriod));
		dataSource.setTestConnectionOnCheckin(true);
		return dataSource;
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