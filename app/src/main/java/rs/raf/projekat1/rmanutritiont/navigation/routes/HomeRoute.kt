package rs.raf.projekat1.rmanutritiont.navigation.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import rs.raf.projekat1.rmanutritiont.data.api.MealFromApi
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
    var randomMeal: MealFromApi?
    viewModel.fetchRandomMeal()

    HomeScreen(
        viewModel = viewModel,
        onFilterClick = {
            navController.navigate(
                route = "${TopLevelRoutes.Home.name}/${SecondaryRoutes.Filter.name}"
            )
        },
        onRandomClick = {
            randomMeal = viewModel.randomMeal.value

            if (randomMeal != null)
                onRandomClicked(randomMeal!!)
        },
        onCategoryClicked = { onCategoryClicked(it) }
    )

}
