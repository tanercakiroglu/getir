package com.example.reading.is.good.config;


import lombok.Value;

@Value
class SecurityUtil {

    static final String SECRET = "5A9D6FB223EAD9CCEEDF9F38BB187";
    static final long EXPIRATION_TIME = 3000000;
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    static final String SIGN_IN_URL = "/api/customer/sign-in";
    static final String SIGN_UP_URL = "/api/customer/sign-up";
    static final String H2_URL = "/h2-console/**";
    static final String SWAGGER_UI_HTML = "/swagger-ui.html";
    static final String WEBJARS = "/webjars/**";
    static final String V_2 = "/v2/**";
    static final String SWAGGER_RESOURCES = "/swagger-resources/**";
    static final String SWAGGER_UI = "/swagger-ui/**";
    static final String API_DOCS_URL = "/v3/api-docs";
    static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";
    static final String JWT_STRING = "JWT";
    static final String HEADER = "header";
    static final String LOCALHOST_8080 = "localhost:8080";
    static final String GLOBAL = "global";
    static final String ACCESS_EVERYTHING = "accessEverything";


}
