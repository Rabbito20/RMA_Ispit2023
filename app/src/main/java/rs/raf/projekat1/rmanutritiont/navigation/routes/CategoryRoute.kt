package rs.raf.projekat1.rmanutritiont.navigation.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi
import rs.raf.projekat1.rmanutritiont.screens.home.category.CategoryScreen
import rs.raf.projekat1.rmanutritiont.screens.home.category.CategoryUiState
import rs.raf.projekat1.rmanutritiont.screens.home.category.CategoryViewModel

@Composable
fun CategoryRoute(
    navController: NavController,
    categoryViewModel: CategoryViewModel,
//    onMealClick: () -> Unit
) {
    val uiState: CategoryUiState by categoryViewModel.uiState.collectAsStateWithLifecycle()
//    val mealList: List<MealFromApi> = mutableListOf()
    val mealList: List<MealFromApi> = categoryViewModel.mealList.value.orEmpty()

    val refreshState = rememberSwipeRefreshState(isRefreshing = uiState.isLoading)

    SwipeRefresh(state = refreshState, onRefresh = { categoryViewModel.onRefresh() }) {
        CategoryScreen(categoryUiState = uiState, mealList = mealList, onMealClick = {})
    }

}
