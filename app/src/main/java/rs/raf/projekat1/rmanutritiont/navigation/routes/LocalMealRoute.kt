package rs.raf.projekat1.rmanutritiont.navigation.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import rs.raf.projekat1.rmanutritiont.data.local.LocalFavoriteMeal
import rs.raf.projekat1.rmanutritiont.screens.localdetails.LocalDetailsViewModel
import rs.raf.projekat1.rmanutritiont.screens.localdetails.LocalMealScreenDetails

@Composable
fun LocalMealRoute(
    localMeal: LocalFavoriteMeal,
    viewModel: LocalDetailsViewModel,
    onClickFavorite: (LocalFavoriteMeal) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LocalMealScreenDetails(localMeal = localMeal, onLocalFavoriteClicked = onClickFavorite)
}
