package com.example.recipescompose.presentation.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.recipescompose.presentation.BaseApplication
import com.example.recipescompose.presentation.components.RecipeView
import com.example.recipescompose.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeFragment: Fragment() {
    @Inject
    lateinit var application: BaseApplication

    lateinit var viewModel: RecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[RecipeViewModel::class.java]
        arguments?.getInt("recipeId")?.let {rId ->
            viewModel.getRecipe(rId)
        }

        return ComposeView(requireContext()).apply {
            setContent {
                val loading = viewModel.loading.value
                val recipe = viewModel.recipe.value

                /*RecipesComposeTheme(darkTheme = application.isDark.value,loading = loading){
                    Scaffold { paddingParameters->
                        Box(modifier = Modifier.fillMaxSize()){
                            if(loading&&recipe == null){
                                Text(text = "Loading")
                            } else {
                                recipe?.let { it ->
                                    RecipeView(recipe = it, it = paddingParameters)
                                }
                            }
                        }
                    }
                }*/
                AppTheme(darkTheme = application.isDark.value,loading = loading) {
                    Scaffold { paddingValues ->
                        if (recipe != null) {
                            RecipeView(recipe = recipe, it = paddingValues)
                        }
                    }
                }
            }
        }
    }
}