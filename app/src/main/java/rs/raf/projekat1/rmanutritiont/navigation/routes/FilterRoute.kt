package rs.raf.projekat1.rmanutritiont.navigation.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi
import rs.raf.projekat1.rmanutritiont.navigation.SecondaryRoutes
import rs.raf.projekat1.rmanutritiont.screens.home.filter.FilterUiState
import rs.raf.projekat1.rmanutritiont.screens.home.filter.FilterViewModel
import rs.raf.projekat1.rmanutritiont.screens.home.filter.FiltersScreen

@Composable
fun FilterRoute(
    navController: NavController,
    viewModel: FilterViewModel,
    onMealClick: (MealFromApi) -> Unit,
) {
    val uiState: FilterUiState by viewModel.uiState.collectAsStateWithLifecycle()
    viewModel.fetchAllMeals()

    FiltersScreen(
        viewModel = viewModel,
        uiState = uiState,
        onRefreshAction = viewModel::onRefresh,
        onMealClicked = {
            onMealClick(it)
            navController.navigate(route = SecondaryRoutes.MealDetails.name)
        })
}
