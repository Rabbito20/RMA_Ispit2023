package rs.raf.projekat1.rmanutritiont.navigation.routes

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import rs.raf.projekat1.rmanutritiont.R
import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi
import rs.raf.projekat1.rmanutritiont.navigation.SecondaryRoutes
import rs.raf.projekat1.rmanutritiont.navigation.TopLevelRoutes
import rs.raf.projekat1.rmanutritiont.screens.home.HomeScreen
import rs.raf.projekat1.rmanutritiont.screens.home.HomeUiState
import rs.raf.projekat1.rmanutritiont.screens.home.HomeViewModel

@Composable
fun HomeRoute(
    navController: NavController,
    viewModel: HomeViewModel,
    onRandomClicked: (MealFromApi?) -> Unit,
    onCategoryClicked: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    viewModel.fetchCategories()
    viewModel.fetchRandomMeal()

    LoadingContent(
        empty = when (uiState) {
            is HomeUiState.HasCategories -> false
            is HomeUiState.NoCategories -> uiState.isLoading
            else -> {
                true
            }
        },
        loading = uiState.isLoading,
        emptyContent = {
            Text(text = stringResource(id = R.string.loading_str))
        },
        content = {
            HomeScreen(
                viewModel = viewModel,
//        uiState = uiState,
                onFilterClick = {
                    navController.navigate(
                        route = "${TopLevelRoutes.Home.name}/${SecondaryRoutes.Filter.name}"
                    )
                },
                onRandomClick = {
                    val randomMeal: MealFromApi? = viewModel.randomMeal.value
                    if (randomMeal != null)
                        onRandomClicked(randomMeal)

                    Log.e("Button click", "Random on home screen clicked!")
                },
                onCategoryClicked = { onCategoryClicked(it) }
            )
        },
        onRefresh = viewModel::onRefresh
    )
}

@Composable
fun LoadingContent(
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
    loading: Boolean,
    content: @Composable () -> Unit,
    onRefresh: () -> Unit,      //  TODO
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
                content()
            }
        }
    }
}
