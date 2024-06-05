package com.sspu.dao;

import com.sspu.domain.user.User;
import com.sspu.domain.user.Address;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Select("SELECT * FROM user WHERE phone = #{phone} AND password = #{password}")
    User login(@Param("phone") String phone, @Param("password") String password);
    @Select("SELECT * FROM address WHERE user_id = #{id}")
    @Results({
            @Result(property = "isDefault", column = "default")
    })
    List<Address> getUserAddress(@Param("id") Integer id);
    @Update("UPDATE user SET avatar = #{avatar} WHERE id = #{userId}")
    int updateAvatar(@Param("avatar") String avatar, @Param("userId") Integer userId);

    // 注册

    @Select("SELECT COUNT(*) FROM user WHERE phone = #{phone}")
    int countUserByPhone(@Param("phone") String phone);

    @Insert("INSERT INTO user (username, phone, password, avatar, tag) VALUES (#{username}, #{phone}, #{password}, #{avatar}, #{tag})")
    void insertUser(@Param("username") String username,
                    @Param("phone") String phone,
                    @Param("password") String password,
                    @Param("avatar") String avatar,
                    @Param("tag") String tag);

    @Select("SELECT * FROM user WHERE phone = #{phone}")
    User getUserByPhone(@Param("phone") String phone);
}
