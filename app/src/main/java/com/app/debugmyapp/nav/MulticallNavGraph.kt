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
import com.app.debugmyapp.ui.multicall.HomeScreen
import com.app.debugmyapp.ui.multicall.RegistrationScreen
import com.app.debugmyapp.ui.multicall.SettingScreen
import kotlinx.coroutines.CoroutineScope


@Composable
fun MulticallNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    startDestination: String = MulticallNavDestinations.APP_ROUTE,
    navActions: MulticallNavigationActions = remember(navController) {
        MulticallNavigationActions(navController)
    }
) {


    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(
            MulticallNavDestinations.APP_ROUTE,
        ) { entry ->
            RegistrationScreen(
                onRegistrationSuccess = { navActions.navigateToHome() }
            )
        }

        composable(
            MulticallNavDestinations.HOME,
        ) { entry ->
            HomeScreen(
                goSetting = {navActions.navigateToSetting()}
            )
        }

        composable(
            MulticallNavDestinations.SETTING,
        ) { entry ->
            SettingScreen(
                goHome = {navActions.navigateToHome()}
            )
        }

    }
}