package com.sspu.service;

import com.sspu.domain.user.Address;
import com.sspu.domain.user.User;

import java.util.List;

public interface UserService {
    String login(String phone, String password);

    List<Address> getUserAddress(Integer id);
    String updateAvatar(String avatar, User user);
    String register(String phone, String password);
}
