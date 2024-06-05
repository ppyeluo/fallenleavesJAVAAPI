package com.sspu.controller;

import com.sspu.domain.user.*;
import com.sspu.service.UserService;
import com.sspu.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JedisPool jedisPool;

    // 获取用户信息
    @GetMapping("/userInfo")
    public ResponseEntity userInfo(@RequestHeader("Authorization") String authHeader){
        try{
            // 从 authHeader 中提取 token
            String token = extractTokenFromHeader(authHeader);
            User user = JWTUtil.decodeToken(token);
            if(user == null) {
                return ResponseHelper.sendResponse(401, null);
            }
            return ResponseHelper.sendResponse(200, user);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseHelper.sendResponse(500, null);
        }
    }
    // 用户登录
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest){
        try {
            String token = userService.login(loginRequest.getPhone(), loginRequest.getPassword());
            return ResponseHelper.sendResponse(200, token);
        }catch (Exception e){
            return ResponseHelper.sendResponse(500, null,"登录失败！");
        }
    }
    // 退出登录
    @GetMapping("/logout")
    public ResponseEntity logout(@RequestHeader("Authorization") String authHeader){
        // 从 authHeader 中提取 token
        String token = extractTokenFromHeader(authHeader);
        User user = JWTUtil.decodeToken(token);
        if(token == "") {
            return ResponseHelper.sendResponse(401, null);
        }
        return ResponseHelper.sendResponse(200, user, "ok");
    }
    @GetMapping("/getUserAddress")
    public ResponseEntity getUserAddress(@RequestHeader("Authorization") String authHeader){
        // 从 authHeader 中提取 token
        String token = extractTokenFromHeader(authHeader);
        User user = JWTUtil.decodeToken(token);
        if(token == "") {
            return ResponseHelper.sendResponse(401, null);
        }
        List<Address> list = userService.getUserAddress(user.getId());
        return ResponseHelper.sendResponse(200, list);
    }
    // 上传头像
    @PostMapping("/uploadAvatar")
    public ResponseEntity uploadAvatar(@RequestHeader("Authorization") String authHeader
            ,@RequestParam("avatar") MultipartFile avatar){
        // 从 authHeader 中提取 token
        String token = extractTokenFromHeader(authHeader);
        User user = JWTUtil.decodeToken(token);
        if(user == null) {
            return ResponseHelper.sendResponse(401, null);
        }
        // 上传头像到远程服务器
        String newAvatarUrl = uploadImageToServer(avatar);
        if(newAvatarUrl == null){
            // 处理保存头像文件时出现的异常
            return ResponseHelper.sendResponse(500, null,"Failed to upload avatar");
        }
        // 返回成功响应
        return ResponseHelper.sendResponse(200, newAvatarUrl);
    }

    // 更新头像
    @GetMapping("/updateAvatar")
    public ResponseEntity updateAvatar(@RequestHeader("Authorization") String authHeader
            , @RequestParam String avatar){
        // 从 authHeader 中提取 token
        String token = extractTokenFromHeader(authHeader);
        User user = JWTUtil.decodeToken(token);
        if(user == null) {
            return ResponseHelper.sendResponse(401, null);
        }

        String realAvatar = avatar.substring(1, avatar.length() - 1);
        String newToken = userService.updateAvatar(realAvatar, user);
        if(newToken != null){
            return ResponseHelper.sendResponse(200, newToken,"ok");
        }else{
            return ResponseHelper.sendResponse(500, null,"failed");
        }
    }

    private String uploadImageToServer(MultipartFile file) {
        try {
            // 构建上传文件的 URL
            String uploadUrl = "http://47.116.49.82:3000/api/uploadAvatar";

            // 生成随机文件名
            String randomFileName = generateRandomFileName(file.getOriginalFilename());

            // 创建 RestTemplate 对象
            RestTemplate restTemplate = new RestTemplate();

            // 创建文件上传的请求体
            MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
            requestBody.add("avatar", new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return randomFileName;
                }
            });

            // 发送 POST 请求并获取响应
            ResponseEntity<NewAvatarUrl> responseEntity = restTemplate.postForEntity(uploadUrl, requestBody, NewAvatarUrl.class);

            // 获取图片访问地址
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return responseEntity.getBody().getData().getAvatar();
            } else {
                throw new RuntimeException("文件上传失败,状态码: " + responseEntity.getStatusCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String generateRandomFileName(String originalFileName) {
        // 获取文件扩展名
        int dotIndex = originalFileName.lastIndexOf(".");
        String fileExtension = dotIndex > 0 ? originalFileName.substring(dotIndex) : "";

        // 生成以时间戳为基础的随机文件名
        long timestamp = System.currentTimeMillis();
        String randomFileName = String.format("%d%s", timestamp, fileExtension);

        return randomFileName;
    }


    private String extractTokenFromHeader(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
    // 生成验证码
    @GetMapping("/validatecode/{phone}")
    private ResponseEntity validatecode(@PathVariable("phone") String phone){
        String code = ValidateCodeUtils.generateValidateCode(6).toString();
        System.out.println("验证码：" + code);
        try{
            SMSUtils.sendShortMessage(phone,code);//发送验证码
            //将验证码保存到redis，只保存20分钟
            jedisPool.getResource().setex(phone +
                    RedisMessageConstant.SENDTYPE_LOGIN,60 * 20,code);
            return ResponseHelper.sendResponse(200, null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseHelper.sendResponse(400, null);
        }
    }
    // 注册
    @PostMapping("/register")
    private ResponseEntity register(@RequestBody RegisterRequest registerRequest){
        String phone = registerRequest.getPhone();
        String password = registerRequest.getPassword();
        String code = registerRequest.getCode();
        //从redis中获取提前保存的验证码
        String codeInRedis = jedisPool.getResource().get(phone +
                RedisMessageConstant.SENDTYPE_LOGIN);
        //校验用户输入的短信验证码是否正确，如果验证码错误则登录失败
        if(codeInRedis == null || !codeInRedis.equals(code)){
            //验证码错误
            return ResponseHelper.sendResponse(200,null,"验证码错误！");
        }
        String token = userService.register(phone, password);
        if(token.equals("手机号已被注册")){
            return ResponseHelper.sendResponse(200,null,"手机号已被注册！");
        }
        return ResponseHelper.sendResponse(200, token, "ok");
    }
}