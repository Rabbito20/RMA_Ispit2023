package rs.raf.projekat1.rmanutritiont.screens.home

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import rs.raf.projekat1.rmanutritiont.R
import rs.raf.projekat1.rmanutritiont.data.model.CategoryFromApi
import rs.raf.projekat1.rmanutritiont.ui.components.RegularWidthButton
import rs.raf.projekat1.rmanutritiont.ui.components.SearchBox

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onFilterClick: () -> Unit,
    onRandomClick: () -> Unit,
    onCategoryClicked: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(start = 20.dp, top = 0.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        val categoryList by remember { mutableStateOf(viewModel.categoryList.value) }
        var searchQuery by remember { mutableStateOf("") }
        var filteredCategories by remember { mutableStateOf(categoryList) }

        filteredCategories = categoryList?.filter {
            it.strCategory!!.contains(searchQuery, ignoreCase = true)
        }

        RegularWidthButton(
            onClick = onFilterClick,
            buttonText = stringResource(id = R.string.filter_text),
            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
        )
        SearchBox(
            onNewQuery = {
                //  TODO: Sredi u ViewModelu
//                viewModel.onSearchInputChanged(it)
                searchQuery = it
            },
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )

        //  Random meal
        RegularWidthButton(
            onClick = onRandomClick,
            buttonText = stringResource(id = R.string.random_meal),
            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
        )

        CategoryContainer(
            listOfCategories = filteredCategories,
            onCategoryClick = {
                onCategoryClicked(it)
            })
    }
}

@Composable
fun CategoryContainer(
    modifier: Modifier = Modifier,
    listOfCategories: List<CategoryFromApi>? = emptyList(),
    onCategoryClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .then(modifier)
    ) {
        Text(
            text = stringResource(id = R.string.categories),
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 12.dp)
        )

        listOfCategories?.forEach { category ->
            CategoryCard(
                thumbnailUrl = category.strCategoryThumb!!,
                category = category,
                onButtonClick = { onCategoryClick(it) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCard(thumbnailUrl: String, category: CategoryFromApi, onButtonClick: (String) -> Unit) {
    var isExpendedState by remember { mutableStateOf(false) }

    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        onClick = { onButtonClick(category.strCategory!!) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.tertiary),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = thumbnailUrl,
                contentDescription = "Meal Thumbnail",
                placeholder = painterResource(id = R.drawable.ic_meal_placeholder_48),
                contentScale = ContentScale.FillBounds,
                filterQuality = FilterQuality.Low,
                modifier = Modifier
                    .clip(RoundedCornerShape(topEnd = 50.dp, bottomEnd = 50.dp))
                    .padding(start = 0.dp, end = 8.dp)
            )

            Text(
                text = category.strCategory!!,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                modifier = Modifier
                    .wrapContentSize()
            )

            IconButton(
                onClick = { isExpendedState = !isExpendedState },
                modifier = Modifier
                    .size(48.dp)
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
                    text = category.strCategoryDescription!!,
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
