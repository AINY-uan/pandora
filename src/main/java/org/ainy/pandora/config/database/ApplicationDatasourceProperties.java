package org.ainy.pandora.config.database;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 阿拉丁省油的灯
 * @date 2019-11-09 21:09
 * @description 权限数据库属性配置文件
 */
@Data
@Component
@ConfigurationProperties(value = "application.datasource.druid")
public class ApplicationDatasourceProperties {

    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private Integer initialSize;
    private Integer minIdle;
    private Integer maxActive;
}
