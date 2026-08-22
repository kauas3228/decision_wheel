package com.ikaroorg.decision_wheel.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ikaroorg.decision_wheel.R
import com.ikaroorg.decision_wheel.data.model.Option
import com.ikaroorg.decision_wheel.ui.theme.Success

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionCard(
    option: Option,
    onDelete: () -> Unit
){
    var showAlertDelete by remember { mutableStateOf(false) }


    // Dialog texts
    val deleteConfirmTitle = stringResource(R.string.delete_confirm_title)
    val deleteConfirmText = stringResource(R.string.delete_confirm_text)
    val confirmText = stringResource(R.string.confirm)
    val cancelText = stringResource(R.string.cancel)

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(12.dp))
                .border(2.dp, MaterialTheme.colorScheme.outline, shape = RoundedCornerShape(12.dp))
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(option.color, shape = CircleShape),
            ){}
            Text(
                option.text,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        IconButton(
            onClick = {
                showAlertDelete = true
            },
        ) {
            Icon(
                painter = painterResource(R.drawable.trash),
                contentDescription = stringResource(R.string.delete_option_desc, option.text),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(32.dp)
            )
        }
    }

    if(showAlertDelete){
        AlertDialog(
            onDismissRequest = {showAlertDelete = false},
            title = {
                Text(
                    deleteConfirmTitle,
                    style = MaterialTheme.typography.titleLarge
                )
            },
            text = {
                Text(
                    deleteConfirmText,
                    style = MaterialTheme.typography.labelLarge
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDelete()
                        showAlertDelete = false
                    }
                ) {
                    Text(
                        confirmText,
                        style = MaterialTheme.typography.titleMedium,
                        color = Success
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showAlertDelete = false }
                ) {
                    Text(
                        cancelText,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        )
    }
}