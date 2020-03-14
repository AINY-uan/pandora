package org.ainy.pandora.config.database;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author 阿拉丁省油的灯
 * @date 2019-11-05 22:48
 * @description 权限数据库配置
 */
@Configuration
@MapperScan(basePackages = AuthorityDatasourceConfig.PACKAGE, sqlSessionTemplateRef = "authoritySqlSessionTemplate")
public class AuthorityDatasourceConfig {

    static final String PACKAGE = "org.ainy.pandora.dao.mapper.authority";

    private static final String MAPPER_LOCATION = "classpath:conf/mybatis/mapper/authority/*.xml";

    @Primary
    @Bean(name = "authorityDataSource")
    public DataSource authorityData(AuthorityDatasourceProperties authorityDatasourceProperties) {

        MyDruidDataSource authorityDataSource = new MyDruidDataSource();

        authorityDataSource.setUrl(authorityDatasourceProperties.getUrl());
        authorityDataSource.setUsername(authorityDatasourceProperties.getUsername());
        authorityDataSource.setPassword(authorityDatasourceProperties.getPassword());
        authorityDataSource.setDriverClassName(authorityDatasourceProperties.getDriverClassName());
        authorityDataSource.setInitialSize(authorityDatasourceProperties.getInitialSize());
        authorityDataSource.setMinIdle(authorityDatasourceProperties.getMinIdle());
        authorityDataSource.setMaxActive(authorityDatasourceProperties.getMaxActive());

        return authorityDataSource;
    }

    @Primary
    @Bean(name = "authoritySqlSessionFactory")
    public SqlSessionFactory authoritySqlSessionFactory(@Qualifier("authorityDataSource") DataSource dataSource) throws Exception {

        final SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
//        Objects.requireNonNull(bean.getObject()).getConfiguration().setMapUnderscoreToCamelCase(true); // 开启驼峰命名规则
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(AuthorityDatasourceConfig.MAPPER_LOCATION));
        return bean.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Primary
    @Bean(name = "authorityTransactionManager")

    public DataSourceTransactionManager authorityTransactionManager(@Qualifier("authorityDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "authoritySqlSessionTemplate")
    public SqlSessionTemplate authoritySqlSessionTemplate(@Qualifier("authoritySqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
