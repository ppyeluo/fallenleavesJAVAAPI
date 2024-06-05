package com.sspu.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.sspu.domain.user.User;


import java.util.Date;

public class JWTUtil {
    private static final String SECRET_KEY = "ppyeluo_nostalgia";

    // 生成JWT token
    public static String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer("auth0")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withClaim("phone", user.getPhone())
                    .withClaim("password", "************") // 将密码替换为 "************"
                    .withClaim("email", user.getEmail())
                    .withClaim("avatar", user.getAvatar())
                    .withClaim("tag", user.getTag())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // 1 day expiration
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
            throw exception;
        }
    }

    // 解码JWT token
    public static User decodeToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build()
                    .verify(token);

            com.auth0.jwt.interfaces.DecodedJWT decodedJWT = JWT.decode(token);
            String username = decodedJWT.getSubject();
            Integer id = decodedJWT.getClaim("id").asInt();
            String phone = decodedJWT.getClaim("phone").asString();
            String password = decodedJWT.getClaim("password").asString();
            String email = decodedJWT.getClaim("email").asString();
            String avatar = decodedJWT.getClaim("avatar").asString();
            String tag = decodedJWT.getClaim("tag").asString();

            User user = new User();
            user.setId(id);
            user.setUsername(username);
            user.setPhone(phone);
            user.setPassword(password);
            user.setEmail(email);
            user.setAvatar(avatar);
            user.setTag(tag);
            return user;
        } catch (TokenExpiredException e) {
            // JWT 令牌已过期
            return null;
        } catch (JWTVerificationException e) {
            // 其他验证失败的情况
            throw e;
        }
    }


    public static void main(String[] args) {
        User user = new User();
        user.setAvatar("de");
        user.setUsername("叶子");
        user.setPassword("de");
        user.setPhone("3245678");
        user.setTag("护院");
        String token = generateToken(user);
        System.out.println(token);
        System.out.println(decodeToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLkupHmt7HkuI3nn6XlpIQiLCJwYXNzd29yZCI6IioqKioqKioqKioqKiIsInBob25lIjoiMTIzMTIzNDEyMzQiLCJpc3MiOiJhdXRoMCIsImlkIjoyNiwiYXZhdGFyIjoiaHR0cDovLzQ3LjExNi40OS44Mi9zdGF0aWMvdXNlci91c2VyXzI2LmpwZyIsInRhZyI6IuaZrumAmuS8muWRmCIsImV4cCI6MTcxNzA4MzA4MCwiZW1haWwiOiJwaWFvcGlhb3llbHVvQGdtYWlsLmNvbSJ9.qms-N5u5JzR7EuMi-0n5h3-FRG07gpxF-IJeRVZG-QM"));
    }
}
