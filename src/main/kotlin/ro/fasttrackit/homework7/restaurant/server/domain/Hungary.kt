package ro.fasttrackit.homework7.restaurant.server.domain

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("hungary")
class Hungary : City {
    override fun getCities(): List<String> {
        return listOf(
            "Debrecen",
            "Budapest",
            "Pecs",
            "Nyirbator",
            "Gyor",
            "Bugyi",
            "Tata",
            "Totkomlos"
        );
    }
}