package org.ainy.pandora.model.authority;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author 阿拉丁省油的灯
 * @date 2019-11-18 22:55
 * @description 用户数据传输对象
 */
@Data
@NoArgsConstructor
public class UserCreateModel {

    /**
     * 用户登录id，用户自定义，唯一，不可重复
     */
    @NotEmpty(message = "用户登录id不能为空")
    private String userId;
    /**
     * 用户昵称
     */
    @NotEmpty(message = "用户昵称不能为空")
    private String userName;
    /**
     * 用户密码
     */
    @NotEmpty(message = "登录密码不能为空")
    private String password;
    /**
     * 确认密码不能为空
     */
    @NotEmpty(message = "确认密码不能为空")
    private String confirmPassword;
    /**
     * 角色id
     */
    @NotEmpty(message = "用户所属角色不能为空")
    private String roleId;
    /**
     * 电子邮件
     */
    @Pattern(regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")
    private String email;
    /**
     * 电话号码
     */
    @Pattern(regexp = "\\d{3}\\d{8}|\\d{4}-\\{7,8}")
    private String contactNumber;
}
