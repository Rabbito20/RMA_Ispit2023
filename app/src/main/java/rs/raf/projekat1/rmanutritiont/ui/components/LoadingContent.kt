package rs.raf.projekat1.rmanutritiont.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun LoadingContent(
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
    loading: Boolean,
    loadedContent: @Composable () -> Unit,
    onRefresh: () -> Unit,
//    onSearchInputChanged: (String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (empty) {
            emptyContent()
        } else {
            val state = rememberSwipeRefreshState(isRefreshing = loading)
//        Box(modifier = Modifier) {
            SwipeRefresh(state = state, onRefresh = { onRefresh() }) {
                loadedContent()
            }
        }
    }
}

@Composable
fun FilterLoadingContent(
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
    loading: Boolean,
    content: @Composable () -> Unit,
    onRefresh: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (empty) {
            emptyContent()
        } else {
            val state = rememberSwipeRefreshState(isRefreshing = loading)
            SwipeRefresh(
                state = state,
                onRefresh = { onRefresh() }) {
                content()
            }
        }
    }
}
