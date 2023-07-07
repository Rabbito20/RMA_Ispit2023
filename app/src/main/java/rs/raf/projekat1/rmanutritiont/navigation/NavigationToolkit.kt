package rs.raf.projekat1.rmanutritiont.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import rs.raf.projekat1.rmanutritiont.R
import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi
import rs.raf.projekat1.rmanutritiont.navigation.routes.HomeRoute
import rs.raf.projekat1.rmanutritiont.screens.MealScreenDetails
import rs.raf.projekat1.rmanutritiont.screens.favorites.FavoritesScreen
import rs.raf.projekat1.rmanutritiont.screens.home.CategoryScreen
import rs.raf.projekat1.rmanutritiont.screens.home.HomeViewModel
import rs.raf.projekat1.rmanutritiont.screens.home.filter.FiltersScreen
import rs.raf.projekat1.rmanutritiont.screens.settings.SettingsScreen
import rs.raf.projekat1.rmanutritiont.screens.settings.createPlan.CreatePlanScreen
import rs.raf.projekat1.rmanutritiont.screens.statistics.StatisticsScreen
import rs.raf.projekat1.rmanutritiont.ui.components.AppBottomNavBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutritionAppSignedIn(
//    viewModel: ViewModel
    navController: NavHostController = rememberNavController()
) {
    val navBarList = listOf(
        BottomNavItem(
            name = stringResource(id = R.string.home_title),
            route = TopLevelRoutes.Home.name,
            icon = painterResource(id = R.drawable.ic_home_24)
        ),
        BottomNavItem(
            name = stringResource(id = R.string.favorites_title),
            route = TopLevelRoutes.Favorites.name,
            icon = painterResource(id = R.drawable.ic_favorites_24)
        ),
        BottomNavItem(
            name = stringResource(id = R.string.stats_title),
            route = TopLevelRoutes.FoodStatistics.name,
            icon = painterResource(id = R.drawable.ic_stat_24)
        ),
        BottomNavItem(
            name = stringResource(id = R.string.settings_title),
            route = TopLevelRoutes.Settings.name,
            icon = painterResource(id = R.drawable.ic_settings_24)
        ),
    )
    Scaffold(
        modifier = Modifier.height(48.dp),
        bottomBar = {
            BottomAppBar {
                AppBottomNavBar(
                    items = navBarList,
                    navController = navController,
                    onItemClick = {
                        navController.popBackStack()
                        navController.navigate(it.route)
                    })
            }
        }) { innerPadding ->
        AppNavigation(
            navController = navController,
            paddingValues = innerPadding
        )
    }
}

@Composable
fun AppNavigation(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = TopLevelRoutes.Home.name,
        modifier = Modifier.padding(paddingValues)
    ) {
        var categoryRoute: String? = ""
        val homeViewModel = HomeViewModel()

        var apiMeal: MealFromApi? = null
        val favoriteMeals = mutableSetOf<MealFromApi>()

        //  Home route domain
        composable(route = TopLevelRoutes.Home.name) {
            HomeRoute(
                navController = navController,
                viewModel = homeViewModel,
                onRandomClicked = { randomMeal ->
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
            FiltersScreen(
//                navController,
                onMealClicked = {
                    apiMeal = it
                    navController.navigate(route = SecondaryRoutes.MealDetails.name)
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
                MealScreenDetails(meal = apiMeal, onFavoriteClicked = { favMeal ->
                    favoriteMeals.add(favMeal)
                })
        }
    }
}
