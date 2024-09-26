package com.app.debugmyapp.ui.recipe.recipelist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.app.debugmyapp.R
import com.app.debugmyapp.model.Recipe

@Preview
@Composable
fun RecipeListScreen(viewModel: RecipeListViewModel = hiltViewModel(),
                     modifier: Modifier = Modifier,
                     categoryName : String,
                     onRecipeClick : (recipeId : String) -> Unit,
                     onBack : () -> Unit,) {
    //LaunchedEffect(true) ensures that the block inside it is executed once when the composable is first composed.
    LaunchedEffect(true) {
        viewModel.fetchRecipeList(categoryName)
    }

    val uiState by viewModel.items.collectAsState()
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
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
                val items = (uiState as UiState.Success<List<Recipe>>).data
                ConstraintLayout(modifier = Modifier.fillMaxSize()) {

                    val (lazyColumn, button) = createRefs()

                    LazyColumn(
                        modifier = Modifier.constrainAs(lazyColumn) {
                            top.linkTo(parent.top)
                            bottom.linkTo(button.top)
                            height = Dimension.fillToConstraints
                        }
                    ) {
                        items(items) {
                            RecipeItem(item = it,
                                onlick ={
                                    onRecipeClick(it.idMeal)
                                })
                        }
                    }

                    Button(
                        onClick = { onBack()},
                        modifier = Modifier.constrainAs(button) {
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                            .padding(16.dp),
                        shape = RoundedCornerShape(0.dp)
                    ) {
                        Text(text = "Go Back")
                    }

                }
            }
            is UiState.Error -> {
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


}


@Composable
fun RecipeItem(item : Recipe, modifier: Modifier = Modifier,onlick :() ->Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp)
            .background(color = Color(0xFF8D8A8A))
            .padding(10.dp)
            .clickable { onlick() }
    ) {
        Text(text = "${item.strMeal}",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            model = item.strMealThumb,
            contentDescription = "Translated description of what the image contains",
            placeholder = painterResource(R.drawable.loadingjpg),
            error = painterResource(R.drawable.error_loading),
            contentScale = ContentScale.FillWidth, // Set the content scale here
        )
    }

}
