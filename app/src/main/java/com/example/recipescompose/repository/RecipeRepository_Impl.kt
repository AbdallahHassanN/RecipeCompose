package com.example.recipescompose.repository

import com.example.recipescompose.domain.model.Recipe
import com.example.recipescompose.domain.network.RecipeService
import com.example.recipescompose.domain.network.model.RecipeDtoMapper
import com.example.recipescompose.domain.network.responses.RecipeResponse
import com.example.recipescompose.domain.network.responses.Resource
import com.example.recipescompose.domain.network.util.handleResponse
import kotlinx.coroutines.flow.Flow

class RecipeRepository_Impl(
    private val recipeService: RecipeService,
    private val mapper: RecipeDtoMapper,
):RecipeRepository {
    override suspend fun search(token: String, page: Int, query: String
    ): List<Recipe> {
        return mapper.toDomainList(recipeService
            .search(token = token, page = page, query = query).recipes)
    }

    override suspend fun search0(
        token: String,
        page: Int,
        query: String
    ): Flow<Resource<RecipeResponse>> {
        val response = recipeService.search0(token, page, query)
        return handleResponse(response)
    }

    override suspend fun get(token: String, id: Int
    ): Recipe {
        return mapper.mapToDomainModel(recipeService
            .getById(token = token, id))
    }
}