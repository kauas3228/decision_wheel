package com.ikaroorg.decision_wheel.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ikaroorg.decision_wheel.data.model.LanguageState
import com.ikaroorg.decision_wheel.ui.screens.EditOptionsScreen
import com.ikaroorg.decision_wheel.ui.screens.HomeScreen
import com.ikaroorg.decision_wheel.ui.screens.LoadingScreen
import com.ikaroorg.decision_wheel.ui.screens.SelectLanguageScreen
import com.ikaroorg.decision_wheel.viewmodel.ViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: ViewModel = viewModel( factory = ViewModel.providerFactory())

    val languageState by viewModel.languageState.collectAsState()


    NavHost(
        navController = navController,
        startDestination = when (languageState) {
            is LanguageState.Loading -> "loading"
            is LanguageState.Selected -> "home"
            is LanguageState.NotSelected -> "selectLanguage"
        }
    ) {
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
            )
        }
        composable("loading") {
            LoadingScreen()
        }
    }
}