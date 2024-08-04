package rockytech.online.deed_writer.users.controller

import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import rockytech.online.deed_writer.common.exception.CustomException
import rockytech.online.deed_writer.config.jwt.JwtTokenUtils
import rockytech.online.deed_writer.users.model.User
import rockytech.online.deed_writer.users.service.UserService

@RestController
class AuthController(private val jwtTokenProvider: JwtTokenUtils, private val userService: UserService) {

    @PostMapping("api/auth/login")
    fun getUserDetails(response: HttpServletResponse): UserDetails {
        val userDetails = SecurityContextHolder.getContext().authentication.principal as User
        // Generate JWT token
        val token = jwtTokenProvider.generateToken(userDetails)

        // Create response header with Authorization token
        response.setHeader("Authorization", "Bearer $token")
        // Create Response header with Refresh Token
        return userDetails
    }



    @PostMapping("/api/auth/signup")
    fun signup(@RequestBody user: User): ResponseEntity<User> {
        val newUser = userService.signup(user)
        val jwtToken = jwtTokenProvider.generateToken(newUser)
        val headers = HttpHeaders()
        headers.add("Authorization", "Bearer $jwtToken")
        return ResponseEntity(newUser, headers, HttpStatus.CREATED)
    }

    @PostMapping("/api/auth/signin")
    fun signin(@RequestBody user: User): ResponseEntity<User> {
        val newUser = userService.signup(user)
        val jwtToken = jwtTokenProvider.generateToken(newUser)
        val headers = HttpHeaders()
        headers.add("Authorization", "Bearer $jwtToken")
        return ResponseEntity(newUser, headers, HttpStatus.CREATED)
    }
    @ExceptionHandler(CustomException::class)
    fun handleCustomException(ex: CustomException): ResponseEntity<Map<String, String?>> {
        val errorResponse = mapOf("error" to ex.message)
        return ResponseEntity(errorResponse, HttpStatus.CONFLICT)
    }
}