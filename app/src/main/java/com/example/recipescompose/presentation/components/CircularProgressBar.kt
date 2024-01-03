package com.example.recipescompose.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun CircularProgressBar(
    isDisplayed:Boolean
){
    if(isDisplayed) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val (progressBar,text) = createRefs()
            CircularProgressIndicator(
                modifier = Modifier.
                constrainAs(progressBar)
                {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                color = MaterialTheme.colorScheme.primary
            )
            Text(text = "Loading...",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 15.sp
                ),
                modifier = Modifier.constrainAs(text){
                    top.linkTo(progressBar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
        }
    }
}
