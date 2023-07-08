package rs.raf.projekat1.rmanutritiont.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi
import rs.raf.projekat1.rmanutritiont.navigation.routes.FilterRoute
import rs.raf.projekat1.rmanutritiont.navigation.routes.HomeRoute
import rs.raf.projekat1.rmanutritiont.screens.MealScreenDetails
import rs.raf.projekat1.rmanutritiont.screens.favorites.FavoritesScreen
import rs.raf.projekat1.rmanutritiont.screens.home.CategoryScreen
import rs.raf.projekat1.rmanutritiont.screens.home.HomeViewModel
import rs.raf.projekat1.rmanutritiont.screens.home.filter.FilterViewModel
import rs.raf.projekat1.rmanutritiont.screens.settings.SettingsScreen
import rs.raf.projekat1.rmanutritiont.screens.settings.createPlan.CreatePlanScreen
import rs.raf.projekat1.rmanutritiont.screens.statistics.StatisticsScreen


@Composable
fun AppNavigation(
//    appContainer: AppContainer,
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = TopLevelRoutes.Home.name,
        modifier = Modifier.padding(innerPadding)
    ) {
        //  Has to be initialized here
        val homeViewModel = HomeViewModel.provideFactory()
            .create(HomeViewModel::class.java)

        var categoryRoute: String? = ""
        var apiMeal: MealFromApi? = null
//        val favoriteMeals = mutableSetOf<MealFromApi>()
        val favoriteMeals = mutableListOf<MealFromApi>()

        //  Home route domain
        composable(route = TopLevelRoutes.Home.name) {
            HomeRoute(
                navController = navController,
                viewModel = homeViewModel,
                onRandomClicked = { randomMeal ->
                    homeViewModel.fetchRandomMeal()
                    apiMeal = randomMeal!!
                    navController.navigate(route = SecondaryRoutes.MealDetails.name)
                },
                onCategoryClicked = { categoryRouteName ->
                    categoryRoute = categoryRouteName
                    navController.navigate(route = "${TopLevelRoutes.Home.name}/${SecondaryRoutes.Category.name}")
                }
            )
        }

        composable(route = "${TopLevelRoutes.Home.name}/${SecondaryRoutes.Filter.name}") {
            val filterViewModel =
                FilterViewModel.provideFactory().create(FilterViewModel::class.java)

            FilterRoute(
                navController = navController,
                viewModel = filterViewModel,
                onMealClick = {
                    apiMeal = it
                })
        }
        composable(route = "${TopLevelRoutes.Home.name}/${SecondaryRoutes.Category.name}") {
            CategoryScreen(categoryName = categoryRoute.toString())
        }


        //  Favorites route domain
        composable(route = TopLevelRoutes.Favorites.name) {
            FavoritesScreen(
                favoriteList = favoriteMeals,
                onFavMealClick = {
                    apiMeal = it
                    navController.navigate(route = SecondaryRoutes.MealDetails.name)
                })
        }

        //  Statistics route domain
        composable(route = TopLevelRoutes.FoodStatistics.name) {
            StatisticsScreen()
        }

        //  Settings route domain
        composable(route = TopLevelRoutes.Settings.name) {
            SettingsScreen(onCreatePlanClick = {
                navController.navigate(route = "${TopLevelRoutes.Settings.name}/${SecondaryRoutes.CreatePlan.name}")
            })
        }
        composable(route = "${TopLevelRoutes.Settings.name}/${SecondaryRoutes.CreatePlan.name}") {
            CreatePlanScreen()
        }

        //  Meal details
        composable(route = SecondaryRoutes.MealDetails.name) {
            if (apiMeal != null)
                MealScreenDetails(
                    meal = apiMeal,
                    onFavoriteClicked = { favMeal ->
                        favoriteMeals.add(favMeal)
                    })
        }
    }
}
