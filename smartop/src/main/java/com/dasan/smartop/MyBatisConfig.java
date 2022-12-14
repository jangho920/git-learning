package com.dasan.smartop;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.dasan.smartop.dataAccess.util.SmartopMap;
//import com.dasan.smartop.mapper.BrndMmMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan("com.dasan.smartop.mapper")
@ComponentScan(basePackages = {"com.dasan.smartop.mapper"})
public class MyBatisConfig {
	protected Logger logger = LoggerFactory.getLogger(MyBatisConfig.class);
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	@Bean(name = "dataSource")
    public DataSource dataSource() throws SQLException {
		DataSource hikariDataSource = new HikariDataSource(hikariConfig());
        return hikariDataSource;

    }
	
	/**
	 * SqlSessionFactory 객체 생성
	 * 
	 * @methodName	sqlSessionFactory
	 * @param		dataSource
	 * @param		applicationContext
	 * @return
	 * @throws		Exception
	 */
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:config/mapper-config.xml"));
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/dasan/smartop/mapper/*.xml"));
		sqlSessionFactoryBean.setTransactionFactory(new ManagedTransactionFactory());
		sqlSessionFactoryBean.setTypeAliases(new Class<?>[] { SmartopMap.class });
		
		//sqlSessionFactoryBean.setTypeAliasesPackage("**.vo");
		return sqlSessionFactoryBean.getObject();
	}
	
	/**
	 * SqlSessionTemplate 객체 생성
	 * 
	 * @methodName	sqlSessionTemplate
	 * @param		dataSource
	 * @param		applicationContext
	 * @return
	 * @throws		Exception
	 */
	@Bean(name = "sqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
        return sqlSessionTemplate;
    }
	
	/*@Bean
	public MapperFactoryBean<BrndMmMapper> BrndMmMapper() throws Exception {
		
		MapperFactoryBean<BrndMmMapper> factoryBean = new MapperFactoryBean<>(BrndMmMapper.class);
		factoryBean.setSqlSessionFactory(sqlSessionFactory());
		return factoryBean;
	}*/
}