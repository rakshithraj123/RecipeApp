package com.app.debugmyapp.ui.multicall

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.debugmyapp.ui.recipe.categorylist.MainViewModel

@Composable
fun HomeScreen(
    viewModel: MainViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    goSetting: () -> Unit,
) {
    Box(modifier = modifier
        .fillMaxSize()
        .background(Color.White)
        .verticalScroll(rememberScrollState(), enabled = true)) {
        Button(onClick = { goSetting()}, modifier = Modifier.align(Alignment.Center)) {
            Text(text = "Setting")
        }
    }
}