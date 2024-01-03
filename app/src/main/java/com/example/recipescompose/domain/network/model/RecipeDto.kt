package com.example.recipescompose.domain.network.model

import com.google.gson.annotations.SerializedName

data class RecipeDto (
    @SerializedName("pk")
    var pk:Int? = null,
    @SerializedName("cooking_instructions")
    var cooking_instructions: String? = null,
    @SerializedName("date_added")
    var date_added: String? =null,
    @SerializedName("date_updated")
    var date_updated: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("featured_image")
    var featured_image: String? = null,
    @SerializedName("ingredients")
    var ingredients: List<String>? = listOf(),
    @SerializedName("publisher")
    var publisher: String? = null,
    @SerializedName("rating")
    var rating: Int? = 0,
    @SerializedName("source_url")
    var source_url: String? = null,
    @SerializedName("title")
    var title: String? = null
)