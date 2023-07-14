package rs.raf.projekat1.rmanutritiont.navigation.routes

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi
import rs.raf.projekat1.rmanutritiont.screens.details.DetailsViewModel
import rs.raf.projekat1.rmanutritiont.screens.details.MealScreenDetails
import rs.raf.projekat1.rmanutritiont.ui.components.LoadingContentBarWithText

@Composable
fun DetailsRoute(
    viewModel: DetailsViewModel,
    isFavorite: Boolean,
    onFavoriteClick: (MealFromApi, String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val refreshState = rememberSwipeRefreshState(isRefreshing = uiState.isLoading)
    SwipeRefresh(state = refreshState, onRefresh = { viewModel.onRefresh() }) {
        when (uiState.isLoading) {
            true -> {
                viewModel.fetchSingleMealData(uiState.meal.toString())
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LoadingContentBarWithText()
                }
            }

            false -> {
                val meal = uiState.meal!!
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    MealScreenDetails(
                        meal = meal,
//                        viewModel = viewModel,
                        onFavoriteClicked = { apiMeal, timeText, date ->
                            //  Not working well!!!
                            onFavoriteClick(apiMeal, timeText)
                        },
                        isFavorite = isFavorite
                    )
                }
            }
        }
    }
}
