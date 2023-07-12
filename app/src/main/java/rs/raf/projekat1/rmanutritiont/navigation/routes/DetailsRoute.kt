package rs.raf.projekat1.rmanutritiont.navigation.routes

import androidx.compose.runtime.Composable
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi
import rs.raf.projekat1.rmanutritiont.screens.details.DetailsViewModel
import rs.raf.projekat1.rmanutritiont.screens.details.MealScreenDetails

@Composable
fun DetailsRoute(
    viewModel: DetailsViewModel,
    isFavorite: Boolean,
    onFavoriteClick: (MealFromApi) -> Unit
) {
    val refreshState = rememberSwipeRefreshState(isRefreshing = viewModel.isLoading.value!!)
    SwipeRefresh(state = refreshState, onRefresh = { viewModel.onRefresh() }) {
        MealScreenDetails(
//            meal = apiMeal!!,
            viewModel = viewModel,
            onFavoriteClicked = { onFavoriteClick(it) },
            isFavorite = isFavorite
        )
    }
}