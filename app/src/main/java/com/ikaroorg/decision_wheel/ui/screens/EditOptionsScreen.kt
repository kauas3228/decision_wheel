package com.ikaroorg.decision_wheel.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import  com.ikaroorg.decision_wheel.R
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.github.skydoves.colorpicker.compose.AlphaSlider
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.ikaroorg.decision_wheel.ui.components.OptionCard
import com.ikaroorg.decision_wheel.ui.theme.Primary
import com.ikaroorg.decision_wheel.viewmodel.ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun EditOptionsScreen(
    navController: NavController,
    viewModel: ViewModel
) {
    val controller = rememberColorPickerController()
    val options by viewModel.options.collectAsStateWithLifecycle()

    var tempColor by remember { mutableStateOf<Color?>(null) }
    var tempText by remember { mutableStateOf<String?>(null) }
    var tempColorRex by remember { mutableStateOf<String?>(null) }
    var showAddOptionDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Column {
                            Text(
                                "Edit Options",
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.headlineSmall
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    navigationIcon = {
                        IconButton(onClick = {navController.navigate("home")}) {
                            Icon(
                                painter = painterResource(R.drawable.arrow_left),
                                contentDescription = "Return to home screen",
                                modifier = Modifier.size(28.dp),
                                tint = MaterialTheme.colorScheme.onBackground
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
                .padding(horizontal = 16.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    "CURRENT CONFIGURATION",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    "Strategy Wheel",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 28.sp
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                if(options.isEmpty()) {
                    Text(
                        "No options found",
                        style = MaterialTheme.typography.headlineSmall,
                        fontSize = 32.sp,
                        color = MaterialTheme.colorScheme.error
                    )
                } else {
                    options.forEach { option ->
                        OptionCard(
                            option = option,
                            onDelete = { viewModel.deleteOption(option.id) }
                        )
                    }
                }
                OutlinedButton(
                    onClick = { showAddOptionDialog = true },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ){
                        Icon(
                            painter = painterResource(R.drawable.plus_circle),
                            contentDescription = "Add new option",
                            tint = MaterialTheme.colorScheme.onSecondary
                        )
                        Text(
                            "Add new option",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }
            }
        }

        if(showAddOptionDialog) {
            Dialog(
                onDismissRequest = {
                    tempColor = null
                    tempText = null
                    tempColorRex = null
                    showAddOptionDialog = false
                },
            ) {
                Column(
                    modifier = Modifier
                        .widthIn(max = 350.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close the add option modal",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(32.dp).clickable{
                                tempColor = null
                                tempText = null
                                tempColorRex = null
                                showAddOptionDialog = false
                            }
                        )
                    }
                    Text(
                        "Create a new option to add in the wheel:",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(12.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(22.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            HsvColorPicker(
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(200.dp)
                                    .padding(10.dp),
                                controller = controller,
                                onColorChanged = { newColor ->
                                    tempColor = newColor.color
                                    tempColorRex = newColor.hexCode
                                },
                                initialColor = Primary,
                            )
                            BrightnessSlider(
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(35.dp),
                                controller = controller,
                                initialColor = Primary
                            )
                            AlphaSlider(
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(35.dp),
                                controller = controller,
                                initialColor = Primary
                            )
                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    "#${tempColorRex}",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .width(90.dp)
                                        .height(50.dp)
                                        .background(tempColor ?: MaterialTheme.colorScheme.primary)
                                ){}
                            }
                        }

                        OutlinedTextField(
                            value = tempText ?: "",
                            onValueChange = { tempText = it },
                            label = { Text("Option name") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Button(
                            onClick = {
                                tempColor?.let { color ->
                                    tempText?.let { text ->
                                        viewModel.addOption(text, color)
                                    }
                                }
                                tempColor = null
                                tempText = null
                                tempColorRex = null
                                showAddOptionDialog = false
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            enabled = tempText?.isEmpty() == false
                        ) {
                            Text(
                                "Add Option",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }
            }
        }
    }
}