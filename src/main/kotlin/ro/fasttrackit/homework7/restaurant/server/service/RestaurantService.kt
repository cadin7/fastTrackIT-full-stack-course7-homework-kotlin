package ro.fasttrackit.homework7.restaurant.server.service

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.fge.jsonpatch.JsonPatch
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import ro.fasttrackit.homework7.restaurant.server.domain.Restaurant
import ro.fasttrackit.homework7.restaurant.server.exceptions.RestaurantNotFoundException
import ro.fasttrackit.homework7.restaurant.server.model.RestaurantFilters
import ro.fasttrackit.homework7.restaurant.server.repository.RestaurantRepository
import java.util.*

@Service
class RestaurantService(
    private val repository: RestaurantRepository,
    private val mapper: ObjectMapper
) {
    fun getAll(filters: RestaurantFilters?, pageable: Pageable): Page<Restaurant> =
        checkRequestParams(filters, pageable)

    fun addRestaurant(restaurant: Restaurant): Restaurant = repository.save(restaurant)

    fun deleteRestaurant(restaurantId: String): Optional<Restaurant> {
        val restaurantToDelete = repository.findById(restaurantId)
        repository.deleteById(restaurantId)
        return restaurantToDelete
    }

    private fun getRestaurantOrElseThrow(restaurantId: String): Restaurant = repository.findById(restaurantId)
        .orElseThrow { RestaurantNotFoundException("Could not find restaurant with ID: $restaurantId") }

    private fun checkRequestParams(filters: RestaurantFilters?, pageable: Pageable): Page<Restaurant> {
        return if (filters?.stars == null && filters?.city == null) repository.findAll(pageable)
        else if (filters.stars != null && filters.city == null)
            repository.findAllByStarsIn(filters.stars, pageable)
        else if (filters.stars == null && filters.city != null)
            repository.findAllByCityIgnoreCase(filters.city, pageable)
        else repository.findAllByStarsInAndCityIgnoreCase(filters.stars!!, filters.city!!, pageable)
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

        //TODO: Learn kotlin
        //val patchedJson = patch.apply { mapper.valueToTree<JsonNode>(dbRestaurant) }
        //val patchedRestaurant = mapper.treeToValue<Restaurant>(patchedJson)
        //return replaceRestaurant(restaurantId, patchedRestaurant!!)

        return replaceRestaurant(restaurantId, dbRestaurant)
    }
}
