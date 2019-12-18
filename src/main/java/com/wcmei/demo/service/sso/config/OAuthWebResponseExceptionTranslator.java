package com.wcmei.demo.service.sso.config;

import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

@Component
public class OAuthWebResponseExceptionTranslator extends DefaultWebResponseExceptionTranslator {

//    @Override
//    public ResponseEntity translate(Exception e) throws Exception {
//        if (e instanceof OAuth2Exception) {
//            int code =  1;
//            String message = "密码错误";
//            return ResponseEntity.ok(BaseResult.fail(code, message));
//        }
//        throw e;
//    }
}
