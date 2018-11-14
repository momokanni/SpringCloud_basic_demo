package com.txhl.user.controller;

import com.netflix.discovery.converters.Auto;
import com.txhl.user.constant.CookieConstant;
import com.txhl.user.constant.RedisConstant;
import com.txhl.user.entity.UserInfo;
import com.txhl.user.enums.ResultEnums;
import com.txhl.user.enums.RoleEnum;
import com.txhl.user.service.UserService;
import com.txhl.user.util.CookieUtil;
import com.txhl.user.util.ResultUtils;
import com.txhl.user.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 用户控制器
 *
 * @author Administrator
 * @create 2018-11-10 21:35
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 买家登录
     * @param openid
     * @param response
     * @return
     */
    @GetMapping(value = "/buyer")
    public ResultVO buyer(@RequestParam(value = "openid") String openid,
                          HttpServletResponse response){
        // 1. openid和数据库数据进行匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null){
            return ResultUtils.error(ResultEnums.BUYER_NOT_EXISTS.getCode(),ResultEnums.BUYER_NOT_EXISTS.getMsg());
        }
        // 2. 判断角色
        if (userInfo.getRole() != RoleEnum.BUYER.getCode()){
            return ResultUtils.error(ResultEnums.ROLE_MISTAKES.getCode(),ResultEnums.ROLE_MISTAKES.getMsg());
        }

        // 3. cookie里设置openid=abc
        CookieUtil.set(response,CookieConstant.OPENID,userInfo.getOpenid(), CookieConstant.expire);

        return ResultUtils.success();
    }

    /**
     * 卖家登录
     * @param openid
     * @param response
     * @return
     */
    @GetMapping(value = "/seller")
    public ResultVO seller(@RequestParam(value = "openid") String openid,
                          HttpServletRequest request,
                          HttpServletResponse response){
        Cookie cookie = CookieUtil.get(request,CookieConstant.TOKEN);
        if (cookie != null &&
                !StringUtils.isEmpty(redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE,cookie.getValue())))){
            return ResultUtils.success();
        }

        // 1. openid和数据库数据进行匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null){
            return ResultUtils.error(ResultEnums.BUYER_NOT_EXISTS.getCode(),ResultEnums.BUYER_NOT_EXISTS.getMsg());
        }
        // 2. 判断角色
        if (userInfo.getRole() != RoleEnum.SELLER.getCode()){
            return ResultUtils.error(ResultEnums.ROLE_MISTAKES.getCode(),ResultEnums.ROLE_MISTAKES.getMsg());
        }

        String token = java.util.UUID.randomUUID().toString();
        // 3. redis设置key=UUID,value=XYZ
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE,token),
                                        userInfo.getOpenid(),CookieConstant.expire,
                                        TimeUnit.SECONDS);

        // 4. cookie里设置token=UUID
        CookieUtil.set(response,CookieConstant.TOKEN,token, CookieConstant.expire);

        return ResultUtils.success();
    }
}
