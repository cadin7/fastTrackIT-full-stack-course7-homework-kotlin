package ro.fasttrackit.homework7.restaurant.server.service

import com.fasterxml.jackson.core.TreeNode
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.treeToValue
import com.github.fge.jsonpatch.JsonPatch
import org.springframework.stereotype.Service
import ro.fasttrackit.homework7.restaurant.server.domain.Restaurant
import ro.fasttrackit.homework7.restaurant.server.exceptions.RestaurantNotFoundException
import ro.fasttrackit.homework7.restaurant.server.repository.RestaurantRepository
import java.util.*

@Service
class RestaurantService(
    private val repository: RestaurantRepository,
    private val mapper: ObjectMapper
) {
    fun getAll(stars: List<Int>?, city: String?): List<Restaurant> = checkRequestParams(stars, city)

    fun addRestaurant(restaurant: Restaurant): Restaurant = repository.save(restaurant)

    fun deleteRestaurant(restaurantId: String): Optional<Restaurant> {
        val restaurantToDelete = repository.findById(restaurantId)
        repository.deleteById(restaurantId)
        return restaurantToDelete
    }

    private fun getRestaurantOrElseThrow(restaurantId: String): Restaurant = repository.findById(restaurantId)
        .orElseThrow { RestaurantNotFoundException("Could not find restaurant with ID: $restaurantId") }

    private fun checkRequestParams(stars: List<Int>?, city: String?): List<Restaurant> {
        return if (stars == null && city == null) repository.findAll()
        else if (stars != null && city == null) repository.findAllByStarsIn(stars)
        else if (stars == null && city != null) repository.findAllByCityIgnoreCase(city)
        else repository.findAllByStarsInAndCityIgnoreCase(stars!!, city!!)
    }

    fun replaceRestaurant(restaurantId: String, newRestaurant: Restaurant): Restaurant {
        val dbRestaurant = getRestaurantOrElseThrow(restaurantId)

        return repository.save(
            Restaurant(
                id = dbRestaurant.id,
                name = newRestaurant.name,
                stars = newRestaurant.stars,
                city = newRestaurant.city,
                since = dbRestaurant.since
            )
        )
    }

    fun patchRestaurant(restaurantId: String, patch: JsonPatch): Restaurant {
        val dbRestaurant = getRestaurantOrElseThrow(restaurantId)

        val patchedJson = patch.apply { mapper.valueToTree<JsonNode>(dbRestaurant) }
        //val patchedRestaurant = mapper.treeToValue<Restaurant>(patchedJson)

        //return replaceRestaurant(restaurantId, patchedRestaurant!!)
        return replaceRestaurant(restaurantId, dbRestaurant)
    }
}
