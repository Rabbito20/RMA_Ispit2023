package rs.raf.projekat1.rmanutritiont.navigation.routes

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import rs.raf.projekat1.rmanutritiont.R
import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi
import rs.raf.projekat1.rmanutritiont.navigation.SecondaryRoutes
import rs.raf.projekat1.rmanutritiont.navigation.TopLevelRoutes
import rs.raf.projekat1.rmanutritiont.screens.home.HomeScreen
import rs.raf.projekat1.rmanutritiont.screens.home.HomeUiState
import rs.raf.projekat1.rmanutritiont.screens.home.HomeViewModel
import rs.raf.projekat1.rmanutritiont.ui.components.LoadingContent
import rs.raf.projekat1.rmanutritiont.ui.components.ShimmerLoadingLine

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
            else -> true
        },
        loading = uiState.isLoading,
        emptyContent = {
            ShimmerLoadingLine(
                isLoading = uiState.isLoading,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                contentAfterLoading = {}
            )
            Text(text = stringResource(id = R.string.loading_str))
        },
        loadedContent = {
            HomeScreen(
                viewModel = viewModel,
                uiState = uiState,
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
