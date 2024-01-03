package com.example.recipescompose.useCase

import android.util.Log
import com.example.recipescompose.domain.model.RecipeApp.Companion.map
import com.example.recipescompose.domain.network.responses.Resource
import com.example.recipescompose.domain.network.util.Constants.TAG
import com.example.recipescompose.repository.RecipeRepository
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Named

class GetRecipeUseCase
@Inject constructor(
    private val repo:RecipeRepository,
    @Named("auth_token") private val token: String,
) {
    suspend fun execute(
        page:Int,
        query:String
    )  = repo.search0(token = token, page = page, query = query)
        .flatMapConcat { it ->
            when(it)  {
                is Resource.Error -> {
                    Log.d(TAG,"UseCase Error ?")
                    flowOf(Resource.Error(it.message.toString()))
                }
                is Resource.Loading -> {
                    Log.d(TAG,"UseCase Loading ?")
                    flowOf(Resource.Loading())
                }
                is Resource.Success -> {
                    Log.d(TAG,"UseCase Success ? ${it.data!!.recipes}")
                    flowOf(Resource.Success(it.data.recipes.map {
                        it.map()
                    }))
                }
            }
        }
}