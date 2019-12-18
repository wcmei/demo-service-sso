package com.wcmei.demo.service.sso.controller;

import com.google.common.collect.Maps;
import com.wcmei.demo.commons.dto.BaseResult;
import com.wcmei.demo.commons.utils.MapperUtils;
import com.wcmei.demo.commons.utils.OkHttpClientUtils;
import com.wcmei.demo.service.sso.config.service.BaseUserDetails;
import io.swagger.annotations.Api;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * @author wcmei
 * @date 2019-12-17
 * @description
 */
@Api(description = "登录接口")
@RestController
public class LoginController {

    private static final String URL_OAUTH_TOKEN = "http://localhost:8103/oauth/token";

    @Value("${sso.oauth2.grant_type}")
    public String oauth2GrantType;

    @Value("${sso.oauth2.client_id}")
    public String oauth2ClientId;

    @Value("${sso.oauth2.client_secret}")
    public String oauth2ClientSecret;

    @Autowired
    public UserDetailsService userDetailsService;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public TokenStore tokenStore;

    @PostMapping("/user/login")
    public BaseResult login(String userAccount, String userPassword) {

        Map<String, Object> result = Maps.newHashMap();
        BaseUserDetails userDetails = (BaseUserDetails) userDetailsService.loadUserByUsername(userAccount);
        if (userDetails == null || !passwordEncoder.matches(userPassword, userDetails.getPassword())) {
            return BaseResult.fail(1, "用户名或密码错误");
        }

        Map<String, String> params = Maps.newHashMap();
        params.put("username", userAccount);
        params.put("password", userPassword);
        params.put("grant_type", oauth2GrantType);
        params.put("client_id", oauth2ClientId);
        params.put("client_secret", oauth2ClientSecret);

        try {
            //oauth_client_details表
            Response response = OkHttpClientUtils.getInstance().postData(URL_OAUTH_TOKEN, params);
            String jsonString = Objects.requireNonNull(response.body()).string();
            Map<String, Object> jsonMap = MapperUtils.json2map(jsonString);
            String token = String.valueOf(jsonMap.get("access_token"));
            result.put("token", token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BaseResult.success(result);
    }

    @PostMapping("/user/info")
    public BaseResult info() throws Exception {
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return BaseResult.success(authentication.getPrincipal());
    }

    //    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/user/logout")
    public BaseResult logout(HttpServletRequest request) {
        // 获取token
        String token = request.getParameter("access_token");
        // 删除token, 进行注销
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return BaseResult.success("用户已注销");
    }

}
