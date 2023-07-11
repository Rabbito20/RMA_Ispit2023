package rs.raf.projekat1.rmanutritiont.navigation.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import rs.raf.projekat1.rmanutritiont.screens.home.category.CategoryScreen

@Composable
fun CategoryRoute(
    navController: NavController,
    categoryRoute: String,  //  Temporary solution, delete later
//    viewModel:
) {
    CategoryScreen(categoryName = categoryRoute, onMealClick = {})
}
