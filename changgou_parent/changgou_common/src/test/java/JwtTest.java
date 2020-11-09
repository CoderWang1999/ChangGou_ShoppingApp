import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

public class JwtTest {
    @Test
    public void testCreateJwt(){
        JwtBuilder builder = Jwts.builder().
                setId("666").
                setSubject("傻逼").
                setIssuedAt(new Date()).setExpiration(new Date()).
                signWith(SignatureAlgorithm.HS256,"itcast");
        HashMap<String, Object> userInfo = new HashMap<>();
        userInfo.put("userName","Tom");
        userInfo.put("age",21);
        builder.addClaims(userInfo);
        String compact = builder.compact();
        System.out.println(compact);
    }

    @Test
    public void testParseJwt(){
        String compact ="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiLlgrvpgLwiLCJpYXQiOjE2MDQ5MjE5NTJ9.QRokz2WKcIpAq5_T-L93Q8rJqPXpqPaYAaOhRamcZ8A";
        Claims itcast = Jwts.parser().setSigningKey("itcast").parseClaimsJws(compact).getBody();
        System.out.println(itcast);
    }
}
