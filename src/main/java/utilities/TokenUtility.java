package utilities;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Created by jiaweizhang on 11/18/2016.
 */
public class TokenUtility {
    public static String generateToken(String username) {
        String secretKey = "secretKey";

        return Jwts.builder().setSubject(username)
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public static Claims retrieveClaims(String jwt) {
        String secretKey = "secretKey";

        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
    }
}