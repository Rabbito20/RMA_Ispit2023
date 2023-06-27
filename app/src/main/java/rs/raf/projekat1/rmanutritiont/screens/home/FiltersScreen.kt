package rs.raf.projekat1.rmanutritiont.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import rs.raf.projekat1.rmanutritiont.R
import rs.raf.projekat1.rmanutritiont.data.Meal
import rs.raf.projekat1.rmanutritiont.ui.components.SearchBox
import rs.raf.projekat1.rmanutritiont.ui.components.SingleMealCard

@Composable
fun FiltersScreen(
    navController: NavController,
    onMealClicked: (Meal) -> Unit,
    //  TODO:   FilterState
) {
    //  TODO:   ViewModel

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 4.dp),
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
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SearchBox(
                onNewQuery = {},
                modifier = Modifier
                    .weight(3f)
                    .padding(end = 4.dp)
            )
            SortButton(onClick = { /*TODO: Open dialog*/ })

        }

        //  TODO: ViewModel ovo da kontrolise
        var selectedFilter by remember { mutableStateOf("") }

        //  Row sa 3 dugmeta kao toggle
        ToggleContainer(
            modifier = Modifier.padding(top = 8.dp),
            selectedFilter = { selectedFilter = it })

        //  TODO: Obraditi listu (sortirati i filtrirati)
        val testMealList = listOf(
            Meal("Burek", null, null, ),
            Meal("Musaka", null, null),
            Meal("Sarma", null, null),
        )

        /**
         * Container sa ostalim jelima
         * Prima sortiranu listu jela
         * [onCardClick] Otvara prozor detaljnog prikaza jela
         * */
        MealContainer(testMealList, onCardClick = { onMealClicked(it) })
    }
}

@Composable
private fun MealContainer(mealList: List<Meal>, onCardClick: (Meal) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,

        ) {
        mealList.forEach { meal ->
            SingleMealCard(
                modifier = Modifier.padding(top = 8.dp),
                meal = meal,
                onClick = { onCardClick(meal) }
            )
        }
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
        ToggleFilterButton(
            onClick = { selectedFilter(it) },
            text = stringResource(id = R.string.area)
        )
        ToggleFilterButton(
            onClick = { selectedFilter(it) },
            text = stringResource(id = R.string.category)
        )
        ToggleFilterButton(
            onClick = { selectedFilter(it) },
            text = stringResource(id = R.string.ingredients)
        )
    }
}

@Composable
private fun ToggleFilterButton(onClick: (String) -> Unit, text: String) {
    Button(onClick = { onClick(text) }, modifier = Modifier) {
        Text(text = text, modifier = Modifier, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
private fun RowScope.SortButton(onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(MaterialTheme.colorScheme.tertiary)
            .size(48.dp)
            .weight(1f)
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(2.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_sort_24),
                tint = MaterialTheme.colorScheme.onBackground,
                contentDescription = "Sort button",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = stringResource(id = R.string.sort_button),
                modifier = Modifier.padding(bottom = 0.dp),
                fontSize = 10.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilterPreview() {
    FiltersScreen(navController = rememberNavController(), onMealClicked = {})
}
