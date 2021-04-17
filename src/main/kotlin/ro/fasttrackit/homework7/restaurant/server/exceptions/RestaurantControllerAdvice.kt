package ro.fasttrackit.homework7.restaurant.server.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestaurantControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RestaurantNotFoundException::class)
    fun handleRestaurantNotFoundException(exception: RestaurantNotFoundException): ApiError =
        ApiError(exception.message)
}

class RestaurantNotFoundException(override val message: String) : RuntimeException(message)

data class ApiError(val message: String)
