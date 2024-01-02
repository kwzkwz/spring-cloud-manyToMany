package com.cqie.controller;

import com.cqie.api.impl.UserServiceApiImpl;
import com.cqie.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserServiceApiImpl userServiceApi;

    @RequestMapping("getUserByIds/{userIds}")
    public Result getUserByIds(@PathVariable("userIds") Integer[] userIds) {
        return userServiceApi.getUserByIds(java.util.Arrays.asList(userIds));
    }
}
