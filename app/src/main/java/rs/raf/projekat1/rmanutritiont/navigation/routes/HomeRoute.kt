package rs.raf.projekat1.rmanutritiont.navigation.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import rs.raf.projekat1.rmanutritiont.navigation.SecondaryRoutes
import rs.raf.projekat1.rmanutritiont.navigation.TopLevelRoutes
import rs.raf.projekat1.rmanutritiont.screens.home.HomeScreen

@Composable
fun HomeRoute(
    navController: NavController,
    onCategoryClicked: (String) -> Unit
) {

    HomeScreen(
//                viewModel = homeViewModel,
        onFilterClick = {
            navController.navigate(
                route = "${TopLevelRoutes.Home.name}/${SecondaryRoutes.Filter.name}"
            )
        },
        onCategoryClicked = { onCategoryClicked(it) })

}
