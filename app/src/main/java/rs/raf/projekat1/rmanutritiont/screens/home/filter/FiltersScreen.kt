package rs.raf.projekat1.rmanutritiont.screens.home.filter

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import rs.raf.projekat1.rmanutritiont.R
import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi
import rs.raf.projekat1.rmanutritiont.ui.components.LoadingContentBarWithText
import rs.raf.projekat1.rmanutritiont.ui.components.MealContainer
import rs.raf.projekat1.rmanutritiont.ui.components.SearchBox

@Composable
fun FiltersScreen(
    viewModel: FilterViewModel,
    uiState: FilterUiState,
//    onRefreshAction: () -> Unit,
    onMealClicked: (MealFromApi) -> Unit,
) {
    var selectedFilter by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var refreshCode by remember { mutableStateOf(RefreshCodes.CODE_S) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        //  Refresh screen state
        val refreshState = rememberSwipeRefreshState(isRefreshing = uiState.isLoading)

        SwipeRefresh(state = refreshState, onRefresh = { viewModel.onRefresh(refreshCode) }) {
            ScreenContent(
                searchInputChange = {
                    viewModel.onSearchInputChanged(searchString = it)
                },
                searchKeyboardAction = { viewModel.onRefresh(refreshCode) },
                sortButtonClick = { showDialog = !showDialog },
                selectedFilter = { selectedFilter = it },
                onAreaFilterClicked = {
                    refreshCode = RefreshCodes.CODE_A
                    viewModel.fetchMealsByArea()
                },
                onCategoryFilterClicked = {
                    refreshCode = RefreshCodes.CODE_C
                    viewModel.fetchMealsByCategory()
                },
                onIngredientsFilterClicked = {},
                mealContainer = {
                    Text(text = selectedFilter)         //  While Debugging / Building
                    if (refreshState.isRefreshing)
                        LoadingContentBarWithText()
                    else
                        MealContainer(
                            viewModel.mealList.value.orEmpty(),
                            onCardClick = { onMealClicked(it) }
                        )
                }
            )
        }
    }

    if (showDialog)
        SortDialog(
            closeDialog = { showDialog = false },
            onSortByNameAlphabetClick = {},
            onSortByNameClick = {},
            onSortByTagsClick = {}
        )
}

@Composable
private fun ScreenContent(
    sortButtonClick: () -> Unit,
    selectedFilter: (String) -> Unit,
    onAreaFilterClicked: () -> Unit,
    onCategoryFilterClicked: () -> Unit,
    onIngredientsFilterClicked: () -> Unit,
    mealContainer: @Composable () -> Unit,
    searchInputChange: (String) -> Unit,
    searchKeyboardAction: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
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
                onNewQuery = searchInputChange,
                keyboardActions = searchKeyboardAction,
                modifier = Modifier
                    .weight(3f)
                    .padding(end = 4.dp)
            )

            SortButton(onClick = sortButtonClick)
        }

        //  Row sa 3 dugmeta kao toggle
        ToggleContainer(
            modifier = Modifier.padding(top = 8.dp),
            onAreaFilterClick = onAreaFilterClicked,
            onCategoryFilterClick = onCategoryFilterClicked,
            onIngredientsFilterClick = onIngredientsFilterClicked,
            selectedFilter = selectedFilter
        )
        mealContainer()
    }
}

@Composable
private fun ToggleContainer(
    modifier: Modifier = Modifier,
    selectedFilter: (String) -> Unit,
    onAreaFilterClick: () -> Unit,
    onCategoryFilterClick: () -> Unit,
    onIngredientsFilterClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ToggleFilterButton(
            onClick = {
                selectedFilter(it)
                onAreaFilterClick()
            },
            text = stringResource(id = R.string.area)
        )
        ToggleFilterButton(
            onClick = {
                selectedFilter(it)
                onCategoryFilterClick()
            },
            text = stringResource(id = R.string.category)
        )
        ToggleFilterButton(
            onClick = {
                selectedFilter(it)
                onIngredientsFilterClick()
            },
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
                text = stringResource(id = R.string.sort_text),
                modifier = Modifier.padding(bottom = 0.dp),
                fontSize = 10.sp
            )
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun FilterPreview() {
    FiltersScreen(
//        navController = rememberNavController(),
        viewModel = FilterViewModel(),
        uiState = viewModel(),
//        onRefreshAction = {},
        onMealClicked = {}
    )
}
*/