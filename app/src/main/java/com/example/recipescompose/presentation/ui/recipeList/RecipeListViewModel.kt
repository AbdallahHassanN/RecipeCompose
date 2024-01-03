package com.example.recipescompose.presentation.ui.recipeList

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipescompose.domain.model.RecipeApp
import com.example.recipescompose.domain.network.responses.Resource
import com.example.recipescompose.domain.network.util.Constants.TAG
import com.example.recipescompose.useCase.GetRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class RecipeListViewModel
@Inject constructor(
    //private val repository: RecipeRepository,
    //@Named("auth_token") private val token: String,
    private val getRecipeUseCase: GetRecipeUseCase
): ViewModel() {


    val recipes0: MutableState<List<RecipeApp>> = mutableStateOf(listOf())

    private val _query = MutableStateFlow<String>("")
    val query = _query.asStateFlow()

    val loading = mutableStateOf(false)

    init {
        search0()
    }

    /*fun search(){
        viewModelScope.launch {
            loading.value = true
            delay(2000)
            val result = repository.search(
                token = token,
                page = 1,
                query = query.value
            )
            recipes.value = result
            loading.value = false
        }
    }*/

    fun search0() = viewModelScope.launch {
        loading.value = true
        getRecipeUseCase.execute(
            page = 1,
            query = _query.value
        ).catch {
            Log.d(TAG,"Error ${it.message}")
        }.collect{ response ->
            when(response) {
                is Resource.Error ->  Log.d(TAG,"Error response")
                is Resource.Loading -> {
                    loading.value = true
                    Log.d(TAG, "Loading")
                }
                is Resource.Success -> {
                    recipes0.value = response.data!!
                    Log.d(TAG, "dataView ${recipes0.value}")
                    loading.value = false
                }
            }
        }
    }
    fun onQueryChanged(newQuery:String){
        _query.value = newQuery
    }
}

