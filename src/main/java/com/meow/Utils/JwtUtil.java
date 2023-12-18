package com.meow.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.meow.constants.ApplicationConstant;
import com.meow.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

//helper class for handling with jwt token
public class JwtUtil {
    /*
     * this is jwt secret key use to encode jwt token only backend server hold this
     * key if an attacker know this key his can modify jwt token in the right way to
     * grant access to api
     */
    // some time units constant
    public static final int ONE_SECOND = 1000;
    public static final int ONE_MINUTE = ONE_SECOND * 60;
    public static final int ONE_HOUR = ONE_MINUTE * 60;
    public static final int ONE_DAY = ONE_HOUR * 24;
    public static final String EMAIL_CLAIM_KEY = "email";
    public static final String ROLE_CLAIM_KEY = "role";
    private static final String SECRET_KEY = "WzC_01aSz@";

    public static Algorithm getAlgorithm() {
        return Algorithm.HMAC256(SECRET_KEY.getBytes());
    }

    public static JWTVerifier getVerifier() {
        return JWT.require(getAlgorithm()).build();
    }

    public static DecodedJWT getDecodedJwt(String token) {
        return getVerifier().verify(token);
    }

    public static String generateToken(User user, int expireAfter) {
        return JWT.create()
                .withSubject(String.valueOf(user.getId()))
                .withClaim(EMAIL_CLAIM_KEY, user.getEmail())
                .withClaim(ROLE_CLAIM_KEY, user.getRole().name())
                .withExpiresAt(new Date(System.currentTimeMillis() + expireAfter))
                .sign(getAlgorithm());
    }

    public static String getToken(HttpServletRequest request) {
        // Dùng trong api call lên.
        if (request.getHeader(ApplicationConstant.AUTHORIZATION_NAME) != null) {
            String token_api =  request.getHeader(ApplicationConstant.AUTHORIZATION_NAME)
                    .replaceAll(ApplicationConstant.AUTHORIZATION_TOKEN_TYPE, "").trim();
            // Lấy thông tin token trong header Authentication: Bearer _token.
            String[] listString = token_api.split("\\.");
            if (listString.length != 3) {
                return null;
            }
            return token_api;
        }
        return null;
    }
}
