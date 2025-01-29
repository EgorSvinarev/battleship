package ru.csit.battleship.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import ru.csit.battleship.config.security.filter.JwtFilter


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig(
    private val jwtFilter: JwtFilter
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { obj: AbstractHttpConfigurer<*, *> -> obj.disable() }
        http
            .cors { configurer ->
                configurer.configurationSource {
                    CorsConfiguration().applyPermitDefaultValues()
                }
            }
        http
            .authorizeHttpRequests { request ->
                request
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers(
                        "/swagger-ui/**",
                        "/api-docs/**",
                        "/api-ui/**",
                        "/swagger-resources/*",
                        "/v3/api-docs/**"
                    ).permitAll()
                    .anyRequest().authenticated()
            }
        http
            .sessionManagement { session ->
                session
                    .sessionCreationPolicy(STATELESS)
            }
        http
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }

}