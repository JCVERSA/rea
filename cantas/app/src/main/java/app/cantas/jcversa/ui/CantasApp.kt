package app.cantas.jcversa.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.cantas.jcversa.BrowserModel
import app.cantas.jcversa.BrowserState
import app.cantas.jcversa.data.FileSort
import app.cantas.jcversa.R
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CantasApp(model: BrowserModel, permitted: Boolean, requestStorage: () -> Unit,
              openFile: (String) -> Unit, mode: String, changeMode: (String) -> Unit) {
    var screen by rememberSaveable { mutableIntStateOf(0) }
    val state by model.state.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    val focus = LocalFocusManager.current
    val root = model.boundary.root.path
    val tabs = listOf(R.string.home, R.string.files, R.string.settings)
    val icons = listOf(Icons.Default.Home, Icons.AutoMirrored.Filled.List, Icons.Default.Settings)
    BackHandler(enabled = screen != 0) {
        if(screen == 1 && state.path != root) model.up() else screen = 0
    }
    Scaffold(containerColor = MaterialTheme.colorScheme.background,
        topBar = { TopAppBar(title = { Column {
            Text(if(screen == 0) "Cantas" else stringResource(tabs[screen]), fontWeight = FontWeight.Bold)
            if(screen == 0) Text(stringResource(R.string.your_space), style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
        } }, colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
            actions = { if(screen == 1 && permitted) IconButton(onClick = { model.refresh() }) {
                Icon(Icons.Default.Refresh,stringResource(R.string.refresh))
            } }) },
        bottomBar = { NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
            tabs.forEachIndexed { index, title -> NavigationBarItem(
                selected = screen == index, onClick = { screen = index },
                modifier = Modifier.testTag("nav-$index"),
                icon = { Icon(icons[index],null) }, label = { Text(stringResource(title)) }) }
        } }
    ) { padding ->
        when(screen) {
            0 -> Home(Modifier.padding(padding), permitted, requestStorage) { folder ->
                screen = 1; model.navigate(File(root,folder).path)
            }
            1 -> if(!permitted) PermissionPanel(Modifier.padding(padding).padding(24.dp),requestStorage)
                else {
                    LaunchedEffect(state.path, state.options.query, state.options.sort, state.options.showHidden) { listState.scrollToItem(0) }
                    Column(Modifier.padding(padding).fillMaxSize()) {
                        Row(Modifier.fillMaxWidth().padding(horizontal = 12.dp),verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = model::up, enabled = state.path != root) {
                                Icon(Icons.Default.KeyboardArrowUp,stringResource(R.string.parent_folder))
                            }
                            Text(if(state.path == root) stringResource(R.string.main_storage) else state.path.removePrefix(root + "/"),
                                Modifier.weight(1f), style = MaterialTheme.typography.labelLarge,
                                maxLines = 2, overflow = TextOverflow.Ellipsis)
                        }
                        OutlinedTextField(value = state.options.query, onValueChange = model::query,
                            label = { Text(stringResource(R.string.search_folder)) }, singleLine = true,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                            keyboardActions = KeyboardActions(onSearch = { focus.clearFocus() }),
                            trailingIcon = { if(state.options.query.isNotEmpty()) IconButton(onClick = { model.query(""); focus.clearFocus() }) {
                                Icon(Icons.Default.Close, stringResource(R.string.clear_search))
                            } },
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).testTag("folder-search"))
                        Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                            var menu by remember { mutableStateOf(false) }
                            Box {
                                TextButton(onClick = { menu = true }, modifier = Modifier.testTag("sort-menu")) {
                                    Text(stringResource(when(state.options.sort) { FileSort.NAME -> R.string.sort_name; FileSort.NAME_DESC -> R.string.sort_name_desc; FileSort.SIZE_DESC -> R.string.sort_size }))
                                }
                                DropdownMenu(expanded = menu, onDismissRequest = { menu = false }) {
                                    listOf(FileSort.NAME to R.string.sort_name, FileSort.NAME_DESC to R.string.sort_name_desc,
                                        FileSort.SIZE_DESC to R.string.sort_size).forEach { (order, label) ->
                                        DropdownMenuItem(text = { Text(stringResource(label)) }, modifier = Modifier.testTag("sort-${order.name}"), onClick = { model.sort(order); menu = false })
                                    }
                                }
                            }
                            Spacer(Modifier.weight(1f))
                            val hiddenLabel = stringResource(R.string.hidden_files)
                            Text(hiddenLabel, style = MaterialTheme.typography.labelMedium)
                            Switch(checked = state.options.showHidden, onCheckedChange = model::hidden,
                                modifier = Modifier.testTag("show-hidden").semantics { contentDescription = hiddenLabel })
                        }
                        if(state.loading) LinearProgressIndicator(Modifier.fillMaxWidth())
                        if(state.error) Notice(stringResource(R.string.folder_error))
                        else if(!state.loading && state.entries.isEmpty()) Notice(stringResource(R.string.empty_folder))
                        LazyColumn(state = listState, modifier = Modifier.fillMaxSize().testTag("file-list"),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            if(state.truncated) item {
                                Column {
                                    Notice(stringResource(R.string.folder_limit))
                                    if(state.options.limit < 100000) TextButton(onClick = model::more, enabled = !state.loading) {
                                        Text(stringResource(R.string.load_more))
                                    }
                                }
                            }
                            items(state.entries,key = { it.path + "/" + it.name },contentType = { it.directory }) { entry ->
                                Surface(shape = RoundedCornerShape(18.dp), color = MaterialTheme.colorScheme.surface) {
                                    Row(Modifier.fillMaxWidth().clickable {
                                        if(entry.directory) model.navigate(entry.path) else openFile(entry.path)
                                    }.padding(14.dp).testTag("entry-${entry.name}"),verticalAlignment = Alignment.CenterVertically) {
                                        Surface(color = if(entry.directory) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
                                            shape = RoundedCornerShape(14.dp)) {
                                            Box(Modifier.size(44.dp),contentAlignment = Alignment.Center) {
                                                Text(if(entry.directory) "▰" else entry.name.substringAfterLast('.',"•").take(3).uppercase(),
                                                    style = MaterialTheme.typography.labelMedium,
                                                    color = if(entry.directory) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant)
                                            }
                                        }
                                        Column(Modifier.weight(1f).padding(horizontal = 14.dp)) {
                                            Text(entry.name,maxLines = 1,overflow = TextOverflow.Ellipsis,style = MaterialTheme.typography.bodyLarge)
                                            Text(if(entry.directory) stringResource(R.string.folder) else android.text.format.Formatter.formatShortFileSize(
                                                androidx.compose.ui.platform.LocalContext.current,entry.bytes),
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,style = MaterialTheme.typography.bodySmall)
                                        }
                                        if(entry.directory) Icon(Icons.AutoMirrored.Filled.ArrowForward,null,Modifier.size(18.dp),tint = MaterialTheme.colorScheme.onSurfaceVariant)
                                    }
                                }
                            }
                        }
                    }
                }
            2 -> SettingsPanel(Modifier.padding(padding), mode, changeMode)
        }
    }
}

