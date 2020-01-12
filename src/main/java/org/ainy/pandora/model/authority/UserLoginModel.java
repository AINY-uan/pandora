package org.ainy.pandora.model.authority;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @Author 阿拉丁省油的灯
 * @Date 2019-11-23 21:09
 * @Description 用户登录Model
 */
@Data
@NoArgsConstructor
public class UserLoginModel {

    /**
     * 用户登录id
     */
    @NotEmpty(message = "用户登录id不能为空")
    private String userId;
    /**
     * 用户登录密码
     */
    @NotEmpty(message = "用户登录密码不能为空")
    private String password;
}
