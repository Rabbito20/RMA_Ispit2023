package rs.raf.projekat1.rmanutritiont.navigation.routes

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import rs.raf.projekat1.rmanutritiont.navigation.SecondaryRoutes
import rs.raf.projekat1.rmanutritiont.navigation.TopLevelRoutes
import rs.raf.projekat1.rmanutritiont.screens.home.HomeScreen
import rs.raf.projekat1.rmanutritiont.screens.home.HomeViewModel

@Composable
fun HomeRoute(
    navController: NavController,
    onCategoryClicked: (String) -> Unit
) {

    val viewModel = HomeViewModel()

    Log.e("Djura", "===================================================================")
    viewModel.fetchRandomMeal()


    HomeScreen(
//                viewModel = homeViewModel,
        onFilterClick = {
            navController.navigate(
                route = "${TopLevelRoutes.Home.name}/${SecondaryRoutes.Filter.name}"
            )
        },
        onCategoryClicked = { onCategoryClicked(it) })

}
