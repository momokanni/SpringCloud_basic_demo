package com.txhl.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.txhl.zuul.constants.CookieConstant;
import com.txhl.zuul.constants.RedisConstant;
import com.txhl.zuul.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 权限过滤，用来区分 卖家和买家
 *
 * @author Administrator
 * @create 2018-11-10 14:14
 */
@Component
public class AuthFilter extends ZuulFilter {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        /**
         * /order/create : 买家访问(cookie里有openid)
         * /order/finish : 卖家访问(cookie里有token，并且对应redis里有值)
         * /product/list : 都可访问
         */
        if("/order/order/create".equals(request.getRequestURI())){
            Cookie cookie = CookieUtil.get(request, CookieConstant.OPENID);
            if (cookie == null || StringUtils.isEmpty(cookie.getValue())){
                requestContext.setSendZuulResponse(false);
                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            }
        }

        if("/order/order/finish".equals(request.getRequestURI())){
            Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
            if (cookie == null || StringUtils.isEmpty(cookie.getValue())
                    || StringUtils.isEmpty(redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE,cookie.getValue())))){

                requestContext.setSendZuulResponse(false);
                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            }
        }
        return null;
    }
}
