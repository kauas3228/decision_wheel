package com.ikaroorg.decision_wheel.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ikaroorg.decision_wheel.R
import com.ikaroorg.decision_wheel.ui.theme.Success
import com.ikaroorg.decision_wheel.viewmodel.ViewModel

@Composable
fun SelectLanguageScreen(
    viewModel: ViewModel,
    navController: NavController
){
    val language by viewModel.language.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(R.string.select_language),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedButton(
                    onClick = { viewModel.changeLanguage("Portuguese") },
                    modifier = Modifier.fillMaxWidth(),
                    border = BorderStroke(3.dp, color = if(language == "Portuguese") Success else MaterialTheme.colorScheme.outline),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if(language == "Portuguese") Success.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surface,
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            stringResource(R.string.portuguese),
                            style = MaterialTheme.typography.titleLarge
                        )
                        Image(
                            painter = painterResource(R.drawable.brazil_flag),
                            contentDescription = stringResource(R.string.brazil_flag_desc),
                            modifier = Modifier.size(38.dp)
                        )
                    }
                }
                OutlinedButton(
                    onClick = { viewModel.changeLanguage("Spanish") },
                    modifier = Modifier.fillMaxWidth(),
                    border = BorderStroke(3.dp, color = if(language == "Spanish") Success else MaterialTheme.colorScheme.outline),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if(language == "Spanish") Success.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surface,
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            stringResource(R.string.spanish),
                            style = MaterialTheme.typography.titleLarge
                        )
                        Image(
                            painter = painterResource(R.drawable.spanish_flag),
                            contentDescription = stringResource(R.string.spanish_flag_desc),
                            modifier = Modifier.size(38.dp)
                        )
                    }
                }
                OutlinedButton(
                    onClick = { viewModel.changeLanguage("English") },
                    modifier = Modifier.fillMaxWidth(),
                    border = BorderStroke(3.dp, color = if(language == "English") Success else MaterialTheme.colorScheme.outline),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if(language == "English") Success.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surface,
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            stringResource(R.string.english),
                            style = MaterialTheme.typography.titleLarge
                        )
                        Image(
                            painter = painterResource(R.drawable.united_states_flag),
                            contentDescription = stringResource(R.string.eua_flag_desc),
                            modifier = Modifier.size(38.dp)
                        )
                    }
                }
            }
            Spacer(Modifier.height(24.dp))
            Button(
                onClick = {
                    viewModel.saveIsSelectedLanguage(true)
                    navController.navigate("home") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Success,
                    contentColor = Color(0xffffffff)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    stringResource(R.string.confirm_language),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}