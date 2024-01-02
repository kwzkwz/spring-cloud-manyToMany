package com.cqie.api;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqie.common.Result;
import com.cqie.pojo.User;

import java.util.List;

public interface UserApi extends IService<User> {
    public Result getUserByIds(List<Integer> userIds);
}
