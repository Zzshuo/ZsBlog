package com.zs.blog.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.google.common.collect.ImmutableMap;
import com.zs.blog.consts.CommonConst;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class JwtUtil {

    public static String createToken(String username, Date expireDate) {
        return createToken(ImmutableMap.of(CommonConst.USERNAME, username), expireDate);
    }

    /**
     * @param claim 自定义字段
     * @return
     */
    public static String createToken(Map<String, String> claim, Date expireDate) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(CommonConst.JWT_SECRET);

            //头部信息
            Map<String, Object> map = new HashMap<>();
            //类型
            map.put("typ", "JWT");
            //算法名称
            map.put("alg", "HS256");

            JWTCreator.Builder builder = JWT.create();
            //自定义Payload字段
            if (Objects.nonNull(claim)) {
                for (Map.Entry<String, String> entry : claim.entrySet()) {
                    builder.withClaim(entry.getKey(), entry.getValue());
                }
            }
            String token = builder
                    // 设置头部信息 Header
                    .withHeader(map)
                    // jwt签发者
                    .withIssuer(CommonConst.JWT_ISSUER)
                    // jwt的签发时间
                    .withIssuedAt(new Date())
                    // jwt的过期时间，这个过期时间必须要大于签发时间
                    .withExpiresAt(expireDate)
                    /*签名 Signature */
                    .sign(algorithm);
            log.info("creat token:{}", token);
            return token;
        } catch (JWTCreationException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("JWT 签名错误", e);
        }
    }

    /**
     * 验证 token
     *
     * @param token
     */
    public static boolean verify(String token) {
        Algorithm algorithm = Algorithm.HMAC256(CommonConst.JWT_SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(CommonConst.JWT_ISSUER)
                .build();
        verifier.verify(token);
        return true;
    }

    public static String getAudience(String token) {
        return JWT.decode(token).getAudience().get(0);
    }

    /**
     * 获得Token中的信息无需secret解密也能获得
     */
    public static Map<String, Claim> getClaims(String token) {
        return JWT.decode(token).getClaims();
    }

    /**
     * 获得Token中的信息无需secret解密也能获得
     */
    public static String getClaim(String token, String key) {
        return JWT.decode(token).getClaim(key).asString();
    }
}
