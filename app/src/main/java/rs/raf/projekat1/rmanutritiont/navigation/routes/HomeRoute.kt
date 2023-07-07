package rs.raf.projekat1.rmanutritiont.navigation.routes

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi
import rs.raf.projekat1.rmanutritiont.navigation.SecondaryRoutes
import rs.raf.projekat1.rmanutritiont.navigation.TopLevelRoutes
import rs.raf.projekat1.rmanutritiont.screens.home.HomeScreen
import rs.raf.projekat1.rmanutritiont.screens.home.HomeViewModel

@Composable
fun HomeRoute(
    navController: NavController,
    viewModel: HomeViewModel,
    onRandomClicked: (MealFromApi?) -> Unit,
    onCategoryClicked: (String) -> Unit,
) {
    HomeScreen(
        viewModel = viewModel,
        onFilterClick = {
            navController.navigate(
                route = "${TopLevelRoutes.Home.name}/${SecondaryRoutes.Filter.name}"
            )
        },
        onRandomClick = {
            val randomMeal: MealFromApi? = viewModel.randomMeal.value
            if (randomMeal != null)
                onRandomClicked(randomMeal)

            Log.e("Button click", "Random on home screen clicked!")
        },
        onCategoryClicked = { onCategoryClicked(it) }
    )
}
