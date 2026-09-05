package com.ikaroorg.decision_wheel.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
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
import com.ikaroorg.decision_wheel.data.model.ListOptions
import com.ikaroorg.decision_wheel.ui.theme.Success

@Composable
fun ListOptionItem(
    listOption: ListOptions,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    var showAlertDelete by remember { mutableStateOf(false) }

    // Dialog texts
    val confirmText = stringResource(R.string.confirm)
    val cancelText = stringResource(R.string.cancel)
    val dialogTitle = stringResource(R.string.delete_confirm_title)
    val dialogText = stringResource(R.string.delete_confirm_text)

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(12.dp))
                .border(2.dp, MaterialTheme.colorScheme.outline, shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
                .clickable{ onClick() },
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                listOption.title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                listOption.options.size.toString() + " " + stringResource(R.string.complement_option_quantity),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
        IconButton(
            onClick = {
                showAlertDelete = true
            },
        ) {
            Icon(
                painter = painterResource(R.drawable.trash),
                contentDescription = stringResource(R.string.delete_option_desc, listOption.title),
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
                    dialogTitle,
                    style = MaterialTheme.typography.titleLarge
                )
            },
            text = {
                Text(
                    dialogText,
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