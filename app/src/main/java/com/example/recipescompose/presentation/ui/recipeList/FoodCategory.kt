package com.example.recipescompose.presentation.ui.recipeList

enum class FoodCategory(val value:String) {
    CHICKEN("Chicken"),
    BEEF("Beef"),
    SOUP("Soup"),
    DESSERT("Dessert"),
    VEGETARIAN("Vegetarian"),
    MILK("Milk"),
    VEGAN("Vegan"),
    PIZZA("Pizza"),
    DONUT("Donut")
}

fun getAllFoodCategories(): List<FoodCategory>{
    return listOf(FoodCategory.CHICKEN,FoodCategory.BEEF,FoodCategory.SOUP,FoodCategory.VEGETARIAN,FoodCategory.DESSERT,
        FoodCategory.MILK,FoodCategory.VEGAN,FoodCategory.PIZZA,FoodCategory.DONUT)
}

fun getFoodCategory(value: String):FoodCategory?{
    val map = FoodCategory.entries.associateBy(FoodCategory::value)
    return map[value]
}