package ro.fasttrackit.homework7.restaurant.server.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ro.fasttrackit.homework7.restaurant.server.domain.Restaurant

interface RestaurantRepository : MongoRepository<Restaurant, String> {

    fun findAllByCityIgnoreCase(city : String): List<Restaurant>

    fun findAllByStarsIn(stars : List<Int>): List<Restaurant>

    fun findAllByStarsInAndCityIgnoreCase(stars: List<Int>, city: String): List<Restaurant>

    
}
