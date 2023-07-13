package rs.raf.projekat1.rmanutritiont.screens.home.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi
import rs.raf.projekat1.rmanutritiont.ui.components.LoadingContentBarWithText
import rs.raf.projekat1.rmanutritiont.ui.components.MealContainer

@Composable
fun CategoryScreen(
    categoryUiState: CategoryUiState,
    mealList: List<MealFromApi>? = null,
    onMealClick: (MealFromApi) -> Unit
) {
    val titleText =
        if (categoryUiState.isLoading) categoryUiState.categoryName
        else "${categoryUiState.categoryName} (${mealList?.size})"
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .verticalScroll(rememberScrollState())
            .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 4.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = titleText,
            color = MaterialTheme.colorScheme.onBackground,
//            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier
//                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 12.dp)
        )
        when (categoryUiState.isLoading) {
            true -> LoadingContentBarWithText()
            false -> {
                MealContainer(mealList = mealList.orEmpty(), onCardClick = { onMealClick(it) })
            }
        }
    }
}
