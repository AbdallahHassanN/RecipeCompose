package com.example.recipescompose.domain.network

import com.example.recipescompose.domain.network.model.RecipeDto
import com.example.recipescompose.domain.network.responses.RecipeResponse
import com.example.recipescompose.domain.network.responses.RecipeSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RecipeService {
    @GET("search")
    suspend fun search(
        @Header("Authorization") token : String,
        @Query("page") page:Int,
        @Query("query") query: String
    ): RecipeSearchResponse

    @GET("search")
    suspend fun search0(
        @Header("Authorization") token : String,
        @Query("page") page:Int,
        @Query("query") query: String
    ): Response<RecipeResponse>

    @GET("get")
    suspend fun getById(
        @Header("Authorization") token : String,
        @Query("id") id:Int,
    ): RecipeDto
}