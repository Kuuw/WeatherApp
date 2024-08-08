package dev.zuwu.weather.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import dev.zuwu.weather.R
import dev.zuwu.weather.model.SearchResponseItem
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchUI(
    onQueryChange:(String)->Unit,
    onSearch:(String)->Unit,
    onActiveChange:()->Unit,
    active: MutableState<Boolean>,
    isSearching: State<Boolean>,
    searchItems: State<List<SearchResponseItem>>,
    history: MutableList<String> = remember { mutableListOf() }
) {
    var query by remember { mutableStateOf("") }
    val modifier = Modifier.fillMaxWidth()

    Scaffold {
        SearchBar(
            modifier = if(active.value) { modifier.zIndex(10f)} else { modifier },
            query = query,
            onQueryChange = {
                runBlocking {
                    query = it
                    onQueryChange(it)
                } },
            onSearch = {
                runBlocking {
                    history.add(it)
                    if( searchItems.value.isNotEmpty()) {
                    onSearch(searchItems.value[0].url)
                } } },
            active = active.value,
            onActiveChange = {
                active.value = it
                runBlocking { onActiveChange() }
            },
            placeholder = { Text(text = "Ara") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "")
             },
            trailingIcon = { if(active.value){ Icon(Icons.Default.Clear, contentDescription = "", modifier = Modifier.clickable{
                if(query.isBlank()){
                    active.value = false
                }
                query = ""
            })} }
        ) {
            if (isSearching.value) {
                CircularProgressIndicator(modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally))
            } else {
                LazyColumn {
                    items(history) { item ->
                        Row(modifier = Modifier.padding(14.dp)) {
                            Icon(painterResource(id = R.drawable.history_24px), contentDescription = "")
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(text = item, modifier = Modifier.clickable {
                                query = item
                            })
                        }
                    }
                    items(searchItems.value) { item ->
                        Row(modifier = Modifier.padding(14.dp)) {
                            Text(text = "${item.name}, ${item.country}", modifier = Modifier.clickable {
                                onSearch(item.url)
                                active.value = false
                                query = ""
                            })
                        }
                    }
                }
            }
        }
    }
}