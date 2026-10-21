package app.cantas.jcversa.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.cantas.jcversa.BrowserModel
import app.cantas.jcversa.R
import app.cantas.jcversa.data.LocalFolders
import app.cantas.jcversa.data.RenameError
import app.cantas.jcversa.data.RenameResult

@Composable
fun RenameDialog(model: BrowserModel) {
    val state by model.rename.collectAsStateWithLifecycle()
    val browser by model.state.collectAsStateWithLifecycle()
    val operation = state ?: return
    var name by rememberSaveable(operation.parent, operation.entry.path, operation.entry.name) { mutableStateOf(operation.entry.name) }
    val focus = LocalFocusManager.current
    val valid = LocalFolders.validName(name) && name != operation.entry.name
    AlertDialog(onDismissRequest = { if (!operation.busy) model.dismissRename() },
        properties = DialogProperties(dismissOnBackPress = !operation.busy, dismissOnClickOutside = !operation.busy),
        title = { Text(stringResource(R.string.rename_item)) },
        text = { Column(Modifier.heightIn(max = 340.dp).verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(operation.entry.name)
            Text(operation.parent)
            Text(stringResource(R.string.rename_scope), style = MaterialTheme.typography.bodySmall)
            OutlinedTextField(value = name, onValueChange = { name = it.take(256) }, singleLine = true,
                enabled = !operation.busy && operation.result == null, label = { Text(stringResource(R.string.rename_new_name)) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done), keyboardActions = KeyboardActions(onDone = { focus.clearFocus() }),
                isError = !valid && name != operation.entry.name, modifier = Modifier.testTag("rename-name"))
            if (operation.busy) LinearProgressIndicator(Modifier.fillMaxWidth())
            when (val result = operation.result) {
                is RenameResult.Renamed -> Text(stringResource(R.string.rename_verified), modifier = Modifier.testTag("rename-success"))
                is RenameResult.Failed -> Text(stringResource(when (result.error) {
                    RenameError.INVALID_NAME -> R.string.folder_invalid_name
                    RenameError.EXISTS -> R.string.folder_exists
                    RenameError.ACCESS_DENIED -> R.string.rename_access_denied
                    RenameError.UNSUPPORTED -> R.string.rename_unsupported
                    RenameError.UNAVAILABLE -> R.string.rename_unavailable
                    RenameError.UNCERTAIN -> R.string.rename_uncertain
                }), color = MaterialTheme.colorScheme.error, modifier = Modifier.testTag("rename-error"))
                null -> Unit
            }
        } },
        confirmButton = { if (operation.result == null) TextButton(onClick = { focus.clearFocus(); model.renameSelected(name) },
            enabled = valid && !operation.busy && !browser.loading && !browser.error && browser.path == operation.parent, modifier = Modifier.testTag("rename-confirm")) { Text(stringResource(R.string.rename_item)) } },
        dismissButton = { TextButton(onClick = model::dismissRename, enabled = !operation.busy,
            modifier = Modifier.testTag("rename-close")) { Text(stringResource(if (operation.result == null) R.string.cancel else R.string.close_selection)) } })
}
