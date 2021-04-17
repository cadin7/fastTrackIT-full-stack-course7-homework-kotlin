package ro.fasttrackit.homework7.restaurant.server.domain

import lombok.AllArgsConstructor
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document(collection = "restaurants")
data class Restaurant(
    @Id
    val id: String = ObjectId.get().toHexString(),
    val name: String,
    val stars: Int,
    val city: String,
    val since: LocalDate
)
