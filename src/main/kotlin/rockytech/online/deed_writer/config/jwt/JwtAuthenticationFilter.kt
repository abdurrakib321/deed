package rockytech.online.deed_writer.config.jwt

import io.jsonwebtoken.JwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import rockytech.online.deed_writer.users.service.UserService
//
//class JwtAuthenticationFilter(private val tokenProvider: JwtTokenUtils, private val userService: UserService) :
//    OncePerRequestFilter() {
//    override fun doFilterInternal(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        filterChain: FilterChain
//    ) {
//        val jwtToken = extractToken(request)
//        if (jwtToken != null) {
//            try {
//                val claims = tokenProvider.parse(jwtToken).body
//                val userId = claims.subject.toString() // Extract user ID from token payload
//                val userDetails = userService.getUserById(userId)
//                val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
//                val context: SecurityContext = SecurityContextHolder.createEmptyContext()
//                context.authentication = authentication
//                SecurityContextHolder.setContext(context)
//            } catch (e: JwtException) {
//                logger.error("JWT verification failed: ${e.message}")
//            }
//        }
//
//        filterChain.doFilter(request, response)
//    }
//
//    private fun extractToken(request: HttpServletRequest): String? {
//        val authorizationHeader = request.getHeader("Authorization")
//        return if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            authorizationHeader.substring(7)
//        } else null
//    }
//
//    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
//        val path = request.servletPath
//        return !path.startsWith("/api/")
//    }
//}


@Component
class JwtAuthenticationFilter(
    private val tokenProvider: JwtTokenUtils,
    private val userService: UserService
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val jwtToken = extractToken(request)
        if (jwtToken != null) {
            try {
                val claims = tokenProvider.parse(jwtToken).body
                val userId = claims.subject
                val userDetails = userService.loadUserByUsername(userId)
                val authorities = claims["authorities"] as List<String>
                val authentication = UsernamePasswordAuthenticationToken(userDetails, null, authorities.map { SimpleGrantedAuthority(it) })
                val context = SecurityContextHolder.createEmptyContext()
                context.authentication = authentication
                SecurityContextHolder.setContext(context)
            } catch (e: JwtException) {
                logger.error("JWT verification failed: ${e.message}")
            }
        }

        filterChain.doFilter(request, response)
    }

    private fun extractToken(request: HttpServletRequest): String? {
        val authorizationHeader = request.getHeader("Authorization")
        return if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            authorizationHeader.substring(7)
        } else null
    }

}