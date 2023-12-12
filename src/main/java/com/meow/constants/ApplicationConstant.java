package com.meow.constants;

public class ApplicationConstant {
    public static final String AUTHORIZATION_NAME = "Authorization";
    public static final String AUTHORIZATION_TOKEN_TYPE = "Bearer ";
    public static final String HOME_PATH = "/";
    public static final String BASE_PATH_REST_API = "api/v1";
    public static final String USER_ROLE = "hasAnyAuthority('USER')";
    public static final String ADMIN_ROLE = "hasAnyAuthority('ADMIN')";
    public static final String IS_AUTH = "isAuthenticated()";
}
