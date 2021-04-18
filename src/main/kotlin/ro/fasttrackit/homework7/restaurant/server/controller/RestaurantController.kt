package ro.fasttrackit.homework7.restaurant.server.controller

import com.github.fge.jsonpatch.JsonPatch
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import ro.fasttrackit.homework7.restaurant.server.domain.Restaurant
import ro.fasttrackit.homework7.restaurant.server.exceptions.RestaurantNotFoundException
import ro.fasttrackit.homework7.restaurant.server.model.CollectionResponse
import ro.fasttrackit.homework7.restaurant.server.model.PageInfo
import ro.fasttrackit.homework7.restaurant.server.model.RestaurantFilters
import ro.fasttrackit.homework7.restaurant.server.service.RestaurantService


@RestController
@RequestMapping("restaurants")
class RestaurantController(private val service: RestaurantService) {

    @GetMapping
    fun getAll(filters: RestaurantFilters?, pageable: Pageable): CollectionResponse<Restaurant> {
        val productPage: Page<Restaurant> = service.getAll(filters, pageable)
        return CollectionResponse(
            productPage.content,
            PageInfo(
                productPage.totalPages,
                productPage.numberOfElements,
                pageable.pageNumber,
                pageable.pageSize
            )
        )
    }

    @PostMapping
    fun addRestaurant(@RequestBody restaurant: Restaurant): Restaurant = service.addRestaurant(restaurant)

    @DeleteMapping("{restaurantId}")
    fun removeRestaurant(@PathVariable restaurantId: String): Restaurant = service.deleteRestaurant(restaurantId)
        .orElseThrow { RestaurantNotFoundException("Could not find restaurant with ID: $restaurantId") }

    @PutMapping("{restaurantId}")
    fun replaceRestaurant(@PathVariable restaurantId: String, @RequestBody newRestaurant: Restaurant): Restaurant =
        service.replaceRestaurant(restaurantId, newRestaurant)

    @PatchMapping("{restaurantId}")
    fun patchRestaurant(@PathVariable restaurantId: String, @RequestBody patch: JsonPatch): Restaurant =
        service.patchRestaurant(restaurantId, patch)
}