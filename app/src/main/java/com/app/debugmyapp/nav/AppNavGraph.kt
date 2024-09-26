package com.app.debugmyapp.nav

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.debugmyapp.nav.AppDestinationsArgs.CATGGORY_NAME_ARG
import com.app.debugmyapp.nav.AppDestinationsArgs.RECIPE_ID_ARG
import com.app.debugmyapp.ui.recipe.categorylist.CategoryListScreen
import com.app.debugmyapp.ui.recipe.recipedetail.RecipeDetail
import com.app.debugmyapp.ui.recipe.recipedetail.RecipeDetailScreen
import com.app.debugmyapp.ui.recipe.recipelist.RecipeListScreen
import kotlinx.coroutines.CoroutineScope

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    startDestination: String = AppNavDestinations.APP_ROUTE,
    navActions: AppNavigationActions = remember(navController) {
        AppNavigationActions(navController)
    }
) {


    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(
            AppNavDestinations.APP_ROUTE,
        ) { entry ->
            CategoryListScreen(
                onCategoryClick = { categoryName -> navActions.navigateToRecipeListTaskTask(categoryName) },
            )
        }

        composable(
            AppNavDestinations.RECIPE_LIST_ROUTE,
        ) { entry ->
            RecipeListScreen(
                categoryName = entry.arguments?.getString(CATGGORY_NAME_ARG)!!,
                onRecipeClick = { recipeId -> navActions.navigateToRecipeDetailTaskTask(recipeId) },
                onBack = { navController.navigateUp() }
            )
        }

        composable(
            AppNavDestinations.RECIPE_DETAIL_ROUTE,
        ) { entry ->
            RecipeDetailScreen(
                recipeId = entry.arguments?.getString(RECIPE_ID_ARG)!!,
            )
        }

    }
}