package com.example.recipescompose.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.recipescompose.domain.model.RecipeApp
import com.example.recipescompose.domain.network.util.Constants.DEFAULT_RECIPE_IMAGE
import com.example.recipescompose.domain.network.util.loadPicture

@Composable
fun RecipeCard(
    recipe: RecipeApp,
    onClick: () -> Unit
) {
    Card(shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(8.dp)) {
        Column {
            recipe.featured_image?.let { url ->
                val image = loadPicture(url = url,
                    defaultImage = DEFAULT_RECIPE_IMAGE).value
                image?.let {img ->
                    Image(bitmap = img.asImageBitmap(),
                        contentDescription = "recipeImage",
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(225.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            recipe.title?.let {title ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 12.dp, bottom = 12.dp,
                            start = 8.dp, end = 8.dp
                        )
                ) {
                    Text(text = title,
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(text = recipe.rating.toString(),
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.End)
                            .align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.headlineSmall)
                }
            }
        }

    }
}