@Composable
private fun Home(modifier: Modifier, permitted: Boolean, request: () -> Unit, browse: (String) -> Unit) {
    val shortcuts = listOf("Download" to R.string.downloads,"Documents" to R.string.documents,
        "Pictures" to R.string.pictures,"DCIM" to R.string.camera,"Movies" to R.string.videos,"Music" to R.string.music)
    LazyColumn(modifier.fillMaxSize(),contentPadding = PaddingValues(20.dp),verticalArrangement = Arrangement.spacedBy(20.dp)) {
        item {
            Surface(shape = RoundedCornerShape(30.dp),color = MaterialTheme.colorScheme.primaryContainer) {
                Column(Modifier.fillMaxWidth().padding(24.dp),verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(stringResource(R.string.local_first),style = MaterialTheme.typography.labelLarge)
                    Text(stringResource(R.string.hero),style = MaterialTheme.typography.headlineLarge,fontWeight = FontWeight.Bold)
                    Text(stringResource(R.string.hero_detail),style = MaterialTheme.typography.bodyMedium)
                    Button(onClick = { if(permitted) browse("") else request() },Modifier.testTag("browse-storage")) {
                        Text(stringResource(if(permitted) R.string.explore_storage else R.string.allow_storage))
                        Spacer(Modifier.width(8.dp));Icon(Icons.AutoMirrored.Filled.ArrowForward,null,Modifier.size(18.dp))
                    }
                }
            }
        }
        item { Text(stringResource(R.string.quick_access),style = MaterialTheme.typography.titleLarge,fontWeight = FontWeight.Bold) }
        items(shortcuts.chunked(2)) { pair ->
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                pair.forEach { (path,title) ->
                    Card(onClick = { if(permitted) browse(path) else request() },modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),shape = RoundedCornerShape(24.dp)) {
                        Column(Modifier.fillMaxWidth().padding(18.dp),verticalArrangement = Arrangement.spacedBy(14.dp)) {
                            Text("▰",color = MaterialTheme.colorScheme.primary,style = MaterialTheme.typography.headlineSmall)
                            Text(stringResource(title),style = MaterialTheme.typography.titleSmall,maxLines = 1,overflow = TextOverflow.Ellipsis)
                        }
                    }
                }
            }
        }
        item { Text(stringResource(R.string.alpha_scope),style = MaterialTheme.typography.bodySmall,color = MaterialTheme.colorScheme.onSurfaceVariant) }
    }
}

