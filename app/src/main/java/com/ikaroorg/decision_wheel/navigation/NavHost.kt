package com.ikaroorg.decision_wheel.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ikaroorg.decision_wheel.data.model.InitializedState
import com.ikaroorg.decision_wheel.ui.screens.EditOptionsScreen
import com.ikaroorg.decision_wheel.ui.screens.HomeScreen
import com.ikaroorg.decision_wheel.ui.screens.LoadingScreen
import com.ikaroorg.decision_wheel.ui.screens.SelectLanguageScreen
import com.ikaroorg.decision_wheel.utils.createLocaleContext
import com.ikaroorg.decision_wheel.utils.toLanguageCode
import com.ikaroorg.decision_wheel.viewmodel.ViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: ViewModel = viewModel( factory = ViewModel.providerFactory())

    val languageState by viewModel.initializedState.collectAsState()
    val language by viewModel.language.collectAsState()

    val currentContext = LocalContext.current

    val localizedContext = remember(language) {
        language?.let {
            currentContext.createLocaleContext(it.toLanguageCode())
        } ?: currentContext
    }
    CompositionLocalProvider(LocalContext provides localizedContext) {
        NavHost(
            navController = navController,
            startDestination = when (languageState) {
                is InitializedState.Loading -> "loading"
                is InitializedState.Selected -> "home"
                is InitializedState.NotSelected -> "selectLanguage"
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
}