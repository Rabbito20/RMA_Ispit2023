package rs.raf.projekat1.rmanutritiont.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import rs.raf.projekat1.rmanutritiont.R
import rs.raf.projekat1.rmanutritiont.data.FoodCategory
import rs.raf.projekat1.rmanutritiont.ui.components.RegularWidthButton
import rs.raf.projekat1.rmanutritiont.ui.components.SearchBar

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onFilterClick: () -> Unit,
    onCategoryClicked: (String) -> Unit
) {
    //  todo:   uiState from viewModel
//    val uiState by viewModel.ui

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())      // Not needed but in an edge case
            .padding(start = 20.dp, top = 24.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        var searchQueryState by remember { mutableStateOf("") }
        RegularWidthButton(
            onClick = {
                onFilterClick()
            },
            buttonText = stringResource(id = R.string.filter_text),
            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
        )
        SearchBar(
            onNewQuery = { searchQueryState = it },
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )

        //  TODO: Add list of categories
        val testList = listOf(
            FoodCategory(categoryName = "Breakfast"),
            FoodCategory(categoryName = "Lunch"),
            FoodCategory(categoryName = "Dinner"),
        )
        CategoryContainer(
            listOfCategories = testList,
            onCategoryClick = {
                onCategoryClicked(it)
            })

    }
}

@Composable
fun CategoryContainer(
    modifier: Modifier = Modifier,
    listOfCategories: List<FoodCategory> = emptyList(),
    onCategoryClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .then(modifier)
    ) {
        Text(
            text = stringResource(id = R.string.category),
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 12.dp)
        )

        listOfCategories.forEach { category ->
            CategoryCard(
                image = category.categoryImage,
                category = category,
                onButtonClick = { onCategoryClick(it) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCard(image: String, category: FoodCategory, onButtonClick: (String) -> Unit) {
    var isExpendedState by remember { mutableStateOf(false) }

    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        onClick = { onButtonClick(category.categoryName) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.tertiary),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            //  Placeholder za sada
            Image(
                imageVector = Icons.Filled.Star,
                contentDescription = "",
                modifier = Modifier
                    .weight(1f)
                    .size(64.dp)
                    .padding(start = 8.dp, end = 8.dp)
            )

            Text(
                text = category.categoryName,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                modifier = Modifier
                    .wrapContentSize()
                    .weight(2f)
            )

            IconButton(
                onClick = { isExpendedState = !isExpendedState },
                modifier = Modifier
                    .size(48.dp)
                    .weight(1f)
                    .padding(end = 4.dp)
            ) {
                Icon(
                    imageVector = if (!isExpendedState) Icons.Default.KeyboardArrowDown else
                        Icons.Default.KeyboardArrowUp,
                    contentDescription = "Expandable icon",
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
        if (isExpendedState) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = category.categoryDescription,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
//    HomeScreen(viewModel = HomeViewModel(), onCategoryClicked = {}, onFilterClick = {})
}
