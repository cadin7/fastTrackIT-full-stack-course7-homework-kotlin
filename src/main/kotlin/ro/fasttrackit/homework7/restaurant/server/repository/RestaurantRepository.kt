package ro.fasttrackit.homework7.restaurant.server.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ro.fasttrackit.homework7.restaurant.server.domain.Restaurant

interface RestaurantRepository : MongoRepository<Restaurant, String> {

    fun findAllByCity(city : String): Restaurant

    fun findAllByStarsIn(stars : List<String>): Restaurant

    
}
