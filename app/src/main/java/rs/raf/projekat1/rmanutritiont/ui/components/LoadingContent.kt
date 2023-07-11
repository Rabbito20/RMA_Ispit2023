package rs.raf.projekat1.rmanutritiont.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import rs.raf.projekat1.rmanutritiont.R

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
fun LoadingContentBarWithText() {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .padding(20.dp)
            .fillMaxHeight()
    ) {
        Text(
            text = stringResource(id = R.string.loading_str),
            modifier = Modifier
                .clip(RoundedCornerShape(100))
                .shimmerEffect()
                .padding(16.dp)
                .align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingContentPrev() {
    LoadingContentBarWithText()
}