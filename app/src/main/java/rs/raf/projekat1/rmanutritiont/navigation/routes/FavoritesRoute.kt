package rs.raf.projekat1.rmanutritiont.navigation.routes

import androidx.compose.runtime.Composable
import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi
import rs.raf.projekat1.rmanutritiont.screens.favorites.FavoritesScreen

@Composable
fun FavoritesRoute(
    favoriteList: List<MealFromApi>,
    onMealClicked: (MealFromApi) -> Unit,
) {
    FavoritesScreen(
        favoriteList = favoriteList,
        onFavMealClick = { onMealClicked(it) })
}
