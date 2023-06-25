package rs.raf.projekat1.rmanutritiont.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rs.raf.projekat1.rmanutritiont.R
import rs.raf.projekat1.rmanutritiont.ui.components.ScreenButton

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())      // Not needed but in an edge case
            .padding(start = 20.dp, top = 24.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Home Screen",
            modifier = Modifier
                .wrapContentSize()
                .padding(bottom = 32.dp)
        )

        ScreenButton(
            onClick = { /*TODO*/ },
            buttonText = stringResource(id = R.string.main_screen)
        )

        ScreenButton(
            onClick = { /*TODO*/ },
            buttonText = stringResource(id = R.string.favorites_title)
        )

        ScreenButton(
            onClick = { /*TODO*/ },
            buttonText = stringResource(id = R.string.stats_title)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen()
}
