package com.ikaroorg.decision_wheel.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ikaroorg.decision_wheel.R
import com.ikaroorg.decision_wheel.ui.components.DecisionWheel
import com.ikaroorg.decision_wheel.utils.getSelectedOption
import com.ikaroorg.decision_wheel.viewmodel.ViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: ViewModel
) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val rotation = remember { androidx.compose.animation.core.Animatable(0f) }

    // Observe states in viewModel
    val selectedOption by viewModel.selectedOption.collectAsState()
    val options by viewModel.options.collectAsState()

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
                .padding(innerPadding)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            DecisionWheel(
                options = options,
                rotateAngle = rotation.value
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        scope.launch {
                            viewModel.clearSelectedOption()

                            // draws to 3 to 6 laps and random stop
                            val randomTarget = rotation.value + (360f * (3..6).random()) + (0..360).random()
                            rotation.animateTo(
                                targetValue = randomTarget,
                                animationSpec = tween(
                                    durationMillis = 4000,
                                    easing = FastOutSlowInEasing // slow-motion effect
                                )
                            )

                            val winner = getSelectedOption(rotation.value, options)
                            viewModel.onSpinFinished(winner)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .dropShadow(
                            shape = RoundedCornerShape(20.dp),
                            shadow = Shadow(
                                radius = 10.dp,
                                spread = 2.dp,
                                color = Color(0x40000000),
                                offset = DpOffset(0.dp, 4.dp)
                            )
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    enabled = !rotation.isRunning && options.size > 1
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.refresh_icon),
                            contentDescription = null,
                            modifier = Modifier.size(42.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            "SPIN",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
                OutlinedButton(
                    onClick = {navController.navigate("edit")},
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary,
                    ),
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.outline),
                    enabled = !rotation.isRunning
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.pencil),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.onSecondary
                        )
                        Spacer(Modifier.width(12.dp))
                        Text(
                            "Edit options",
                            style = MaterialTheme.typography.titleLarge,
                            fontSize = 18.sp
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(128.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .border(2.dp, MaterialTheme.colorScheme.outline),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    "announcement",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            selectedOption?.let {
                ModalBottomSheet(
                    onDismissRequest = { viewModel.clearSelectedOption() }
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(26.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                "RESULT",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                            Text(
                                selectedOption!!.text,
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Black,
                                fontSize = 26.sp
                            )
                        }
                        Box(
                            modifier = Modifier
                                .width(120.dp)
                                .height(120.dp)
                                .background(MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(100))
                                .clip(RoundedCornerShape(100))
                                .border(2.dp, MaterialTheme.colorScheme.outline, shape = RoundedCornerShape(100)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.party_horn),
                                contentDescription = "party horn symbolizing that the draw has been completed",
                                modifier = Modifier.size(64.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        OutlinedButton(
                            onClick = {viewModel.clearSelectedOption()},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = MaterialTheme.colorScheme.surface,
                                contentColor = MaterialTheme.colorScheme.primary,
                            ),
                            border = BorderStroke(2.dp, MaterialTheme.colorScheme.outline),
                            enabled = !rotation.isRunning && options.size > 1
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.refresh_icon),
                                    contentDescription = null,
                                    modifier = Modifier.size(32.dp),
                                    tint = if (!rotation.isRunning && options.size > 1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(Modifier.width(12.dp))
                                Text(
                                    "Spin again",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
