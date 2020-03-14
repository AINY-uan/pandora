
package org.ainy.pandora.entity.authority;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author 阿拉丁省油的灯
 * @date 2019-11-06 22:29
 * @description 用户实体类
 */
@Data
@NoArgsConstructor
public class UserInfo {

    /**
     * 用用户唯一id，由系统生成
     */
    private Long uid;
    /**
     * 用户登录id，用户自定义，唯一，不可重复
     */
    private String userId;
    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 密码盐值
     */
    private String salt;
    /**
     * 密码哈希值
     */
    private String hashValue;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 用户创建时间
     */
    private LocalDateTime createTime;
    /**
     * 电子邮件
     */
    private String email;
    /**
     * 电话号码
     */
    private String contactNumber;
}