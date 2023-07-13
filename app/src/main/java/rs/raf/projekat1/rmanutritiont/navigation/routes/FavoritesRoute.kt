package rs.raf.projekat1.rmanutritiont.navigation.routes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import rs.raf.projekat1.rmanutritiont.data.local.LocalFavoriteMeal
import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi
import rs.raf.projekat1.rmanutritiont.screens.favorites.FavoritesScreen
import rs.raf.projekat1.rmanutritiont.screens.favorites.FavoritesViewModel
import rs.raf.projekat1.rmanutritiont.ui.components.LoadingContentBarWithText

@Composable
fun FavoritesRoute(
    favoriteList: MutableSet<LocalFavoriteMeal>,
    viewModel: FavoritesViewModel,
    onMealClicked: (MealFromApi) -> Unit,
) {
    /** Favorites sadrzi podatke
     *  kad ce jelo biti pripremljeno (danasnji datum je default),
     *  kategorija: dorucak, rucak, vecera
     *
     *  todo:   Otvaranje kamere i zamena default fotografije sa API-ja
     *
     * */

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val refreshState = rememberSwipeRefreshState(isRefreshing = uiState.isLoading)


    SwipeRefresh(state = refreshState, onRefresh = viewModel::onRefresh) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val convertedList = mutableSetOf<MealFromApi>()
            favoriteList.forEach {
                convertedList.add(it.mealApi!!)
            }

            when (uiState.isLoading) {
                false -> FavoritesScreen(
                    favoriteList = convertedList,
                    onFavMealClick = {
                        onMealClicked(it)
                        viewModel.removeFromDb(it.fromApiToLocal())
                    }
                )

                true -> LoadingContentBarWithText()
            }
        }
    }

}
