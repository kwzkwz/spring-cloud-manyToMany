package com.cqie.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqie.common.Result;
import com.cqie.pojo.Role;

import java.util.List;

public interface RoleApi extends IService<Role> {
    public Result getRoleByUserId(Integer userId);
}
