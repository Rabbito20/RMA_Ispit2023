package rs.raf.projekat1.rmanutritiont.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi

@Composable
fun MealContainer(mealList: List<MealFromApi>, onCardClick: (MealFromApi) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
    ) {
        //  Turn a list into a set in case there happen to be duplicate meals in the list
        mealList.toSet().forEach { meal ->
            SingleMealCard(
                modifier = Modifier.padding(top = 8.dp),
                meal = meal,
                onClick = { onCardClick(meal) }
            )
        }
    }
}
