package app.cantas.jcversa.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.cantas.jcversa.BrowserModel
import app.cantas.jcversa.R
import app.cantas.jcversa.data.FavoriteFolder
import app.cantas.jcversa.data.FavoritePolicy

@Composable
fun FavoritesDialog(model: BrowserModel, close: () -> Unit, open: (String) -> Unit) {
    val status by model.preferences.collectAsStateWithLifecycle()
    val focus = LocalFocusManager.current
    var rename by remember { mutableStateOf<FavoriteFolder?>(null) }
    var title by remember(rename) { mutableStateOf(rename?.title ?: "") }
    var remove by remember { mutableStateOf<FavoriteFolder?>(null) }
    var reset by remember { mutableStateOf(false) }
    AlertDialog(onDismissRequest = close, title = { Text(stringResource(R.string.favorites)) },
        text = {
            LazyColumn(Modifier.fillMaxWidth().heightIn(max = 360.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                item { Text(stringResource(R.string.favorites_scope), style = MaterialTheme.typography.bodySmall) }
                if (status.busy || !status.ready) item { LinearProgressIndicator(Modifier.fillMaxWidth()) }
                if (status.error) item { Text(stringResource(R.string.preferences_error), color = MaterialTheme.colorScheme.error, modifier = Modifier.testTag("preferences-error")) }
                if (status.ready && status.data.favorites.isEmpty()) item { Text(stringResource(R.string.favorites_empty)) }
                itemsIndexed(status.data.favorites, key = { _, favorite -> favorite.key }) { index, favorite ->
                    Column {
                        TextButton(onClick = { open(favorite.key) }, modifier = Modifier.testTag("favorite-open-$index")) {
                            Text(favorite.title)
                        }
                        Text(if (favorite.tree == null) favorite.location else stringResource(R.string.favorite_saf_location),
                            style = MaterialTheme.typography.labelSmall, maxLines = 3)
                        Row {
                            TextButton(onClick = { rename = favorite }, enabled = !status.busy && !status.error,
                                modifier = Modifier.testTag("favorite-rename-$index")) { Text(stringResource(R.string.favorite_rename)) }
                            TextButton(onClick = { remove = favorite }, enabled = !status.busy && !status.error,
                                modifier = Modifier.testTag("favorite-remove-$index")) { Text(stringResource(R.string.favorite_remove)) }
                        }
                    }
                }
                item { TextButton(onClick = model::reloadPreferences, enabled = !status.busy) { Text(stringResource(R.string.preferences_retry)) } }
                item { TextButton(onClick = { reset = true }, enabled = !status.busy) { Text(stringResource(R.string.preferences_reset)) } }
            }
        }, confirmButton = { TextButton(onClick = close, modifier = Modifier.testTag("favorites-close")) { Text(stringResource(R.string.close_selection)) } })
    if (rename != null) AlertDialog(onDismissRequest = { rename = null }, title = { Text(stringResource(R.string.favorite_rename)) },
        text = { OutlinedTextField(value = title, onValueChange = { title = it.take(FavoritePolicy.MAX_TITLE).let { value -> if (value.lastOrNull()?.isHighSurrogate() == true) value.dropLast(1) else value } }, singleLine = true, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focus.clearFocus() }),
            label = { Text(stringResource(R.string.favorite_title)) }, isError = !FavoritePolicy.validTitle(title), modifier = Modifier.testTag("favorite-title")) },
        confirmButton = { TextButton(onClick = { model.renameFavorite(rename!!.key, title); rename = null },
            enabled = FavoritePolicy.validTitle(title) && !status.busy, modifier = Modifier.testTag("favorite-rename-confirm")) { Text(stringResource(R.string.favorite_save)) } },
        dismissButton = { TextButton(onClick = { rename = null }) { Text(stringResource(R.string.cancel)) } })
    if (remove != null) AlertDialog(onDismissRequest = { remove = null }, title = { Text(stringResource(R.string.favorite_remove)) },
        text = { Text(remove!!.title + "\n\n" + stringResource(R.string.favorite_remove_scope)) },
        confirmButton = { TextButton(onClick = { model.removeFavorite(remove!!.key); remove = null }, enabled = !status.busy,
            modifier = Modifier.testTag("favorite-remove-confirm")) { Text(stringResource(R.string.favorite_remove)) } },
        dismissButton = { TextButton(onClick = { remove = null }) { Text(stringResource(R.string.cancel)) } })
    if (reset) AlertDialog(onDismissRequest = { reset = false }, title = { Text(stringResource(R.string.preferences_reset)) },
        text = { Text(stringResource(R.string.preferences_reset_scope)) },
        confirmButton = { TextButton(onClick = { model.resetPreferences(); reset = false }, enabled = !status.busy,
            modifier = Modifier.testTag("preferences-reset-confirm")) { Text(stringResource(R.string.preferences_reset)) } },
        dismissButton = { TextButton(onClick = { reset = false }) { Text(stringResource(R.string.cancel)) } })
}
