package rockytech.online.deed_writer.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

import rockytech.online.deed_writer.config.jwt.JwtAuthenticationFilter
import rockytech.online.deed_writer.config.jwt.JwtTokenUtils
import rockytech.online.deed_writer.users.service.UserService


//
//@Configuration
//@EnableWebSecurity
//class SecurityConfig(
//    val tokenProvider: JwtTokenUtils,
//    val userService: UserService
//) {
//
//    @Bean
//    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
//        http
//            .csrf{c -> c.disable()}
//            .cors{ }
//            .authorizeHttpRequests { r ->
//                r.requestMatchers(
//                    HttpMethod.GET,
//                    "/swagger-ui",
//                    "/swagger-ui/**",
//                    "/v3/api-docs",
//                    "/v3/api-docs/**",
//                    "/actuator/**"
//                ).permitAll()
//            }
//            .authorizeHttpRequests { r -> r.requestMatchers(HttpMethod.OPTIONS,"/api/**").permitAll() }
//            .authorizeHttpRequests { r -> r.requestMatchers(HttpMethod.HEAD,"/api/**").permitAll() }
//            .authorizeHttpRequests { r -> r.requestMatchers(HttpMethod.POST,"/api/auth/login").permitAll() }
//             .authorizeHttpRequests { r -> r.anyRequest().hasAuthority("USER") }
//            .addFilterBefore(JwtAuthenticationFilter(tokenProvider,userService),UsernamePasswordAuthenticationFilter::class.java)
//            .formLogin { }
//            .httpBasic { }
//        return http.build()
//    }
//
//    @Bean
//    fun authManager(authConfig: AuthenticationConfiguration): AuthenticationManager {
//        return authConfig.authenticationManager
//    }
//
//    @Bean
//    fun passwordEncoder(): PasswordEncoder {
//        return BCryptPasswordEncoder()
//    }
//    @Bean
//    fun corsConfigurationSource(): CorsConfigurationSource {
//        val configuration = CorsConfiguration()
//        configuration.allowedOrigins = listOf("http://localhost:3100","http://localhost:3000")
//        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
//        configuration.allowedHeaders = listOf("Authorization", "Content-Type")
//        configuration.allowCredentials = true
//
//        val source = UrlBasedCorsConfigurationSource()
//        source.registerCorsConfiguration("/**", configuration)
//        return source
//    }
//}
//


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val tokenProvider: JwtTokenUtils,
    private val userService: UserService
)  {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { c -> c.disable() }
            .cors { }
            .authorizeHttpRequests { r ->
                r.requestMatchers(
                    HttpMethod.GET,
                    "/swagger-ui",
                    "/swagger-ui/**",
                    "/v3/api-docs",
                    "/v3/api-docs/**",
                    "/actuator/**",
                    "/manifest.json",
                    "/static/**"
                ).permitAll()
                r.requestMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
                r.requestMatchers(HttpMethod.HEAD, "/api/**").permitAll()
                r.requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                r.requestMatchers(HttpMethod.POST,"/api/user/addUser").permitAll()
                r.anyRequest().hasAuthority("USER")
            }
            .addFilterBefore(JwtAuthenticationFilter(tokenProvider, userService), UsernamePasswordAuthenticationFilter::class.java)
            .formLogin { }
            .httpBasic { }
        return http.build()
    }

    @Bean
    fun authManager(authConfig: AuthenticationConfiguration): AuthenticationManager {
        return authConfig.authenticationManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*")
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
        configuration.allowedHeaders = listOf("Authorization", "Content-Type")
        configuration.allowCredentials = true

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

}
