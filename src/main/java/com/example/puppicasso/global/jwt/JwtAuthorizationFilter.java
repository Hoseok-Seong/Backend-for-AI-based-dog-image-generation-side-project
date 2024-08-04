package com.example.puppicasso.global.jwt;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.puppicasso.global.error.exception.ErrorCode;
import com.example.puppicasso.global.error.exception.InvalidInputException;
import com.example.puppicasso.global.security.MyUserDetails;
import com.example.puppicasso.domain.user.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String prefixJwt = request.getHeader(JwtProvider.ACCESS_TOKEN_HEADER);

        if(prefixJwt == null){
            chain.doFilter(request, response);
            return;
        }

        String jwt = prefixJwt.replace(JwtProvider.TOKEN_PREFIX, "");

        try {
            DecodedJWT decodedJWT = JwtProvider.verifyAccessToken(jwt);
            Long id = decodedJWT.getClaim("id").asLong();
            String role = decodedJWT.getClaim("role").asString();

            User user = User.builder().id(id).build();
            MyUserDetails myUserDetails = new MyUserDetails(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    myUserDetails,
                    myUserDetails.getPassword(),
                    myUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (SignatureVerificationException sve) {
            log.error("토큰 검증 실패");
            throw new InvalidInputException("토큰이 검증되지 않았습니다. 다시 로그인해주세요.", ErrorCode.INVALID_ACCESS_TOKEN);
        } catch (TokenExpiredException tee) {
            log.error("토큰 만료");
            throw new InvalidInputException("토큰이 만료되었습니다. 다시 로그인해주세요.", ErrorCode.INVALID_ACCESS_TOKEN);
        } finally {
            chain.doFilter(request, response);
        }
    }
}
