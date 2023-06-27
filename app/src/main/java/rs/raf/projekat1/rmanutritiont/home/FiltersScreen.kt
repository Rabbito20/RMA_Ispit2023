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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import rs.raf.projekat1.rmanutritiont.ui.components.SearchBox

@Composable
fun FiltersScreen(
    navController: NavController,
    //  TODO:   FilterState
) {
    //  TODO:   ViewModel

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
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
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchBox(
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

        //  TODO: ViewModel ovo da kontrolise
        var selectedFilter by remember { mutableStateOf("") }
        //  Row sa 3 dugmeta kao toggle
        ToggleContainer(
            modifier = Modifier.padding(top = 12.dp),
            selectedFilter = { selectedFilter = it })

        //  Container sa ostalim jelima, prima [selectedFilter] kao parametar
    }
}

@Composable
private fun ToggleContainer(modifier: Modifier = Modifier, selectedFilter: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TabField(onClick = { selectedFilter(it) }, text = stringResource(id = R.string.area))
        TabField(onClick = { selectedFilter(it) }, text = stringResource(id = R.string.category))
        TabField(onClick = { selectedFilter(it) }, text = stringResource(id = R.string.ingredients))
    }
}

@Composable
private fun TabField(onClick: (String) -> Unit, text: String) {
    Button(onClick = { onClick(text) }, modifier = Modifier) {
        Text(text = text, modifier = Modifier, style = MaterialTheme.typography.bodySmall)
    }
}

@Preview(showBackground = true)
@Composable
fun FilterPreview() {
    FiltersScreen(navController = rememberNavController())
}
