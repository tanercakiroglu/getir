package com.example.reading.is.good.config;

import com.auth0.jwt.JWT;
import com.example.reading.is.good.entity.Customer;
import com.example.reading.is.good.model.ApiError;
import com.example.reading.is.good.model.ApiResponse;
import com.example.reading.is.good.request.CustomerSignInRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.example.reading.is.good.config.SecurityUtil.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper) {
        this.authenticationManager = authenticationManager;
        this.objectMapper = objectMapper;
        setFilterProcessesUrl(SIGN_IN_URL);
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            final CustomerSignInRequest csr = new ObjectMapper().readValue(req.getInputStream(), CustomerSignInRequest.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(csr.getUsername(), csr.getPassword(), new ArrayList<>())
            );
        } catch (IOException e) {
            this.unsuccessfulAuthentication(req, res, null );
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {

        final String token = JWT.create()
                .withSubject(((Customer) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() +  EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        res.setStatus(HttpServletResponse.SC_OK);
        prepareResponse(res,new ApiResponse());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        prepareResponse(response,ApiError
                .builder()
                .status(HttpServletResponse.SC_UNAUTHORIZED)
                .timestamp(LocalDateTime.now())
                .errors(Collections.singletonList("Invalid username or password please check this fields or request structure"))
                .build());

    }

    private void prepareResponse(HttpServletResponse res,ApiResponse apiResponse) throws IOException {
        res.setContentType("application/json;charset=UTF-8");
        final PrintWriter writer = res.getWriter();
        writer.write(objectMapper.writeValueAsString(apiResponse));
        writer.flush();
        writer.close();
    }
}