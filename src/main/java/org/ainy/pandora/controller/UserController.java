package org.ainy.pandora.controller;

import lombok.extern.slf4j.Slf4j;
import org.ainy.pandora.model.ResponseData;
import org.ainy.pandora.model.authority.UserCreateModel;
import org.ainy.pandora.model.authority.UserLoginModel;
import org.ainy.pandora.service.authority.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Author 阿拉丁省油的灯
 * @Date 2019-11-06 22:38
 * @Description 用户控制类
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 创建用户
     *
     * @param userCreateModel 用户信息
     * @return ResponseData
     */
    @ResponseBody
    @PostMapping(value = "/create")
    public ResponseData<?> createUser(@Valid @RequestBody UserCreateModel userCreateModel) {

        return userService.insert(userCreateModel);
    }

    /**
     * 用户登录
     * @param userLoginModel 用户信息
     * @return token
     */
    @ResponseBody
    @PostMapping(value = "/login")
    public ResponseData<?> login(@Valid @RequestBody UserLoginModel userLoginModel) {

        return userService.login(userLoginModel);
    }

    /**
     * 获取用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    @ResponseBody
    @GetMapping(value = "/getInfo")
    public ResponseData<?> getUserInfo(HttpServletRequest request,
                                    @NotNull(message = "用户唯一id不能为空") @RequestParam Long id) {

        log.info("userId: {}", request.getAttribute("userId"));

        return userService.getUserInfo(id);
    }
}
