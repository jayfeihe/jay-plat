package com.jay.config.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;

/*
 * 服务层使用MyBatis操作数据库，这里从远程git配置中心获取数据库连接信息
 * 配置可参看：http://blog.csdn.net/isea533/article/details/50359390
 * 
 * 特别注意：
 *       由于MapperScannerConfigurer执行的比较早，必须在MyBatisConfig执行完成后才执行，
 *       因此MapperScannerConfigurer的bean创建单独放在MyBatisMapperScanConfig中
 */
@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)  //使用自定义的配置解析类
@EnableTransactionManagement
public class MyBatisConfig {
	@Autowired
	private DataSourceProperties dataSourceProperties;
	//mybatis mapper xml的搜索路径
	private final static String mapperLocations = "classpath:mybatis/*.xml";
	
	private DruidDataSource dataSource;
	
	/*
	 * 使用Druid数据库连接池
	 */
	@Bean
	public DataSource dataSource(){
		dataSource = new DruidDataSource();
		dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
		dataSource.setUrl(dataSourceProperties.getUrl());
		dataSource.setUsername(dataSourceProperties.getUsername());
		dataSource.setPassword(dataSourceProperties.getPassword());
		dataSource.setInitialSize(dataSourceProperties.getInitialSize());
		dataSource.setMaxActive(dataSourceProperties.getMaxActive());
		dataSource.setMinIdle(dataSourceProperties.getMinIdle());
		dataSource.setTestOnBorrow(dataSourceProperties.isTestOnBorrow());
		dataSource.setTestOnReturn(dataSourceProperties.isTestOnReturn());
		return dataSource;
	}
	
	@PreDestroy
	public void close(){
		if(dataSource!=null){
			dataSource.close();
		}
	}
	
	/*
	 * 配置MyBatis的SqlSessionFactory，指定Mapper的xml路径
	 */
	@Bean(value = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {		
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());		
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}
