package org.d3if0107.assesment2.ui.ui.Screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0107.assesment2.R
import org.d3if0107.assesment2.database.PesananDb
import org.d3if0107.assesment2.ui.ui.Theme.Assesment2Theme
import org.d3if0107.assesment2.util.ViewModelFactory

const val KEY_ID_PESANAN = "idPesanan"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navConreoller: NavHostController, id: Long? = null) {
    val context = LocalContext.current
    val db = PesananDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)

    val viewModel: DetailViewModel = viewModel(factory = factory)

    var judul by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var pesanan by remember { mutableStateOf("") }
    var jenis by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }

    val jenisOptions = listOf(
        "Makan Ditempat",
        "Dibungkus",
    )

    LaunchedEffect(true){
        if(id == null) return@LaunchedEffect
        val data = viewModel.getPesanan(id) ?: return@LaunchedEffect
        judul = data.orderan
        name = data.nama
        pesanan = data.pesanan
        jenis = data.jenis
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navConreoller.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    if (id == null)
                        Text(text = stringResource(id = R.string.tambah_pesanan))
                    else
                        Text(text = stringResource(id = R.string.edit_pesanan))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(onClick = {
                        if(judul == "" || name == "" || pesanan == "" || jenis == ""){
                            Toast.makeText(context, R.string.invalid, Toast.LENGTH_LONG).show()
                            return@IconButton
                        }
                        if (id == null){
                            viewModel.insert(judul, name, pesanan, jenis)
                        }else{
                            viewModel.update(id,judul, name, pesanan, jenis)
                        }
                        navConreoller.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(id = R.string.simpan),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    if (id != null) {
                        DeleteAction { showDialog = true }
                        DisplayAlertDialog(
                            openDialog = showDialog,
                            onDismissRequest = { showDialog = false }
                        ) {
                            showDialog = false
                            viewModel.delete(id)
                            navConreoller.popBackStack()
                        }
                    }
                }
            )
        }
    ) { padding ->
        FormPesanan(
            orders = judul,
            onOrdersChange = { judul = it },
            name = name,
            onNameChange = { name = it },
            pesanan = pesanan,
            onPesananChange = { pesanan = it },
            jenis = jenis,
            onJenisChange = { jenis = it },
            jenisOptions = jenisOptions,
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun FormPesanan(
    orders: String, onOrdersChange: (String) -> Unit,
    name: String, onNameChange: (String) -> Unit,
    pesanan: String, onPesananChange: (String) -> Unit,
    jenis: String, onJenisChange: (String) -> Unit,
    jenisOptions: List<String>,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = orders,
            onValueChange = { onOrdersChange(it) },
            label = { Text(text = stringResource(id = R.string.judul)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = name,
            onValueChange = { onNameChange(it) },
            label = { Text(text = stringResource(id = R.string.nama_customer)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = pesanan,
            onValueChange = { onPesananChange(it) },
            label = { Text(text = stringResource(id = R.string.pesanan)) },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(8.dp)
        ) {
            Column {
                jenisOptions.forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        RadioButton(
                            selected = jenis == option,
                            onClick = { onJenisChange(option) }
                        )
                        Text(text = option)
                    }
                }
            }
        }
    }
}
@Composable
fun DeleteAction(delete: () -> Unit) {
    var expanded by remember { mutableStateOf(false)}

    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(id = R.string.lainya),
            tint = MaterialTheme.colorScheme.primary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(text =
            {
                Text(text = stringResource(id = R.string.hapus))
            },
                onClick = {
                    expanded = false
                    delete()
                })
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPriview() {
    Assesment2Theme {
        DetailScreen(rememberNavController())
    }
}
