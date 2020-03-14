package org.ainy.pandora.config.database;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author 阿拉丁省油的灯
 * @date 2019-11-06 21:51
 * @description 应用数据库配置
 */
@Configuration
@MapperScan(basePackages = ApplicationDatasourceConfig.PACKAGE, sqlSessionTemplateRef = "applicationSqlSessionTemplate")
public class ApplicationDatasourceConfig {

    static final String PACKAGE = "org.ainy.pandora.dao.mapper.application";

    private static final String MAPPER_LOCATION = "classpath:conf/mybatis/mapper/application/*.xml";

    @Bean(name = "applicationDataSource")
    public DataSource applicationData(ApplicationDatasourceProperties applicationDatasourceProperties) {

        MyDruidDataSource applicationDataSource = new MyDruidDataSource();

        applicationDataSource.setUrl(applicationDatasourceProperties.getUrl());
        applicationDataSource.setUsername(applicationDatasourceProperties.getUsername());
        applicationDataSource.setPassword(applicationDatasourceProperties.getPassword());
        applicationDataSource.setDriverClassName(applicationDatasourceProperties.getDriverClassName());
        applicationDataSource.setInitialSize(applicationDatasourceProperties.getInitialSize());
        applicationDataSource.setMinIdle(applicationDatasourceProperties.getMinIdle());
        applicationDataSource.setMaxActive(applicationDatasourceProperties.getMaxActive());

        return applicationDataSource;
    }

    @Bean(name = "applicationSqlSessionFactory")
    public SqlSessionFactory applicationSqlSessionFactory(@Qualifier("applicationDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
//        Objects.requireNonNull(bean.getObject()).getConfiguration().setMapUnderscoreToCamelCase(true); // 开启驼峰命名规则
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(ApplicationDatasourceConfig.MAPPER_LOCATION));
        return bean.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Bean(name = "applicationTransactionManager")
    public DataSourceTransactionManager applicationTransactionManager(@Qualifier("applicationDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "applicationSqlSessionTemplate")
    public SqlSessionTemplate applicationSqlSessionTemplate(@Qualifier("applicationSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
