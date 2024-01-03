package com.example.recipescompose.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    @SerializedName("pk")
    var id:Int? = null,
    var cooking_instructions: String? = null,
    var date_added: String? =null,
    var date_updated: String? = null,
    var description: String? = null,
    var featured_image: String? = null,
    var ingredients: List<String>? = listOf(),
    var publisher: String? = null,
    var rating: Int? = 0,
    var source_url: String? = null,
    var title: String? = null
) :  Parcelable