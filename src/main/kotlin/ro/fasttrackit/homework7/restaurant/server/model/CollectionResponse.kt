package ro.fasttrackit.homework7.restaurant.server.model

data class CollectionResponse<T>(
    val content: List<T>,
    val pageInfo: PageInfo
) {

}