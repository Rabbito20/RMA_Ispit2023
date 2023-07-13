package rs.raf.projekat1.rmanutritiont.navigation.routes

import androidx.compose.runtime.Composable
import rs.raf.projekat1.rmanutritiont.data.local.LocalFavoriteMeal
import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi
import rs.raf.projekat1.rmanutritiont.screens.favorites.FavoritesScreen

@Composable
fun FavoritesRoute(
    favoriteList: List<LocalFavoriteMeal>,
    onMealClicked: (MealFromApi) -> Unit,
) {
    /** Favorites sadrzi podatke
     *  kad ce jelo biti pripremljeno (danasnji datum je default),
     *  kategorija: dorucak, rucak, vecera
     *
     *  todo:   Otvaranje kamere i zamena default fotografije sa API-ja
     *
     * */

    val convertedList = mutableListOf<MealFromApi>()

    favoriteList.forEach {
        convertedList.add(it.mealApi!!)
    }

    FavoritesScreen(
        favoriteList = convertedList,
        onFavMealClick = { onMealClicked(it) })
}
