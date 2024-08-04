package rockytech.online.deed_writer.config.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import rockytech.online.deed_writer.users.model.User
import java.time.Instant
import java.util.*
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
//
//@Component
//class JwtTokenUtils(
//    @Value("\${deedwriter.jwt.lifetime}") val jwtLifeDuration: Long,
//    @Value("\${deedwriter.jwt.secret}") val secretKey: String,
//) {
//    fun generateToken(user: User): String = Jwts.builder()
//        .claim("username", user.phoneNo)
//        .subject(user.id)
//        .id(UUID.randomUUID().toString())
//        .issuedAt(Date())
//        .expiration(Date.from(Instant.now().plusSeconds(jwtLifeDuration)))
//        .signWith(getKey())
//        .compact()
//
//    fun getKey() = SecretKeySpec(
//        Base64.getDecoder().decode(secretKey),
//        "HmacSHA256"
//    )
//
//    fun parse(jwtString: String): Jws<Claims> {
//        return Jwts.parser()
//            .verifyWith(getKey())
//            .build()
//            .parseSignedClaims(jwtString)
//    }
//
//}


@Component
class JwtTokenUtils(
    @Value("\${deedwriter.jwt.lifetime}") val jwtLifeDuration: Long,
    @Value("\${deedwriter.jwt.secret}") val secretKey: String,
) {

    fun generateToken(user: User): String = Jwts.builder()
        .claim("username", user.phoneNo)
        .claim("authorities", user.authoritiess)
        .subject(user.id)
        .id(UUID.randomUUID().toString())
        .issuedAt(Date())
        .expiration(Date.from(Instant.now().plusSeconds(jwtLifeDuration)))
        .signWith(getKey())
        .compact()

    fun getKey(): SecretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey))

    fun parse(jwtString: String): Jws<Claims> {
        return Jwts.parser()
            .setSigningKey(getKey())
            .build()
            .parseClaimsJws(jwtString)
    }
}