package com.app.debugmyapp.ui.recipe.categorylist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.debugmyapp.ui.theme.DebugMyAppTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.app.debugmyapp.R
import com.app.debugmyapp.model.Category

@Composable
fun CategoryListScreen(
    viewModel: MainViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    onCategoryClick: (String) -> Unit
) {
    //LaunchedEffect(true) ensures that the block inside it is executed once when the composable is first composed.
    LaunchedEffect(true) {
        viewModel.fetchCategoryList()
    }

    val uiState by viewModel.uiState.collectAsState()
    val pagingItems = viewModel.pagingData.collectAsLazyPagingItems()

    pagingItems.apply {
        when {
            loadState.refresh is LoadState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }

            }

            else -> {
                LazyColumn(modifier = modifier.fillMaxHeight()) {
                    items(pagingItems.itemCount) {
                        pagingItems[it]?.let { item ->
                            CategoryItem(item = item,
                                itemClciked = {
                                    onCategoryClick(item.strCategory)
                                })
                        }
                    }
                    pagingItems.apply {
                        when {
                            loadState.append is LoadState.Loading -> {
                                item {
                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp)
                                            .wrapContentWidth(Alignment.CenterHorizontally)
                                    )
                                }
                            }
                            loadState.refresh is LoadState.Error -> {
                                // Handle refresh errors
                            }
                            loadState.append is LoadState.Error -> {
                                // Handle append (load more) errors
                            }
                        }
                    }

                }
            }

        }


//    when (uiState) {
//        is UiState.Loading -> {
//            // Display a loading indicator
//            Box(
//                contentAlignment = Alignment.Center,
//                modifier = Modifier.fillMaxSize()
//            ) {
//                CircularProgressIndicator()
//            }
//        }
//
//        is UiState.Success -> {
//
//            LazyColumn(modifier = modifier.fillMaxHeight()) {
//                items(pagingItems.itemCount){
//                    pagingItems[it]?.let { item ->
//                        CategoryItem(item = item,
//                            itemClciked = {
//                                onCategoryClick(item.strCategory)
//                            })
//                    }
//                }
//                pagingItems.apply {
//                    when {
//                        loadState.refresh is LoadState.Loading -> {
//                            item {
//                                Box(modifier = Modifier.fillMaxHeight().fillMaxHeight(),
//                                    contentAlignment = Alignment.Center
//                                    ) {
//                                    CircularProgressIndicator(
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .padding(16.dp)
//                                            .wrapContentWidth(Alignment.CenterHorizontally)
//                                    )
//                                }
//
//                            }
//                        }
//                        loadState.append is LoadState.Loading -> {
//                            item {
//                                Box(modifier = Modifier.fillMaxHeight().fillMaxHeight(),
//                                    contentAlignment = Alignment.Center
//                                ) {
//                                    CircularProgressIndicator(
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .padding(16.dp)
//                                            .wrapContentWidth(Alignment.CenterHorizontally)
//                                    )
//                                }
//                            }
//                        }
//                        loadState.refresh is LoadState.Error -> {
//                            // Handle refresh errors
//                        }
//                        loadState.append is LoadState.Error -> {
//                            // Handle append (load more) errors
//                        }
//                    }
//                }
//            }


    }
//is UiState.Error -> {
//    // Display an error message
//    val message = (uiState as UiState.Error).message
//    Box(
//        contentAlignment = Alignment.Center,
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Text(text = "Error: $message")
//    }
//
//}
//}
}


@Composable
fun CategoryItem(item: Category, modifier: Modifier = Modifier, itemClciked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp)
            .background(color = Color(0xFF8D8A8A))
            .padding(10.dp)
            .clickable { itemClciked() },

        ) {
        Text(
            text = "${item.strCategory}",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            model = item.strCategoryThumb,
            contentDescription = "Translated description of what the image contains",
            placeholder = painterResource(R.drawable.loadingjpg),
            error = painterResource(R.drawable.error_loading),
            contentScale = ContentScale.FillWidth, // Set the content scale here
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            text = "${item.strCategoryDescription}"
        )

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DebugMyAppTheme {
        CategoryItem(item = Category(
            idCategory = "1", strCategory = "test12",
            strCategoryThumb = "https://www.themealdb.com/images/category/beef.png",
            strCategoryDescription = "test"
        ),
            itemClciked = {

            })
    }
}