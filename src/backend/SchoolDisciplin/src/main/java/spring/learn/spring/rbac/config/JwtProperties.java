package spring.learn.spring.rbac.config;

public class JwtProperties {
    public static final String SECRET = "Valgo-SecretForJWTGeneration";
    public static final int EXPIRATION_TIME = 86_400_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}