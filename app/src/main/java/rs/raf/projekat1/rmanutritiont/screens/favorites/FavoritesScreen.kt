package rs.raf.projekat1.rmanutritiont.screens.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import rs.raf.projekat1.rmanutritiont.R
import rs.raf.projekat1.rmanutritiont.data.api.MealFromApi
import rs.raf.projekat1.rmanutritiont.ui.components.MealContainer

@Composable
fun FavoritesScreen(
    favoriteList: Set<MealFromApi>,
    onFavMealClick: (MealFromApi) -> Unit
) {
    Column(
        modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(id = R.string.favorites_title),
            style = MaterialTheme.typography.titleMedium
        )
        MealContainer(mealList = favoriteList, onCardClick = { onFavMealClick(it) })
    }
}
