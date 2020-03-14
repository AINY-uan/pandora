package org.ainy.pandora.util;

import com.google.common.hash.Hashing;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author 阿拉丁省油的灯
 * @date 2019-11-06 22:36
 * @description 加解密工具
 */
public class EncryptionUtils {

    /*
     *盐值与密码拼接后进行哈希
     */
    public static String Encryption(String password, String salt) {

        String mergeString = salt.substring(0, 4) + password + salt.substring(4);
        return Hashing.sha256().hashBytes(mergeString.getBytes()).toString();
    }

    /*
     * 加盐加密，生成盐值
     */
    public static String GenerateSalt() {
        return RandomStringUtils.random(8, true, true);
    }
}
