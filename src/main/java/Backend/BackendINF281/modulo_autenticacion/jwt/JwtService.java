package Backend.BackendINF281.modulo_autenticacion.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import Backend.BackendINF281.modulo_usuario.models.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service                                                                                                                                                                                                                                                                                                                                                                                                                                                
public class JwtService {

    private static final String SECRET_KEY="64601479114486756327303130436610547181750268544554334116547843060389176995036";

    public String getToken(UserDetails user) {

        return getToken(new HashMap<>(),user);
    }

    private String getToken(Map<String,Object> extraClaim, UserDetails user){
        return Jwts
            .builder()
            .claims(extraClaim)
            .subject(user.getUsername())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis()+1000*60*24))
            .signWith( SignatureAlgorithm.HS256 , getKey())
            .compact();
    }

    private Key getKey() {
        byte[] keybytes=Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keybytes);
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {

        final String username=getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()))&& !isTokenExpired(token);
    }

    private Claims getAllClaims(String token){
        return Jwts
            .parser()
            .setSigningKey(getKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public <T> T getClaim(String token,Function<Claims,T> claimsResolver){

        final Claims claims =getAllClaims(token);
        return claimsResolver.apply(claims);

    }

    private Date getExpiration(String token)
    {
        return getClaim(token,Claims::getExpiration);

    }
    private boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());

    }



}
