package com.qys.im.config;

import com.qys.im.annotation.LoginUser;
import com.qys.im.pojo.User;
import com.qys.im.utils.JwtUtil;
import com.qys.im.utils.TokenExcption;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class LoginUserHandleMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(User.class) && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest httpServletRequest=webRequest.getNativeRequest(HttpServletRequest.class);
        String token = httpServletRequest.getHeader("Authorization");
        if (StringUtils.isEmpty(token)){
            throw new TokenExcption();
        }
        return jwtUtil.getToken(token);
    }
}
