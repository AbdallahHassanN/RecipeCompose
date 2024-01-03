package com.example.recipescompose.presentation.components

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.recipescompose.R
import com.example.recipescompose.domain.model.RecipeApp
import com.example.recipescompose.domain.network.util.Constants.TAG

@Composable
fun RecipeList(
    loading:Boolean,
    recipes: List<RecipeApp>?,
    it: PaddingValues,
    navController: NavController
){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background)
        .padding(it)) {
        LazyColumn {
            itemsIndexed(
                items = recipes!!
            ) { index, recipe ->
                RecipeCard(recipe = recipe, onClick = {
                    if(recipe.id != null){
                        val bundle = Bundle()
                        bundle.putInt("recipeId", recipe.id!!)
                        navController.navigate(R.id.viewRecipe,bundle)
                    }else{
                        Log.d(TAG,"Error")
                    }
                })
            }
        }
    }
}