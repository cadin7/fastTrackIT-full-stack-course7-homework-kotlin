package ro.fasttrackit.homework7.restaurant.server.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import ro.fasttrackit.homework7.restaurant.server.domain.Restaurant

interface RestaurantRepository : MongoRepository<Restaurant, String> {

    fun findAllByCityIgnoreCase(city : String, pageable: Pageable): Page<Restaurant>

    fun findAllByStarsIn(stars : List<Int>, pageable: Pageable): Page<Restaurant>

    fun findAllByStarsInAndCityIgnoreCase(stars: List<Int>, city: String, pageable: Pageable): Page<Restaurant>

    
}
