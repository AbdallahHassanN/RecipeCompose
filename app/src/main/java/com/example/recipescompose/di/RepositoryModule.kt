package com.example.recipescompose.di

import com.example.recipescompose.domain.network.RecipeService
import com.example.recipescompose.domain.network.model.RecipeDtoMapper
import com.example.recipescompose.repository.RecipeRepository
import com.example.recipescompose.repository.RecipeRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeDto: RecipeDtoMapper
    ): RecipeRepository {
        return RecipeRepository_Impl(recipeService,recipeDto)
    }
}