package com.example.recipescompose.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.recipescompose.domain.model.Recipe
import com.example.recipescompose.domain.network.util.Constants.DEFAULT_RECIPE_IMAGE
import com.example.recipescompose.domain.network.util.Constants.IMAGE_HEIGHT
import com.example.recipescompose.domain.network.util.loadPicture

@Composable
fun RecipeView(
    recipe: Recipe,
    it: PaddingValues,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        recipe.featured_image?.let {
            val image = loadPicture(url = it, defaultImage = DEFAULT_RECIPE_IMAGE).value
            image?.let {
                Image(bitmap = it.asImageBitmap(), contentDescription = "Recipe Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(IMAGE_HEIGHT.dp),
                    contentScale = ContentScale.Crop)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            recipe.title?.let {title ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(text = title,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.titleLarge
                    )
                    val rate = recipe.rating.toString()
                    Text(text = rate,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                recipe.publisher?.let {publisher ->
                    val updated = recipe.date_updated
                    Text(text = if(updated != null) {
                        "Updated $updated by $publisher"
                    }else{
                        "By $publisher"
                    },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(bottom = 8.dp, start = 8.dp),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
                for(ingredients in recipe.ingredients!!) {
                    Text(text = ingredients,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp, start = 8.dp),
                        style = MaterialTheme.typography.bodyLarge)
                }
            }
        }

    }

}