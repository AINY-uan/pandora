package org.ainy.pandora.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.ainy.pandora.constant.TokenConstant;

import java.util.Date;

/**
 * @author 阿拉丁省油的灯
 * @date 2019-11-06 22:36
 * @description TOKEN验证
 */
@Slf4j
public class AuthUtils {

    /*JWT认证，生成token，加入token中的信息可以根据实际情况修改*/
    public static String getToken(final String userId, final String roleId) {

        String token = null;
        try {
            long currentTime = System.currentTimeMillis();
            String tokenId = System.currentTimeMillis() + userId;
            Date expiresAt = new Date(currentTime + TokenConstant.TOKEN_EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("pandora")
                    // 加入userId字段
                    .withClaim("userId", userId)
                    // 加入role字段
                    .withClaim("roleId", roleId)
                    .withIssuedAt(new Date(currentTime))
                    .withJWTId(tokenId)
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(TokenConstant.SECRET_KEY));
        } catch (JWTCreationException e) {
            log.error("JWTCreationException", e);
        } catch (IllegalArgumentException e) {
            log.error("IllegalArgumentException", e);
        }
        return token;
    }

    // 校验前端发送的token
    public static Boolean decryptToken(final String token) {

        if (token == null) {
            return false;
        }
        try {
            // 使用了HMAC256加密算法。
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TokenConstant.SECRET_KEY))
                    .withIssuer("pandora")
                    .build(); // Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            if (jwt.getExpiresAt().compareTo(new Date()) < 0) {
                return false;
            }
            // 是否在redis黑名单中
            if (RedisUtil.isBlackListed(jwt.getId())) {
                return false;
            }
        } catch (JWTVerificationException e) {
            log.error("JWTCreationException", e);
            return false;
        } catch (IllegalArgumentException e) {
            log.error("IllegalArgumentException", e);
            return false;
        }
        return true;
    }

    // 设置token黑名单
    public static Boolean setBlackList(String token) {

        if (decryptToken(token)) {
            RedisUtil.addTokenBlackList(JWT.decode(token).getId(), JWT.decode(token).getExpiresAt().getTime(), token);
            return true;
        } else {
            return false;
        }
    }

    // 获取token中的userId字段
    public static String getUserId(String token) {

        return JWT.decode(token).getClaim("userId").asString();
    }
}