@Composable
private fun PermissionPanel(modifier: Modifier, request: () -> Unit) {
    Column(modifier,verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(stringResource(R.string.permission_title),style = MaterialTheme.typography.headlineSmall)
        Text(stringResource(R.string.permission_detail))
        Button(onClick = request) { Text(stringResource(R.string.allow_storage)) }
    }
}
@Composable
private fun Notice(message: String) { Text(message,Modifier.padding(20.dp),color = MaterialTheme.colorScheme.onSurfaceVariant) }

@Composable
private fun SettingsPanel(modifier: Modifier, mode: String, changeMode: (String) -> Unit) {
    LazyColumn(modifier.fillMaxSize(),contentPadding = PaddingValues(20.dp),verticalArrangement = Arrangement.spacedBy(20.dp)) {
        item { Text(stringResource(R.string.appearance),style = MaterialTheme.typography.titleLarge,fontWeight = FontWeight.Bold) }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("system" to R.string.system_theme,"light" to R.string.light_theme,"dark" to R.string.dark_theme).forEach { (value,label) ->
                    FilterChip(selected = mode == value,onClick = { changeMode(value) },
                        label = { Text(stringResource(label)) },modifier = Modifier.testTag("theme-$value"))
                }
            }
        }
        item { Surface(shape = RoundedCornerShape(24.dp),color = MaterialTheme.colorScheme.surfaceVariant) {
            Column(Modifier.padding(20.dp),verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(stringResource(R.string.private_title),style = MaterialTheme.typography.titleMedium)
                Text(stringResource(R.string.private_detail),style = MaterialTheme.typography.bodyMedium)
            }
        } }
        item { Text(stringResource(R.string.alpha_title),style = MaterialTheme.typography.titleLarge,fontWeight = FontWeight.Bold) }
        item { Text(stringResource(R.string.alpha_scope)) }
        item { Text(stringResource(R.string.future_scope),color = MaterialTheme.colorScheme.onSurfaceVariant) }
        item { Text("Cantas 0.2.0 · app.cantas.jcversa",style = MaterialTheme.typography.labelMedium) }
    }
}
