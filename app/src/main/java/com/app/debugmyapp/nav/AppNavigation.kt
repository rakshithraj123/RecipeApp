package com.app.debugmyapp.nav

import androidx.navigation.NavHostController
import com.app.debugmyapp.nav.AppDestinationsArgs.CATGGORY_NAME_ARG
import com.app.debugmyapp.nav.AppDestinationsArgs.RECIPE_ID_ARG
import com.app.debugmyapp.nav.AppNavScreens.CATEGORY_LIST_SCREEN
import com.app.debugmyapp.nav.AppNavScreens.RECIPE_DETAIL_SCREEN
import com.app.debugmyapp.nav.AppNavScreens.RECIPE_LIST_SCREEN

/**
 * Arguments used in [TodoDestinations] routes
 */
object AppDestinationsArgs {
    const val CATGGORY_NAME_ARG = "categry_name"
    const val RECIPE_ID_ARG = "recipe_id"
}

/**
 * Screens used in [AppNavDestinations]
 */
private object AppNavScreens {
    const val CATEGORY_LIST_SCREEN = "category"
    const val RECIPE_LIST_SCREEN = "recipe_list"
    const val RECIPE_DETAIL_SCREEN = "recipe_detail"
}

/**
 * Destinations used in the [TodoActivity]
 */
object AppNavDestinations {
    const val APP_ROUTE = "$CATEGORY_LIST_SCREEN"
    const val RECIPE_LIST_ROUTE = "$RECIPE_LIST_SCREEN/{$CATGGORY_NAME_ARG}"
    const val RECIPE_DETAIL_ROUTE = "$RECIPE_DETAIL_SCREEN?$RECIPE_ID_ARG={$RECIPE_ID_ARG}"
}

/**
 * Models the navigation actions in the app.
 */
class AppNavigationActions(private val navController: NavHostController) {
    fun navigateToRecipeListTaskTask(categoryName: String) {
        navController.navigate(
            "$RECIPE_LIST_SCREEN/$categoryName"
        )
    }

    fun navigateToRecipeDetailTaskTask(recipeId: String) {
        navController.navigate(
            "$RECIPE_DETAIL_SCREEN".let {
                if (recipeId != null) "$it?$RECIPE_ID_ARG=$recipeId" else it
            }
        )
    }

}
