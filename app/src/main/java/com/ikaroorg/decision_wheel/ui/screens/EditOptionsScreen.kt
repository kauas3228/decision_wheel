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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.res.stringResource
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
import com.ikaroorg.decision_wheel.ui.theme.Success
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

    // Dialog confirm and cancel texts

    // Add Option Dialog texts
    val closeModalDesc = stringResource(R.string.close_modal_desc)
    val createOptionTitle = stringResource(R.string.create_option_title)
    val optionNameLabel = stringResource(R.string.option_name_label)
    val addOptionText = stringResource(R.string.add_option)

    // Save Options Dialog
    var showSaveOptionDialog by remember { mutableStateOf(false) }
    var tempSaveOptionsTitle by remember { mutableStateOf<String?>(null) }
    val confirmText = stringResource(R.string.confirm)
    val cancelText = stringResource(R.string.cancel)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Column {
                            Text(
                                stringResource(R.string.edit_title),
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
                                contentDescription = stringResource(R.string.back_desc),
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    stringResource(R.string.current_config),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    stringResource(R.string.strategy_wheel),
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
                        stringResource(R.string.no_options),
                        style = MaterialTheme.typography.headlineSmall,
                        fontSize = 32.sp,
                        color = MaterialTheme.colorScheme.error
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.height(500.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(
                            items = options,
                            key = { option -> option.id}
                        ) { option ->
                            OptionCard(
                                option = option,
                                onDelete = { viewModel.deleteOption(option.id) }
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { showAddOptionDialog = true },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ){
                        Icon(
                            painter = painterResource(R.drawable.plus_circle),
                            contentDescription = stringResource(R.string.add_new_option),
                            tint = MaterialTheme.colorScheme.onSecondary
                        )
                        Text(
                            stringResource(R.string.add_new_option),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }
                Spacer(Modifier.height(12.dp))
                if(options.size >= 2) {
                    Button(
                        onClick = {showSaveOptionDialog = true},
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ){
                            Icon(
                                painter = painterResource(R.drawable.save_icon),
                                contentDescription = "Save options icon",
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.size(28.dp)
                            )
                            Text(
                                "Save Options",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            }
        }
        // Add Option Dialog
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
                            contentDescription = closeModalDesc,
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
                        createOptionTitle,
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
                            label = { Text(optionNameLabel) },
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
                                addOptionText,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }
            }
        }
        // Save Options dialog
        if(showSaveOptionDialog){
            Dialog(
                onDismissRequest = {
                    showSaveOptionDialog = false
                    tempSaveOptionsTitle = null
                }
            ) {
                Column(
                    modifier = Modifier.widthIn(max = 350.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            "Do you want to save the options to a list to use them later?",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            "If you save them, you can use them in the future without having to add each option individually; you will only need to load them on the main page.",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSurface.copy(0.6f)
                        )
                    }
                    OutlinedTextField(
                        value = tempSaveOptionsTitle ?: "",
                        onValueChange = {tempSaveOptionsTitle = it},
                        label = {Text("Write the title of the list")},
                        modifier = Modifier.fillMaxWidth()
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextButton(
                            onClick = {
                                showSaveOptionDialog = false
                                tempSaveOptionsTitle = null
                            }
                        ) {
                            Text(
                                cancelText,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                        Spacer(Modifier.width(12.dp))
                        TextButton(
                            enabled = tempSaveOptionsTitle?.isEmpty() == false,
                            onClick = {
                                tempSaveOptionsTitle?.let { tempSaveOptionsTitle ->
                                    viewModel.addListOptions(
                                        listTitle = tempSaveOptionsTitle,
                                        options = options
                                    )
                                }
                                showSaveOptionDialog = false
                                tempSaveOptionsTitle = null
                            },
                            colors = ButtonDefaults.textButtonColors(
                                disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(0.6f),
                                contentColor = Success
                            )
                        ) {
                            Text(
                                confirmText,
                                style = MaterialTheme.typography.titleMedium,
                            )
                        }
                    }
                }
            }
        }
    }
}