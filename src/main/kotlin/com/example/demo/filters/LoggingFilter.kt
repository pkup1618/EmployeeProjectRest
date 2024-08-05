package com.example.demo.filters

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.lang.NonNull
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException


@Component
class LoggingFilter : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(@NonNull request: HttpServletRequest,
                                  @NonNull response: HttpServletResponse,
                                  filterChain: FilterChain) {

        val startTime = System.currentTimeMillis()
        filterChain.doFilter(request, response)
        val duration = System.currentTimeMillis() - startTime

        val logMessage = String.format(
            "request method: %s,request URI: %s,response status: %d,request processing time: %d ms",
            request.method, request.requestURI, response.status, duration
        )

        logger.debug(logMessage)
    }
}