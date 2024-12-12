package com.scafisystems.myridesimulator.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.scafisystems.myridesimulator.ui.screen.HistoryTripScreen
import com.scafisystems.myridesimulator.ui.screen.HomeScreen
import com.scafisystems.myridesimulator.ui.screen.RequestTripScreen
import com.scafisystems.myridesimulator.ui.screen.TripOptionsScreen
import com.scafisystems.myridesimulator.ui.viewModel.MyRideViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationGraph() {
    val navController = rememberAnimatedNavController()
    val viewModel: MyRideViewModel = hiltViewModel()

    AnimatedNavHost(
        navController = navController,
        startDestination = Routes.HomeScreen.name
    ) {

        composableWithAnimation(
            route = Routes.HomeScreen.name,
            navController = navController
        ) {
            HomeScreen(navController = navController)
        }

        composableWithAnimation(
            route = Routes.TripRequestScreen.name,
            navController = navController
        ) {
            RequestTripScreen(navController = navController, viewModel = viewModel)
        }

        composableWithAnimation(
            route = Routes.TripHistoryScreen.name,
            navController = navController
        ) {
            HistoryTripScreen(navController = navController, viewModel = viewModel)
        }

        composableWithAnimation(
            route = Routes.TripOptionsScreen.name,
            navController = navController
        ) {
            TripOptionsScreen(navController = navController, viewModel = viewModel)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.composableWithAnimation(
    route: String,
    navController: NavHostController,
    screen: @Composable () -> Unit,

    ) {
    return composable(
        route = route,
        enterTransition = {
            slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(700))
        },
        exitTransition = {
            slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(700))
        }
    ) {
        BackHandler(enabled = true) {
            navController.popBackStack()
        }
        screen()
    }
}