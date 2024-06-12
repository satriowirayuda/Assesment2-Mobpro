package org.d3if0107.assesment2.ui.Ui.screen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import org.d3if0107.assesment2.R

@Composable
fun DisplayAlertDialog(
    openDialog: Boolean,
    onDismissRequest: () -> Unit,
    onConfrimation: () -> Unit
) {
    if (openDialog) {
        AlertDialog(
            text = { Text(text = stringResource(R.string.pesan_hapus)) },
            confirmButton = {
                TextButton(onClick = { onConfrimation() }) {
                    Text(text = stringResource(id = R.string.tombol_hapus))
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismissRequest() }) {
                    Text(text = stringResource(id = R.string.tombol_batal))
                }
            },
            onDismissRequest = { onDismissRequest() }
        )
    }
}