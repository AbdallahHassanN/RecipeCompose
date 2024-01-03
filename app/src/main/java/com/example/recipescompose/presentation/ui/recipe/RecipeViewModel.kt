package com.example.recipescompose.presentation.ui.recipe

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipescompose.domain.model.Recipe
import com.example.recipescompose.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeViewModel
@Inject
constructor(
    private val recipeRepository: RecipeRepository,
    @Named("auth_token") private val token:String,
): ViewModel(){
    val recipe: MutableState<Recipe?> = mutableStateOf(null)
    val loading = mutableStateOf(false)

    fun getRecipe(id:Int) = viewModelScope.launch{
        loading.value = true
        delay(1000)

        val recipe = recipeRepository.get(
            token = token,
            id = id
        )
        this@RecipeViewModel.recipe.value = recipe
        loading.value = false
    }
}