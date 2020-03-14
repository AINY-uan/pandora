package org.ainy.pandora.service.authority;

import lombok.extern.slf4j.Slf4j;
import org.ainy.pandora.constant.ErrorConstant;
import org.ainy.pandora.dao.mapper.authority.UserInfoMapper;
import org.ainy.pandora.entity.authority.UserInfo;
import org.ainy.pandora.model.ResponseData;
import org.ainy.pandora.model.authority.UserCreateModel;
import org.ainy.pandora.model.authority.UserInfoModel;
import org.ainy.pandora.model.authority.UserLoginModel;
import org.ainy.pandora.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author 阿拉丁省油的灯
 * @date 2019-11-06 22:36
 * @description 用户服务
 */
@Slf4j
@Service
public class UserService {

    private final UserInfoMapper userInfoMapper;

    @Autowired
    public UserService(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    /**
     * 获取用户信息
     *
     * @param o 用户uid
     * @return 用户信息
     */
    public ResponseData<?> getUserInfo(Object o) {

        try {

            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(o);

            if (userInfo == null) {
                return new ResponseData<>(ErrorConstant.USER_IS_NOT_EXIST);
            }

            return new ResponseData<>(OrikaMapper.copy(userInfo, UserInfoModel.class));

        } catch (Exception e) {

            log.error("获取用户信息出现错误", e);
            return new ResponseData<>(ErrorConstant.SYSTEM_ERROR);
        }
    }

    /**
     * 创建用户
     *
     * @param o 用户数据
     * @return ResponseData
     */
    public ResponseData<?> insert(UserCreateModel o) {

        try {

            UserInfo userInfoTmp = userInfoMapper.selectByUniqueKey(o.getUserId());

            if (userInfoTmp != null) {
                return new ResponseData<>(ErrorConstant.USER_IS_EXIST);
            }

            UserInfo userInfo = new UserInfo();

            OrikaMapper.copy(o, userInfo);
            userInfo.setUid(new SnowFlake(2, 3).nextId());
            String salt = EncryptionUtils.GenerateSalt();
            userInfo.setSalt(salt);
            userInfo.setHashValue(EncryptionUtils.Encryption(o.getPassword().trim(), salt.trim()));
            userInfo.setCreateTime(LocalDateTime.now());

            int i = userInfoMapper.insert(userInfo);

            if (i == 0) {

                return new ResponseData<>(ErrorConstant.OPERATION_IS_FAILED);
            }

            return new ResponseData<>(ErrorConstant.SYSTEM_SUCCESS.getMsg());

        } catch (Exception e) {

            log.error("创建用户出现错误", e);

            return new ResponseData<>(ErrorConstant.SYSTEM_ERROR);
        }
    }

    /**
     * 用户登录
     *
     * @param userLoginModel 用户登录
     * @return ResponseData
     */
    public ResponseData<?> login(UserLoginModel userLoginModel) {

        try {

            UserInfo userInfoTmp = userInfoMapper.selectByUniqueKey(userLoginModel.getUserId());

            if (userInfoTmp == null) {
                return new ResponseData<>(ErrorConstant.USER_IS_NOT_EXIST);
            }

            /*
             * 判断密码是否正确
             */
            String tmpPwd = EncryptionUtils.Encryption(userLoginModel.getPassword().trim(), userInfoTmp.getSalt());
            // 密码错误
            if (!tmpPwd.equals(userInfoTmp.getHashValue())) {
                return new ResponseData<>(ErrorConstant.PASSWORD_IS_ERROR);
            }

            String token = RedisUtil.getToken(userLoginModel.getUserId());

            if (StringUtils.isEmpty(token)) {
                token = AuthUtils.getToken(userInfoTmp.getUserId(), userInfoTmp.getRoleId());
                RedisUtil.addToken(userInfoTmp.getUserId(), token);
            }

            return new ResponseData<>(token);

        } catch (Exception e) {

            log.error("用户登录出现错误", e);

            return new ResponseData<>(ErrorConstant.SYSTEM_ERROR);
        }
    }
}
