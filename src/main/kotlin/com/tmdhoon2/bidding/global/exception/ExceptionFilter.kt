package com.tmdhoon2.bidding.global.exception

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import java.nio.charset.StandardCharsets

class ExceptionFilter(
    private val objectMapper: ObjectMapper,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: Exception) {
            println(e.cause.toString())
            when (e.cause) {
                is CustomException -> {
                    writeErrorResponse(response, (e.cause as CustomException))
                }

                is ClassCastException -> {
                    writeErrorResponse(response, ForbiddenException(message = "Expired Token"))
                }

                is Exception -> {
                    e.printStackTrace()
                    writeErrorResponse(response, InternalServerErrorException())
                }
            }
        }
    }

    private fun writeErrorResponse(response: HttpServletResponse, exception: CustomException) {
        val errorResponse = ErrorResponse(exception.errorCode.status, exception.message)
        response.apply {
            status = exception.errorCode.status
            contentType = MediaType.APPLICATION_JSON_VALUE
            characterEncoding = StandardCharsets.UTF_8.name()
            writer.write(objectMapper.writeValueAsString(errorResponse))
        }
    }
}