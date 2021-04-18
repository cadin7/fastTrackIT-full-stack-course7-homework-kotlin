package ro.fasttrackit.homework7.restaurant.server.model

data class PageInfo(
    val totalPages: Int,
    val totalElements: Int,
    val crtPage: Int,
    val pageSize: Int
) {
}