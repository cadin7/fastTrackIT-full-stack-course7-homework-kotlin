package ro.fasttrackit.homework7.restaurant.server.service

import org.springframework.stereotype.Service
import ro.fasttrackit.homework7.restaurant.server.domain.Restaurant
import ro.fasttrackit.homework7.restaurant.server.repository.RestaurantRepository
import java.util.*

@Service
class RestaurantService(private val repository: RestaurantRepository) {

    fun getAll(stars: List<String>?, city: String?): List<Restaurant> = repository.findAll()

    fun addRestaurant(restaurant: Restaurant): Restaurant = repository.save(restaurant)

    fun deleteRestaurant(restaurantId: String): Optional<Restaurant> {
        val restaurantToDelete = getRestaurant(restaurantId)
        repository.deleteById(restaurantId)
        return restaurantToDelete
    }

    private fun getRestaurant(restaurantId: String): Optional<Restaurant> = repository.findById(restaurantId)

}
