package com.example.reading.is.good.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.reading.is.good.model.ApiError;
import com.example.reading.is.good.model.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import static com.example.reading.is.good.config.SecurityUtil.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private  final ObjectMapper objectMapper ;

    public JWTAuthorizationFilter(AuthenticationManager authManager,ObjectMapper mapper) {
        super(authManager);
        this.objectMapper=mapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String token = req.getHeader(HEADER_STRING);

        if (token == null || !token.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        UsernamePasswordAuthenticationToken authentication;
        try {
             authentication = getAuthentication(token);
        }catch (Exception exception){
            SecurityContextHolder.clearContext();
            prepareResponse(res, ApiError
                    .builder()
                    .status(HttpServletResponse.SC_UNAUTHORIZED)
                    .timestamp(LocalDateTime.now())
                    .errors(Collections.singletonList(exception.getMessage())).build());
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) throws IOException {
        final String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
        if (user != null) {
            return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        }
        throw  new BadCredentialsException("user cant extracted from token");
    }

    private void prepareResponse(HttpServletResponse res, ApiResponse apiResponse) throws IOException {
        res.setContentType("application/json;charset=UTF-8");
        final PrintWriter writer = res.getWriter();
        writer.write(objectMapper.writeValueAsString(apiResponse));
        writer.flush();
        writer.close();
    }
}
