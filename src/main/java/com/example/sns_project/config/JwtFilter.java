package com.example.sns_project.config;

import com.example.sns_project.service.UserService;
import com.example.sns_project.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final String Key;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization:{} ",authorization);

        if(authorization==null || !authorization.startsWith("Bearer ")){
            log.info("authorization is Error ");
            filterChain.doFilter(request,response);
            return;
        }

        //토큰 꺼내기
        String token = authorization.split(" ")[1];
        if(JwtUtil.isExpired(token,Key)){
            log.info("token이 만료되었습니다.");
            filterChain.doFilter(request,response);
            return;
        }
        //UserName Token 에서 꺼내기
        String userName=JwtUtil.getUserName(token,Key);
        log.info("userNAme:{}",userName);
        //권한 부여
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userName,null, List.of(new SimpleGrantedAuthority("USER")));

        //detail 을 넣는다
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);


    }
}
