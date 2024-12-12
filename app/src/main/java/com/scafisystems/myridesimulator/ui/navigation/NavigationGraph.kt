package com.scafisystems.myridesimulator.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
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
        composable(
            route = Routes.HomeScreen.name,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(700))
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(700))
            }
        ) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Routes.TripRequestScreen.name,
            enterTransition = {
                fadeIn(animationSpec = tween(500))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(500))
            }
        ) {
            BackHandler(enabled = true) {
                navController.popBackStack()
            }
            RequestTripScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            route = Routes.TripHistoryScreen.name,
            enterTransition = {
                expandIn(expandFrom = Alignment.BottomCenter, animationSpec = tween(500))
            },
            exitTransition = {
                shrinkOut(shrinkTowards = Alignment.TopCenter, animationSpec = tween(500))
            }
        ) {
            BackHandler(enabled = true) {
                navController.popBackStack()
            }
            HistoryTripScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            route = Routes.TripOptionsScreen.name,
            enterTransition = {
                slideInVertically(initialOffsetY = { 1000 }, animationSpec = tween(700))
            },
            exitTransition = {
                slideOutVertically(targetOffsetY = { -1000 }, animationSpec = tween(700))
            }
        ) {
            BackHandler(enabled = true) {
                navController.popBackStack()
            }
            TripOptionsScreen(navController = navController, viewModel = viewModel)
        }
    }
}
