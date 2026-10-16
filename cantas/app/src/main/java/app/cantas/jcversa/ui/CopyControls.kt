package app.cantas.jcversa.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.cantas.jcversa.BrowserModel
import app.cantas.jcversa.BrowserState
import app.cantas.jcversa.R
import app.cantas.jcversa.data.CopyStatus

@Composable
fun CopyDestinationControls(model: BrowserModel, state: BrowserState, writable: Boolean, requestWrite: () -> Unit) {
    val clipboard by model.clipboard.collectAsStateWithLifecycle()
    val session by model.copy.collectAsStateWithLifecycle()
    val ready by model.copyReady.collectAsStateWithLifecycle()
    var confirm by remember(state.path) { mutableStateOf(false) }
    if (clipboard.isNotEmpty()) Column(Modifier.padding(horizontal = 16.dp)) {
        Text(stringResource(R.string.copy_staged, clipboard.size), style = MaterialTheme.typography.labelMedium)
        Row {
            TextButton(onClick = { if (writable) confirm = true else requestWrite() },
                enabled = ready && session == null && !state.external && !state.loading && !state.error,
                modifier = Modifier.testTag("paste-here")) { Text(stringResource(if (writable) R.string.paste_here else R.string.allow_copy)) }
            TextButton(onClick = model::clearClipboard, enabled = session?.busy != true) { Text(stringResource(R.string.clear_clipboard)) }
        }
    }
    if (confirm) AlertDialog(onDismissRequest = { confirm = false }, title = { Text(stringResource(R.string.confirm_copy)) },
        text = { Column(Modifier.heightIn(max = 320.dp).verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(state.path)
            Text(stringResource(R.string.copy_safety))
            clipboard.forEach { Text(it.name, style = MaterialTheme.typography.bodySmall) }
        } },
        confirmButton = { TextButton(onClick = { confirm = false; model.paste(state.path) }, modifier = Modifier.testTag("copy-confirm")) { Text(stringResource(R.string.copy_files)) } },
        dismissButton = { TextButton(onClick = { confirm = false }) { Text(stringResource(R.string.cancel)) } })
}

@Composable
fun CopyReport(model: BrowserModel) {
    val operation by model.copy.collectAsStateWithLifecycle()
    val session = operation ?: return
    AlertDialog(onDismissRequest = {}, properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
        title = { Text(stringResource(if (session.busy) R.string.copy_running else R.string.copy_report)) },
        text = { Column(Modifier.heightIn(max = 320.dp).verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(session.destination)
            if (session.busy) {
                LinearProgressIndicator(Modifier.fillMaxWidth())
                session.progress?.let { Text(stringResource(R.string.copy_progress, it.index, it.total, it.name, it.bytes)) }
                Text(stringResource(R.string.copy_cancel_scope), style = MaterialTheme.typography.bodySmall)
            }
            if (session.interrupted || session.journalError) Text(stringResource(R.string.copy_recovery), color = MaterialTheme.colorScheme.error)
            if (!session.busy) Text(stringResource(R.string.copy_report_scope), style = MaterialTheme.typography.bodySmall)
            session.names.forEach { Text(it, style = MaterialTheme.typography.bodySmall) }
            if (session.results.any { it.created && it.status != CopyStatus.COPIED }) Text(stringResource(R.string.copy_partial_warning), color = MaterialTheme.colorScheme.error)
            session.results.forEach { entry ->
                Text(entry.name + " — " + stringResource(when (entry.status) {
                    CopyStatus.COPIED -> R.string.copy_ok
                    CopyStatus.EXISTS -> R.string.copy_exists
                    CopyStatus.CANCELLED -> R.string.copy_cancelled
                    CopyStatus.NOT_ATTEMPTED -> R.string.copy_not_attempted
                    CopyStatus.SOURCE_CHANGED -> R.string.copy_source_changed
                    CopyStatus.INVALID -> R.string.folder_invalid_name
                    CopyStatus.FAILED -> R.string.copy_failed
                }) + (if (entry.status == CopyStatus.COPIED) " (${entry.bytes} B)" else " (≥ ${entry.bytes} B)"), style = MaterialTheme.typography.bodySmall)
            }
        } }, confirmButton = {
            if (session.busy) TextButton(onClick = model::cancelCopy, modifier = Modifier.testTag("copy-cancel")) { Text(stringResource(R.string.cancel)) }
            else TextButton(onClick = model::dismissCopy, modifier = Modifier.testTag("copy-report-close")) { Text(stringResource(R.string.copy_acknowledge)) }
        })
}
