package rs.raf.projekat1.rmanutritiont.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import rs.raf.projekat1.rmanutritiont.R
import rs.raf.projekat1.rmanutritiont.ui.components.SearchBar

@Composable
fun FiltersScreen(
    navController: NavController,
    //  TODO:   FilterState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(id = R.string.filter_title),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleMedium
        )

        //  Searchbox
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchBar(
                onNewQuery = {},
                modifier = Modifier
                    .weight(4f)
                    .padding(end = 4.dp)
            )

            IconButton(
                onClick = { /*TODO: Open dialog*/ },
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(MaterialTheme.colorScheme.tertiary)
                    .size(48.dp)
                    .weight(1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_sort_24),
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = "Sort button",
                    modifier = Modifier.size(24.dp)
                )
            }

        }

        //  Row sa 3 dugmeta kao toggle

        //  Container sa ostalim jelima
    }
}

@Composable
private fun ToggleContainer(rowModifier: Modifier = Modifier) {
    Row(modifier = Modifier.then(rowModifier)) {

    }
}

@Preview(showBackground = true)
@Composable
fun FilterPreview() {
    FiltersScreen(navController = rememberNavController())
}
