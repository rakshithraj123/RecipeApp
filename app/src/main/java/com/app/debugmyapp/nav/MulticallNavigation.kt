package com.app.debugmyapp.nav

import androidx.navigation.NavHostController
import com.app.debugmyapp.nav.MulticallNavScreens.HOME_SCREEN
import com.app.debugmyapp.nav.MulticallNavScreens.Registration_SCREEN
import com.app.debugmyapp.nav.MulticallNavScreens.SETTING_SCREEN


private object MulticallNavScreens {
    const val Registration_SCREEN = "registration"
    const val HOME_SCREEN = "home"
    const val SETTING_SCREEN = "setting"
}

object MulticallNavDestinations {
    const val APP_ROUTE = "$Registration_SCREEN"
    const val HOME = "$HOME_SCREEN"
    const val SETTING = "$SETTING_SCREEN"
}

class MulticallNavigationActions(private val navController: NavHostController) {

    fun navigateToHome() {
        navController.navigate(
            "$HOME_SCREEN"
        ){
            // Clear the back stack to prevent back navigation to the login screen
           // popUpTo(Registration_SCREEN) { inclusive = true }
            popUpTo(navController.graph.id) { inclusive = true }
        }
    }

    fun navigateToSetting() {
        navController.navigate(
            "$SETTING_SCREEN"
        )
    }

}

