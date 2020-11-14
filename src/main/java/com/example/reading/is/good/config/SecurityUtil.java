package com.example.reading.is.good.config;


import lombok.Value;

@Value
class SecurityUtil {

    final static String SECRET = "5A9D6FB223EAD9CCEEDF9F38BB187";
    final static long EXPIRATION_TIME = 3000000;
    final static String TOKEN_PREFIX = "Bearer ";
    final static String HEADER_STRING = "Authorization";
    final static String SIGN_IN_URL = "/api/customer/sign-in";
    final static String SIGN_UP_URL = "/api/customer/sign-up";
    final static String H2_URL = "/h2-console/**";

}
