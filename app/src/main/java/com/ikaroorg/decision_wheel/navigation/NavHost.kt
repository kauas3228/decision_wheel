package com.ikaroorg.decision_wheel.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ikaroorg.decision_wheel.ui.screens.EditOptionsScreen
import com.ikaroorg.decision_wheel.ui.screens.HomeScreen
import com.ikaroorg.decision_wheel.ui.screens.SelectLanguageScreen
import com.ikaroorg.decision_wheel.viewmodel.ViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: ViewModel = viewModel( factory = ViewModel.providerFactory())

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable("edit") {
            EditOptionsScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable("selectLanguage") {
            SelectLanguageScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}