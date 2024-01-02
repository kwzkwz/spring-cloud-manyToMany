package com.cqie.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cqie.VO.RoleVO;
import com.cqie.VO.UserVO;
import com.cqie.api.UserServiceApi;
import com.cqie.common.Result;
import com.cqie.mapper.RoleMapper;
import com.cqie.mapper.UserMapper;
import com.cqie.mapper.UserRoleMapper;
import com.cqie.pojo.Role;
import com.cqie.pojo.User;
import com.cqie.pojo.UserRole;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceApiImpl extends ServiceImpl<UserMapper, User> implements UserServiceApi {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public Result getUserByIds(List<Integer> userIds) {
        //查询用户
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(User::getUsId,userIds);
        List<User> userList = list(lambdaQueryWrapper);
        List<UserVO> userVOS = EntityUtils.toList(userList, UserVO::new);

        for (UserVO userVO : userVOS) {
            //查询角色
            LambdaQueryWrapper<UserRole> userRoleLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userRoleLambdaQueryWrapper.eq(UserRole::getUsId,userVO.getUsId());
            List<UserRole> userRoles = userRoleMapper.selectList(userRoleLambdaQueryWrapper);
            //获取所有的角色id
            List<Integer> roleIds = userRoles.stream().map(UserRole::getRoId).collect(Collectors.toList());
            //获取所有的角色
            List<Role> roles = roleMapper.selectList(new LambdaQueryWrapper<Role>().in(Role::getRoId,roleIds));
            //获取所有角色对应的用户并分组
            List<UserRole> userRoles1 = userRoleMapper.selectList(new LambdaQueryWrapper<>(UserRole.class).in(UserRole::getRoId, roleIds));
            Map<Integer, List<UserRole>> userRoleCollect = userRoles1.stream().collect(Collectors.groupingBy(UserRole::getRoId));

            //将用户插入角色内
            List<RoleVO> roleVOS = EntityUtils.toList(roles, RoleVO::new);
            for (RoleVO roleVO : roleVOS) {
                List<Integer> userIds2 = userRoleCollect.get(roleVO.getRoId())
                        .stream()
                        .map(UserRole::getUsId)
                        .collect(Collectors.toList());
                List<User> userList2 = list(new LambdaQueryWrapper<User>().in(User::getUsId,userIds2));
//            List<UserVO> userVOList = EntityUtils.toList(userList2, UserVO::new);
                roleVO.setUsers(userList2);
            }
            userVO.setRoles(roleVOS);
        }
        return Result.success(userVOS);
    }



}
