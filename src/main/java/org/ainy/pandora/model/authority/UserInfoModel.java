package org.ainy.pandora.model.authority;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author 阿拉丁省油的灯
 * @date 2019-11-20 22:57
 * @description 用户信息Model
 */
@Data
@NoArgsConstructor
public class UserInfoModel {

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
     * 角色id
     */
    private String roleId;
    /**
     * 用户创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}
