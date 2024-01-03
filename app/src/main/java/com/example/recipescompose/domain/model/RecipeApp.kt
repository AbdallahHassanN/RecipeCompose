package com.example.recipescompose.domain.model

data class RecipeApp(

    var id:Int?,
    var title: String?,
    var rating: Int?,
    var featured_image: String?,
    var ingredients: List<String>?,
    var date_updated: String?,
) {
    companion object {
        fun Recipe.map() = RecipeApp(
            id = id,
            title = title,
            rating = rating,
            featured_image = featured_image,
            ingredients = ingredients,
            date_updated = date_updated
        )
    }
}