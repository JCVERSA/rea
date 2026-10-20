package app.cantas.jcversa.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.DialogProperties
import app.cantas.jcversa.data.FavoritePolicy
import app.cantas.jcversa.data.LocalFolders
import app.cantas.jcversa.data.FolderCreation
import app.cantas.jcversa.data.FolderError
import app.cantas.jcversa.data.key
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CantasApp(model: BrowserModel, permitted: Boolean, requestStorage: () -> Unit, pickTree: () -> Unit,
              openFile: (String) -> Unit, mode: String, changeMode: (String) -> Unit,
              canCreateLocal: Boolean = false, requestFolderWrite: () -> Unit = {}, pickWritableTree: () -> Unit = {}) {
    var screen by rememberSaveable { mutableIntStateOf(0) }
    val state by model.state.collectAsStateWithLifecycle()
    val preferences by model.preferences.collectAsStateWithLifecycle()
    var favoritesOpen by remember { mutableStateOf(false) }
    val operation by model.creation.collectAsStateWithLifecycle()
    var createParent by remember(state.path) { mutableStateOf<String?>(null) }
    var folderName by remember(createParent) { mutableStateOf("") }
    val context = LocalContext.current
    val createdMessage = stringResource(R.string.folder_created, operation?.name ?: "")
    LaunchedEffect(operation?.result) {
        if (operation?.result is FolderCreation.Created) {
            createParent = null
            model.dismissCreation()
            android.widget.Toast.makeText(context, createdMessage, android.widget.Toast.LENGTH_LONG).show()
        }
    }
    val listState = rememberLazyListState()
    val focus = LocalFocusManager.current
    val localRoot = model.boundary.root.path
    val root = state.root
    val roots by model.roots.collectAsStateWithLifecycle()
    val connectionError by model.connectionError.collectAsStateWithLifecycle()
    val connect = { screen = 1; pickTree() }
    val tabs = listOf(R.string.home, R.string.files, R.string.settings)
    val icons = listOf(Icons.Default.Home, Icons.AutoMirrored.Filled.List, Icons.Default.Settings)
    BackHandler(enabled = screen != 0) {
        if (screen == 1 && state.selecting) model.clearSelection()
        else if(screen == 1 && state.path != root) model.up() else screen = 0
    }
    Scaffold(containerColor = MaterialTheme.colorScheme.background,
        topBar = { TopAppBar(title = { Column {
            Text(if(screen == 0) "Cantas" else stringResource(tabs[screen]), fontWeight = FontWeight.Bold)
            if(screen == 0) Text(stringResource(R.string.your_space), style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
        } }, colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
            actions = {
                IconButton(onClick = { favoritesOpen = true }, modifier = Modifier.testTag("favorites-open")) {
                    Text("★", modifier = Modifier.semantics { contentDescription = context.getString(R.string.favorites) })
                }
                if(screen == 1 && (permitted || state.external)) IconButton(onClick = { model.refresh() }) {
                Icon(Icons.Default.Refresh,stringResource(R.string.refresh))
            } }) },
        bottomBar = { NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
            tabs.forEachIndexed { index, title -> NavigationBarItem(
                selected = screen == index, onClick = { if (screen != index) model.clearSelection(); screen = index },
                modifier = Modifier.testTag("nav-$index"),
                icon = { Icon(icons[index],null) }, label = { Text(stringResource(title)) }) }
        } }
    ) { padding ->
        when(screen) {
            0 -> Home(Modifier.padding(padding), permitted, requestStorage, roots, connectionError, connect,
                selectTree = { screen = 1; model.selectTree(it) }, forgetTree = model::forgetTree,
                connectWritable = { screen = 1; pickWritableTree() }) { folder ->
                screen = 1; model.navigate(File(localRoot,folder).path)
            }
            1 -> if(!permitted && !state.external) Column(Modifier.padding(padding).padding(24.dp)) {
                    PermissionPanel(Modifier, requestStorage)
                    OutlinedButton(onClick = connect) { Text(stringResource(R.string.connect_tree)) }
                }
                else {
                    LaunchedEffect(state.path, state.options.query, state.options.sort, state.options.showHidden) { listState.scrollToItem(0) }
                    Column(Modifier.padding(padding).fillMaxSize()) {
                        Row(Modifier.fillMaxWidth().padding(horizontal = 12.dp),verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = model::up, enabled = state.path != root) {
                                Icon(Icons.Default.KeyboardArrowUp,stringResource(R.string.parent_folder))
                            }
                            Text(if(state.external) state.title ?: stringResource(R.string.connected_folder) else if(state.path == root) stringResource(R.string.main_storage) else state.path.removePrefix(root + "/"),
                                Modifier.weight(1f), style = MaterialTheme.typography.labelLarge,
                                maxLines = 2, overflow = TextOverflow.Ellipsis)
                            val saved = preferences.data.favorites.any { model.isCurrentFavorite(it) }
                            val fallback = stringResource(R.string.folder)
                            val defaultTitle = if (state.external) state.title ?: fallback else if (state.path == root) stringResource(R.string.main_storage) else File(state.path).name
                            IconButton(onClick = { model.toggleFavorite(FavoritePolicy.defaultTitle(defaultTitle, fallback)) },
                                enabled = preferences.ready && !preferences.busy && !preferences.error && !state.loading && !state.error,
                                modifier = Modifier.testTag("favorite-toggle").semantics {
                                    contentDescription = context.getString(if (saved) R.string.favorite_unpin else R.string.favorite_pin)
                                }) { Text(if (saved) "★" else "☆") }
                        }
                        if (preferences.error) Text(stringResource(R.string.preferences_error), color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(horizontal = 16.dp).testTag("preferences-warning"))
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
                        if (state.selecting) {
                            Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                                Text(stringResource(R.string.selected_count, state.selected.size), Modifier.weight(1f).testTag("selection-count"))
                                TextButton(onClick = model::stageCopy,
                                    enabled = state.selected.isNotEmpty() && state.selected.size <= 100,
                                    modifier = Modifier.testTag("copy-selection")) { Text(stringResource(R.string.copy_files)) }
                                TextButton(onClick = model::clearSelection, modifier = Modifier.testTag("selection-clear")) { Text(stringResource(R.string.close_selection)) }
                            }
                            TextButton(onClick = model::selectDisplayed, enabled = !state.loading && !state.error,
                                modifier = Modifier.padding(horizontal = 16.dp).testTag("select-displayed")) {
                                Text(stringResource(R.string.select_displayed))
                            }
                            Text(stringResource(R.string.copy_selection_scope),
                                Modifier.padding(horizontal = 16.dp), style = MaterialTheme.typography.labelSmall)
                        } else Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                            TextButton(onClick = model::startSelection, enabled = !state.loading && !state.error && state.entries.isNotEmpty(),
                                modifier = Modifier.testTag("selection-start")) { Text(stringResource(R.string.select_items)) }
                            if (!state.external || (roots.any { it.writable } && model.canCreateSaf())) TextButton(onClick = {
                                if (state.external || canCreateLocal) { model.dismissCreation(); createParent = state.path }
                                else requestFolderWrite()
                            }, enabled = !state.loading && !state.error, modifier = Modifier.testTag("new-folder")) {
                                Text(stringResource(if (state.external || canCreateLocal) R.string.new_folder else R.string.allow_creation))
                            }
                        }
                        if (!state.selecting) CopyDestinationControls(model, state, canCreateLocal, requestFolderWrite)
                        if(state.loading) LinearProgressIndicator(Modifier.fillMaxWidth())
                        if(state.error) Column {
                            Notice(stringResource(if(state.external) R.string.tree_unavailable else R.string.folder_error))
                            if(state.external) TextButton(onClick = connect) { Text(stringResource(R.string.connect_tree)) }
                        }
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
                                Surface(shape = RoundedCornerShape(18.dp), color = if (entry.key() in state.selected) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surface) {
                                    Row(Modifier.fillMaxWidth().combinedClickable(enabled = !state.loading, onLongClickLabel = stringResource(R.string.select_items),
                                        onLongClick = { model.toggle(entry) }, onClick = {
                                        // A queued click from an old composition must not open a stale row.
                                        val latest = model.state.value
                                        if (!latest.loading && entry in latest.entries) {
                                            if (latest.selecting) model.toggle(entry)
                                            else if(entry.directory) model.navigate(entry.path) else openFile(entry.path)
                                        }
                                    }).semantics { selected = entry.key() in state.selected }.padding(14.dp).testTag("entry-${entry.name}"),verticalAlignment = Alignment.CenterVertically) {
                                        if (state.selecting) Checkbox(checked = entry.key() in state.selected,
                                            onCheckedChange = { model.toggle(entry) },
                                            modifier = Modifier.semantics { contentDescription = entry.name })
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
    if (favoritesOpen) FavoritesDialog(model, close = { favoritesOpen = false }, open = {
        favoritesOpen = false
        screen = 1
        model.openFavorite(it)
    })
    CopyReport(model)
    if (createParent != null) {
        val busy = operation?.busy == true
        val valid = LocalFolders.validName(folderName)
        val failure = (operation?.result as? FolderCreation.Failed)?.error
        AlertDialog(onDismissRequest = { if (!busy) { createParent = null; model.dismissCreation() } },
            properties = DialogProperties(dismissOnBackPress = !busy, dismissOnClickOutside = !busy),
            title = { Text(stringResource(R.string.new_folder)) },
            text = { Column(Modifier.heightIn(max = 320.dp).verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                val dialogFocus = LocalFocusManager.current
                Text(createParent!!, maxLines = 3, overflow = TextOverflow.Ellipsis)
                Text(stringResource(if (state.external) R.string.saf_creation_scope else R.string.creation_scope), style = MaterialTheme.typography.bodySmall)
                OutlinedTextField(value = folderName, onValueChange = { folderName = it.take(256); model.dismissCreation() },
                    singleLine = true, enabled = !busy, label = { Text(stringResource(R.string.folder_name)) },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { dialogFocus.clearFocus() }),
                    isError = (folderName.isNotEmpty() && !valid) || failure != null,
                    modifier = Modifier.testTag("folder-name"))
                if (busy) LinearProgressIndicator(Modifier.fillMaxWidth())
                if (failure != null || (folderName.isNotEmpty() && !valid)) Text(stringResource(when (failure ?: FolderError.INVALID_NAME) {
                    FolderError.INVALID_NAME -> R.string.folder_invalid_name
                    FolderError.EXISTS -> R.string.folder_exists
                    FolderError.ACCESS_DENIED -> R.string.folder_access_denied
                    FolderError.UNAVAILABLE -> R.string.folder_unavailable
                    FolderError.UNSUPPORTED -> R.string.folder_unsupported
                    FolderError.UNCERTAIN -> R.string.folder_creation_uncertain
                    FolderError.FAILED -> R.string.folder_creation_failed
                }), color = MaterialTheme.colorScheme.error, modifier = Modifier.testTag("folder-create-error"))
            } },
            confirmButton = { TextButton(onClick = { model.createFolder(createParent!!, folderName) },
                enabled = valid && !busy && operation?.result !is FolderCreation.Created && failure != FolderError.UNCERTAIN,
                modifier = Modifier.testTag("folder-create-confirm")) { Text(stringResource(R.string.create_folder)) } },
            dismissButton = { TextButton(onClick = { createParent = null; model.dismissCreation() }, enabled = !busy) { Text(stringResource(R.string.cancel)) } })
    }
}

@Composable
private fun Home(modifier: Modifier, permitted: Boolean, request: () -> Unit,
                 roots: List<app.cantas.jcversa.GrantedTree>, connectionError: Boolean, connect: () -> Unit,
                 selectTree: (String) -> Unit, forgetTree: (String) -> Unit, connectWritable: () -> Unit, browse: (String) -> Unit) {
    val shortcuts = listOf("Download" to R.string.downloads,"Documents" to R.string.documents,
        "Pictures" to R.string.pictures,"DCIM" to R.string.camera,"Movies" to R.string.videos,"Music" to R.string.music)
    LazyColumn(modifier.fillMaxSize().testTag("home-list"),contentPadding = PaddingValues(20.dp),verticalArrangement = Arrangement.spacedBy(20.dp)) {
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
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(stringResource(R.string.connected_folders), style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text(stringResource(R.string.tree_scope), style = MaterialTheme.typography.bodySmall)
                if (connectionError) Text(stringResource(R.string.connection_error), color = MaterialTheme.colorScheme.error)
                OutlinedButton(onClick = connect, modifier = Modifier.testTag("connect-tree")) { Text(stringResource(R.string.connect_tree)) }
                OutlinedButton(onClick = connectWritable, modifier = Modifier.testTag("connect-writable-tree")) { Text(stringResource(R.string.connect_writable_tree)) }
            }
        }
        items(roots, key = { it.uri }) { tree ->
            var confirm by remember { mutableStateOf(false) }
            Surface(shape = RoundedCornerShape(20.dp), color = MaterialTheme.colorScheme.surfaceVariant) {
                Column(Modifier.fillMaxWidth().padding(12.dp)) {
                    TextButton(onClick = { selectTree(tree.uri) }) { Text(tree.name, maxLines = 2, overflow = TextOverflow.Ellipsis) }
                    Text(stringResource(if (tree.writable) R.string.tree_read_write else R.string.tree_read_only), style = MaterialTheme.typography.labelSmall)
                    TextButton(onClick = { confirm = true }) { Text(stringResource(R.string.disconnect_tree)) }
                }
            }
            if (confirm) AlertDialog(onDismissRequest = { confirm = false },
                title = { Text(stringResource(R.string.disconnect_tree)) },
                text = { Text(tree.name + "\n\n" + stringResource(R.string.disconnect_detail)) },
                confirmButton = { TextButton(onClick = { confirm = false; forgetTree(tree.uri) }) { Text(stringResource(R.string.disconnect_tree)) } },
                dismissButton = { TextButton(onClick = { confirm = false }) { Text(stringResource(R.string.cancel)) } })
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
        item { Text(stringResource(R.string.cantas_build_version),style = MaterialTheme.typography.labelMedium) }
    }
}
