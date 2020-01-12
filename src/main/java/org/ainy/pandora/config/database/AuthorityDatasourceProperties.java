package org.ainy.pandora.config.database;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author 阿拉丁省油的灯
 * @Date 2019-11-09 21:09
 * @Description 权限数据库属性配置文件
 */
@Data
@Component
@ConfigurationProperties(value = "authority.datasource.druid")
public class AuthorityDatasourceProperties {

    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private Integer initialSize;
    private Integer minIdle;
    private Integer maxActive;
}
