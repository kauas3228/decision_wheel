package com.ikaroorg.decision_wheel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ikaroorg.decision_wheel.navigation.AppNavigation
import com.ikaroorg.decision_wheel.ui.theme.Decision_wheelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Decision_wheelTheme {
                AppNavigation()
            }
        }
    }
}