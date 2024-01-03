package com.example.recipescompose.presentation.ui.recipeList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.findNavController
import com.example.recipescompose.domain.network.util.Constants.TAG
import com.example.recipescompose.presentation.BaseApplication
import com.example.recipescompose.presentation.components.RecipeList
import com.example.recipescompose.presentation.components.SearchAppBar
import com.example.recipescompose.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment : Fragment(){
    @Inject
    lateinit var application: BaseApplication
    lateinit var viewModel: RecipeListViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[RecipeListViewModel::class.java]

        return ComposeView(requireContext()).apply {
            setContent {

                val recipes0 = viewModel.recipes0.value
                val query by viewModel.query.collectAsStateWithLifecycle()

                val loading = viewModel.loading.value
                Log.d(TAG,"Query : $query")

                AppTheme(darkTheme = application.isDark.value,loading = loading) {
                    Scaffold(
                        topBar = {
                            SearchAppBar(query = query,
                                onQueryChanged = { viewModel.onQueryChanged(it) },
                                onExecuteSearch = { viewModel.search0()},
                                onToggleTheme = { application.toggleLightTheme() })
                        }
                    ) { it ->
                        RecipeList(loading = loading, recipes = recipes0,
                            it = it, navController = findNavController())
                    }
                }
            }
        }
    }
}