package com.example.imagedetector.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.imagedetector.R

@Composable
fun PermissionRequestAlertDialog(onRequestPermission: () -> Unit) {
    AlertDialog(
        confirmButton = {onRequestPermission()},
        onDismissRequest = {},
        title = { Text(stringResource(R.string.permission_alert_title)) },
        text = {Text(stringResource(R.string.permission_rationale))}
    )
}