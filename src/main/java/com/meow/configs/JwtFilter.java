package com.meow.configs;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import com.meow.Utils.Enums;
import com.meow.Utils.JwtUtil;
import com.meow.dtos.response.ResponseObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String token = JwtUtil.getToken(request);
        if(!StringUtils.hasText(token) || !validateToken(token)) {
            SecurityContextHolder.clearContext();
            filterChain.doFilter(request, response);
            return;
        }
        try {
            DecodedJWT decodedJWT = JwtUtil.getDecodedJwt(token);
            Long userId = Long.parseLong(decodedJWT.getSubject());

            List<GrantedAuthority> author = Collections.singletonList(new SimpleGrantedAuthority(decodedJWT.getClaim("role").asString()));;
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userId, decodedJWT, author);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            String json = new Gson().toJson(
                    ResponseObject.builder().status(Enums.ResponseStatus.ERROR).data(e.getMessage()).build()
            );
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(json);
            out.flush();
            SecurityContextHolder.clearContext();
        }
    }

    public boolean validateToken(String token){
        try {
            JwtUtil.getDecodedJwt(token);
            return true;
        } catch (Exception ex) {
            log.error("Invalid JWT token");
        }
        return false;
    }
}
