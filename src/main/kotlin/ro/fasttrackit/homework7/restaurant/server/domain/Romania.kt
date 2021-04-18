package ro.fasttrackit.homework7.restaurant.server.domain

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("romania")
class Romania : City {
    override fun getCities(): List<String> {
        return listOf(
            "Oradea",
            "Cluj",
            "Bors",
            "Csikszereda",
            "Iasi",
            "Brasov",
            "Timisoara",
            "Arad"
        )
    }
}