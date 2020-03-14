package org.ainy.pandora.util;

import lombok.SneakyThrows;
import org.ainy.pandora.constant.RedisConstant;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author 阿拉丁省油的灯
 * @date 2019-11-06 22:36
 * @description TOKEN验证
 */
@Configuration
public class RedisUtil {

    private static String host = RedisConstant.HOST_NAME;

    private static int port = RedisConstant.PORT;

    private static int timeout = RedisConstant.TIME_OUT;

    private static int maxIdle = RedisConstant.MAX_IDLE;

    private static long maxWaitMillis = RedisConstant.MAX_WAIT;

    private static String password = RedisConstant.PASSWORD;

    public static JedisPool redisPoolFactory() throws Exception {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        return new JedisPool(jedisPoolConfig, host, port, timeout);
    }

    @SneakyThrows
    public static void addToken(String key, String value) {
        redisPoolFactory().getResource().setex(key, 7200, value);
    }

    @SneakyThrows
    public static String getToken(String key) {

        return redisPoolFactory().getResource().get(key);
    }

    @SneakyThrows
    public static void addTokenBlackList(String key, Long expire, String value) {
        int time = (int) (expire - System.currentTimeMillis());
        redisPoolFactory().getResource().setex(key, time / 1000, value);
    }

    @SneakyThrows
    public static Boolean isBlackListed(String key) {

        return redisPoolFactory().getResource().get(key) != null;
    }

    @SneakyThrows
    public static void passwordChanged(String userId) {
        redisPoolFactory().getResource().set(userId, "");
    }

    @SneakyThrows
    public static Boolean isPasswordChanged(String userId) {

        return redisPoolFactory().getResource().get((userId)) != null;
    }
}
