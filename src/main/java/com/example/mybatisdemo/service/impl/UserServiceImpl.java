package com.example.mybatisdemo.service.impl;

import com.example.mybatisdemo.entity.User;
import com.example.mybatisdemo.mapper.UserMapper;
import com.example.mybatisdemo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weihua
 * @since 2021-07-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
