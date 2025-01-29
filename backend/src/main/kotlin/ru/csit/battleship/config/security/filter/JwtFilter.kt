package ru.csit.battleship.config.security.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import ru.csit.battleship.domain.auth.jwt.JwtService


@Component
class JwtFilter(
    private val jwtService: JwtService,
    private val userDetailsService: UserDetailsService,
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val bearer = request.tokenFromRequest()

        bearer?.let { token ->
            val username = jwtService.extractSubject(token)
            val storedUser = userDetailsService.loadUserByUsername(username)

            if (jwtService.validateToken(token)) {
                val authToken = UsernamePasswordAuthenticationToken(
                    storedUser,
                    null,
                    storedUser.authorities
                )
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authToken
            }
        }
        filterChain.doFilter(request, response)
    }

    private fun HttpServletRequest.tokenFromRequest(): String? {
        val bearer = getHeader(AUTHORIZATION)
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7)
        }
        return null
    }

    companion object {
        private const val AUTHORIZATION = "AUTHORIZATION"
    }
}