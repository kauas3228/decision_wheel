package com.ikaroorg.decision_wheel.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ikaroorg.decision_wheel.R
import com.ikaroorg.decision_wheel.data.Option
import com.ikaroorg.decision_wheel.ui.components.DecisionWheel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
) {
    val rotation = remember { androidx.compose.animation.core.Animatable(0f) }

    val options = remember {
        listOf<Option>(
            // Using an illustrator colors and texts
            Option("1", "Option 1", Color(0xFF1E40AF)),
            Option("2", "Option 2", Color(0xFF0D9488)),
            Option("3", "Option 3", Color(0xFF14B8A6)),
            Option("4", "Option 4", Color(0xFF3B82F6)),
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Column {
                            Text(
                                "Decision Wheel",
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Spacer(Modifier.height(1.dp))
                            Text(
                                "Make your choice",
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Image(
                                painter = painterResource(R.drawable.logo),
                                contentDescription = "Logo Application",
                                modifier = Modifier.size(36.dp)
                            )
                        }
                    }

                )
                HorizontalDivider(
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DecisionWheel(
                options = options,
                rotateAngle = rotation.value
            )
        }
    }
}