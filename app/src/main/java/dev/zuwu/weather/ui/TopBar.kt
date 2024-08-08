package dev.zuwu.weather.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.zIndex
import dev.zuwu.weather.model.SearchResponseItem
import dev.zuwu.weather.ui.components.SearchUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    refresh: () -> Unit,
    padding: MutableState<PaddingValues>,
    navToSettings: () -> Unit,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onActiveChange: () -> Unit,
    items: State<List<SearchResponseItem>>,
    isSearching: State<Boolean>,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val searchActive = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            if (searchActive.value) {
                SearchUI(
                    onQueryChange = onQueryChange,
                    onSearch = onSearch,
                    onActiveChange = onActiveChange,
                    active = searchActive,
                    searchItems = items,
                    isSearching = isSearching,
                )
            }
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Hava Durumu",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    IconButton(onClick = { searchActive.value = !searchActive.value }) {
                        Icon(Icons.Filled.Search, contentDescription = "")
                    }
                    IconButton(onClick = { refresh() }) {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = ""
                        )
                    }
                    IconButton(onClick = { navToSettings() }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = ""
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
                modifier = Modifier.alpha(
                    if (searchActive.value) 0.0f else 1.0f
                ).zIndex(
                    if (searchActive.value) -1f else 1f
                )
            )
        },
        content = {
            paddingValues ->
            padding.value = paddingValues
        }
    )
}