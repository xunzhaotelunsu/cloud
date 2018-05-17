package com.baosight.cloud.oauth2.config;

import com.baosight.cloud.oauth2.consume.LoginInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yang on 2018/3/19.
 */
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

    @Autowired
    LoginInterface loginInterface;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("USER"));
        Map<String, Object> result = loginInterface.tryLogin(userName, password);
        if((boolean)result.get("status")){
            return new UsernamePasswordAuthenticationToken(result.get("userId"), password, roles);
        }else{
            throw new BadCredentialsException((String)result.get("msg"));
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
