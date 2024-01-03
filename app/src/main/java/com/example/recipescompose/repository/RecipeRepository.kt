package com.example.recipescompose.repository

import com.example.recipescompose.domain.model.Recipe
import com.example.recipescompose.domain.network.responses.RecipeResponse
import com.example.recipescompose.domain.network.responses.Resource
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun search(token: String, page: Int, query: String): List<Recipe>

    suspend fun search0(token: String, page: Int, query: String)
            : Flow<Resource<RecipeResponse>>

    suspend fun get(token: String, id: Int): Recipe

}