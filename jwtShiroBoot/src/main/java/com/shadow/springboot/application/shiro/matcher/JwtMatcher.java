package com.shadow.springboot.application.shiro.matcher;

import com.shadow.springboot.application.domain.vo.JwtAccount;
import com.shadow.springboot.application.util.JsonWebTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @author tomsun28
 * @date 18:01 2018/3/3
 */
@Component
public class JwtMatcher implements CredentialsMatcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtMatcher.class);

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {

        String jwt = (String) authenticationInfo.getCredentials();
        LOGGER.debug("start.jwt=" + jwt);
        JwtAccount jwtAccount = null;
        try {
            jwtAccount = JsonWebTokenUtil.parseJwt(jwt, JsonWebTokenUtil.SECRET_KEY);
        } catch (SignatureException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            // 令牌错误
            throw new AuthenticationException("errJwt");
        } catch (ExpiredJwtException e) {
            // 令牌过期
            throw new AuthenticationException("expiredJwt");
        } catch (Exception e) {
            throw new AuthenticationException("errJwt");
        }
        if (null == jwtAccount) {
            throw new AuthenticationException("errJwt");
        }
        LOGGER.debug("end.jwt=" + jwt);
        return true;
    }
}
