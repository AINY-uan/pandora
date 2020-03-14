package org.ainy.pandora;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 阿拉丁省油的灯
 * @date 2019-11-17 22:45
 * @description 启动类
 */
@Slf4j
@SpringBootApplication
public class PandoraApplication {

    public static void main(String[] args) {

        SpringApplication.run(PandoraApplication.class, args);
    }
}