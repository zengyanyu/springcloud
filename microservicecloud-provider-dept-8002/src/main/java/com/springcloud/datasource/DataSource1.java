package com.springcloud.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

/**
 * DataSource1:ORACLE数据源
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 下午2:51:06
 */
//注册到Spring容器中
@Component
@MapperScan(basePackages = { "com.springcloud.mapper" }, sqlSessionFactoryRef = "db1SqlSessionFactory")
public class DataSource1 {

	/**
	 * 配置Xxx类型的数据库
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月6日 下午12:08:41
	 * @return
	 */
	@Bean(name = "primaryDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.primary")
	@Primary
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean("db1SqlSessionFactory")
	@Primary
	public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		return bean.getObject();
	}

	/**
	 * 配置事务管理器
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月6日 下午12:07:42
	 * @param dataSource
	 * @return
	 */
	@Bean(name = "db1TransactionManager")
	@Primary
	public DataSourceTransactionManager primaryDataSourceTransactionManager(
			@Qualifier("primaryDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	/**
	 * 封装的数据库操作
	 * SqlSessionTemplate是一个线程安全的对象
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月6日 下午12:07:58
	 * @param sqlSessionFactory
	 * @return
	 */
	@Bean(name = "db1SqlSessionTemplate")
	@Primary
	public SqlSessionTemplate primarySqlSessionTemplate(
			@Qualifier("db1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
