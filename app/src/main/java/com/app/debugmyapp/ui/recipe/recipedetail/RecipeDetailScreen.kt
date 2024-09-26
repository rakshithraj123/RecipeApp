package com.app.debugmyapp.ui.recipe.recipedetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.app.debugmyapp.R
import com.app.debugmyapp.model.Recipe
import com.app.debugmyapp.model.RecipeDetail

@Composable
fun RecipeDetailScreen(viewModel: RecipeListViewModel = hiltViewModel(),
                       modifier: Modifier = Modifier,
                       recipeId : String) {
    //LaunchedEffect(true) ensures that the block inside it is executed once when the composable is first composed.
    LaunchedEffect(true) {
        viewModel.fetchRecipeDetail(recipeId)
    }

    val uiState by viewModel.items.collectAsState()


    when (uiState) {
        is UiState.Loading -> {
            // Display a loading indicator
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            // Display the list of items
            val item = (uiState as UiState.Success<RecipeDetail>).data
            RecipeDetail(item = item)
        }
        is UiState.Error -> {
            // Display an error message
            val message = (uiState as UiState.Error).message
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "Error: $message")
            }

        }
    }
}


@Composable
fun RecipeDetail(item : RecipeDetail, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp)
            .background(color = Color(0xFF8D8A8A))
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "${item.strMeal}",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        AsyncImage(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 15.dp),
            model = item.strMealThumb,
            contentDescription = "Translated description of what the image contains",
            placeholder = painterResource(R.drawable.loadingjpg),
            error = painterResource(R.drawable.error_loading),
            contentScale = ContentScale.FillWidth, // Set the content scale here
        )

        Text(text = "${item.strInstructions}",
            fontSize = 15.sp,
            modifier = Modifier.padding(top = 30.dp),
            textAlign = TextAlign.Justify,
        )
    }

}
