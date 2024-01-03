package com.example.recipescompose.domain.network.responses

import com.example.recipescompose.domain.network.model.RecipeDto
import com.google.gson.annotations.SerializedName

data class RecipeSearchResponse(
    @SerializedName("count")
    var count:Int,
    @SerializedName("results")
    var recipes: List<RecipeDto>
)