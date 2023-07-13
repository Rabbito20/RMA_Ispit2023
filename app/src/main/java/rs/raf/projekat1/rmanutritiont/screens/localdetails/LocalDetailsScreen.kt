package rs.raf.projekat1.rmanutritiont.screens.localdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import rs.raf.projekat1.rmanutritiont.data.local.LocalFavoriteMeal
import rs.raf.projekat1.rmanutritiont.screens.details.MealScreenDetails

@Composable
fun LocalMealScreenDetails(
    localMeal: LocalFavoriteMeal,
    onLocalFavoriteClicked: (LocalFavoriteMeal) -> Unit,
) {
//    val stateRefresh by remember { mutableStateOf(false) }
//    val refreshState = rememberSwipeRefreshState(isRefreshing = stateRefresh)

//    SwipeRefresh(state = refreshState, onRefresh = { /*TODO*/ }) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        MealScreenDetails(
            meal = localMeal.mealApi!!,
            onFavoriteClicked = { onLocalFavoriteClicked(it.fromApiToLocal()) },
            isFavorite = true
        )

        LocalDataComponent(modifier = Modifier.padding(20.dp))
    }
//    }

}

@Composable
private fun LocalDataComponent(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(text = "Date...")
    }
}
