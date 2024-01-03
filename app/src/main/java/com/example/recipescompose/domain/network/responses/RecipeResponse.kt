package com.example.recipescompose.domain.network.responses

import com.example.recipescompose.domain.model.Recipe
import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("results")
    val recipes:List<Recipe>
)