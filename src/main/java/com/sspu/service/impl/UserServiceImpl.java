package com.sspu.service.impl;

import com.sspu.dao.UserMapper;
import com.sspu.domain.user.User;
import com.sspu.domain.user.Address;
import com.sspu.service.UserService;
import com.sspu.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public String login(String phone, String password){
        User user = userMapper.login(phone, password);
        if(user != null){
            String token = JWTUtil.generateToken(user);
            return token;
        }
        return null;
    }
    public List<Address> getUserAddress(Integer id){
        List<Address> list = userMapper.getUserAddress(id);
        return list;
    }
    public String updateAvatar(String avatar, User user){
        Integer i = userMapper.updateAvatar(avatar, user.getId());
        if(i > 0){
            user.setAvatar(avatar);
            return JWTUtil.generateToken(user);
        }else{
            return null;
        }
    }
    public String register(String phone, String password){
        // 检查手机号是否已经被注册
        if (userMapper.countUserByPhone(phone) > 0) {
            return "手机号已被注册";
        }
        // 生成随机用户名
        String username = "leaves-" + (int) (Math.random() * 10000);
        String avatar = "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png";
        String tag = "普通会员";

        // 将注册信息保存到数据库中
        userMapper.insertUser(username, phone, password, avatar, tag);

        // 查询注册成功的用户信息
        User user = userMapper.getUserByPhone(phone);

        // 生成 token
        String token = JWTUtil.generateToken(user);

        return token;
    }
}
