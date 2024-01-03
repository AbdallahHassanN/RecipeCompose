package com.example.recipescompose.domain.network.model

import com.example.recipescompose.domain.model.Recipe
import com.example.recipescompose.domain.network.util.DomainMapper

class RecipeDtoMapper : DomainMapper<RecipeDto, Recipe> {
    override fun mapToDomainModel(model: RecipeDto): Recipe {
        return Recipe(
            id = model.pk,
            cooking_instructions = model.cooking_instructions,
            date_added = model.date_added,
            date_updated = model.date_updated,
            featured_image = model.featured_image,
            source_url = model.source_url,
            description = model.description,
            publisher = model.publisher,
            title = model.title,
            ingredients = model.ingredients?: listOf(),
            rating = model.rating
        )
    }

    override fun mapFromDomainModel(domainModel: Recipe): RecipeDto {
        return RecipeDto(
            pk = domainModel.id,
            cooking_instructions = domainModel.cooking_instructions,
            date_added = domainModel.date_added,
            date_updated = domainModel.date_updated,
            featured_image = domainModel.featured_image,
            source_url = domainModel.source_url,
            description = domainModel.description,
            publisher = domainModel.publisher,
            title = domainModel.title,
            ingredients = domainModel.ingredients?: listOf(),
            rating = domainModel.rating
        )
    }
    fun toDomainList(initial: List<RecipeDto>): List<Recipe>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Recipe>): List<RecipeDto>{
        return initial.map { mapFromDomainModel(it) }
    }
}