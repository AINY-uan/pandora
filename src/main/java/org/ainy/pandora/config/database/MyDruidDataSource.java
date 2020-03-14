package org.ainy.pandora.config.database;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.ainy.pandora.util.RsaUtil;

/**
 * @author 阿拉丁省油的灯
 * @date 2019-11-08 22:32
 * @description 自定义数据库连接池
 */
@Slf4j
public class MyDruidDataSource extends DruidDataSource {

    /**
     * <p>
     * Title: setUsername
     * </p>
     * <p>
     * Description:数据库用户名解密
     * </p>
     *
     * @param username 数据库用户名
     * @see com.alibaba.druid.pool.DruidAbstractDataSource#setUsername(String)
     */
    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    /**
     * <p>
     * Title: setPassword
     * </p>
     * <p>
     * Description: 数据库密码解密
     * </p>
     *
     * @param password 数据库密码
     * @see com.alibaba.druid.pool.DruidAbstractDataSource#setPassword(String)
     */
    @Override
    public void setPassword(String password) {
        try {
            String dbrasPath = "E:/Workspace/";
            String publicKey = RsaUtil.loadPublicKeyByFile(dbrasPath);
            password = ConfigTools.decrypt(publicKey, password);
        } catch (Exception e) {
            log.error("[数据库密码解密失败]", e);
        }
        super.setPassword(password);
    }
}
