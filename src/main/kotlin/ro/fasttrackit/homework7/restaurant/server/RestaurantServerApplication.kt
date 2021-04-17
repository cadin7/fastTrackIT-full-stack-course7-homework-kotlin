package ro.fasttrackit.homework7.restaurant.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RestaurantServerApplication

fun main(args: Array<String>) {
    runApplication<RestaurantServerApplication>(*args)
}
