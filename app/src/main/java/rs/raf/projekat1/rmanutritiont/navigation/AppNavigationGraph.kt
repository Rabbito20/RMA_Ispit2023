package rs.raf.projekat1.rmanutritiont.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import rs.raf.projekat1.rmanutritiont.data.local.LocalFavoriteMeal
import rs.raf.projekat1.rmanutritiont.data.local.LocalMealDatabase
import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi
import rs.raf.projekat1.rmanutritiont.navigation.routes.CategoryRoute
import rs.raf.projekat1.rmanutritiont.navigation.routes.DetailsRoute
import rs.raf.projekat1.rmanutritiont.navigation.routes.FavoritesRoute
import rs.raf.projekat1.rmanutritiont.navigation.routes.FilterRoute
import rs.raf.projekat1.rmanutritiont.navigation.routes.HomeRoute
import rs.raf.projekat1.rmanutritiont.navigation.routes.LocalMealRoute
import rs.raf.projekat1.rmanutritiont.screens.details.DetailsViewModel
import rs.raf.projekat1.rmanutritiont.screens.favorites.FavoritesViewModel
import rs.raf.projekat1.rmanutritiont.screens.home.HomeViewModel
import rs.raf.projekat1.rmanutritiont.screens.home.category.CategoryViewModel
import rs.raf.projekat1.rmanutritiont.screens.home.filter.FilterViewModel
import rs.raf.projekat1.rmanutritiont.screens.localdetails.LocalDetailsViewModel
import rs.raf.projekat1.rmanutritiont.screens.settings.SettingsScreen
import rs.raf.projekat1.rmanutritiont.screens.settings.createPlan.CreatePlanScreen
import rs.raf.projekat1.rmanutritiont.screens.statistics.StatisticsScreen


@Composable
fun AppNavigation(
    navController: NavHostController,
    localDb: LocalMealDatabase,
    innerPadding: PaddingValues,
    logOutButtonClick: () -> Unit,
) {
    var categoryRoute: String by remember { mutableStateOf("") }
    var apiMeal: MealFromApi? by remember { mutableStateOf(null) }
//    val favoriteMeals = mutableListOf<MealFromApi>()

    val favoriteMeals = mutableSetOf<LocalFavoriteMeal>()
    var localMeal: LocalFavoriteMeal? by remember { mutableStateOf(null) }

    NavHost(
        navController = navController,
        startDestination = TopLevelRoutes.Home.name,
        modifier = Modifier.padding(innerPadding)
    ) {
        //  Home route domain
        val homeViewModel = HomeViewModel.provideFactory()
            .create(HomeViewModel::class.java)
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
            val categoryViewModel =
                CategoryViewModel.provideFactory(categoryRoute)
                    .create(CategoryViewModel::class.java)

            CategoryRoute(
//                navController = navController,
                categoryViewModel = categoryViewModel,
                onMealClick = {
                    apiMeal = it
                    navController.navigate(route = SecondaryRoutes.MealDetails.name)
                }
            )
        }

        //  Favorites route domain
        composable(route = TopLevelRoutes.Favorites.name) {
            val dao = localDb.mealDao()
            val favoritesViewModel =
                FavoritesViewModel.provideFactory(mealsFeed = favoriteMeals, dao = dao)
                    .create(FavoritesViewModel::class.java)

            FavoritesRoute(
                favoriteList = favoriteMeals,
                viewModel = favoritesViewModel,
                onMealClicked = {
                    localMeal = it.fromApiToLocal()
                    navController.navigate(route = SecondaryRoutes.LocalMealDetails.name)
                }
            )
        }

        //  Statistics route domain
        composable(route = TopLevelRoutes.FoodStatistics.name) {
            StatisticsScreen()
        }

        //  Settings route domain
        composable(route = TopLevelRoutes.Settings.name) {
            SettingsScreen(
                onCreatePlanClick = {
                    navController.navigate(
                        route =
                        "${TopLevelRoutes.Settings.name}/${SecondaryRoutes.CreatePlan.name}"
                    )
                },
                onLogOutClick = logOutButtonClick
            )
        }
        composable(route = "${TopLevelRoutes.Settings.name}/${SecondaryRoutes.CreatePlan.name}") {
            CreatePlanScreen()
        }

        //  Meal details
        composable(route = SecondaryRoutes.MealDetails.name) {
            val dao = localDb.mealDao()
            val isFav = favoriteMeals.contains(apiMeal?.fromApiToLocal())

            val viewModel = DetailsViewModel.provideFactory(
//                    meal = dao.getLatestMeal()?.meal?.fromApiToLocal()!!,
//                    meal = apiMeal!!.fromApiToLocal(),
                meal = apiMeal!!,
                isLoading = false,
                dao = dao,
                isFavorite = isFav
            ).create(DetailsViewModel::class.java)

            if (apiMeal != null) {
                DetailsRoute(
                    viewModel = viewModel,
                    onFavoriteClick = { meal ->
                        viewModel.addMealToLocalDb(meal)
                        navController.popBackStack()
                        navController.navigate(route = SecondaryRoutes.LocalMealDetails.name)

                        localMeal = meal.fromApiToLocal()
                        favoriteMeals.add(localMeal!!)
                    },
                    isFavorite = isFav
                )
            }
        }

        //  Opening Local Favorites meal
        composable(route = SecondaryRoutes.LocalMealDetails.name) {
            val dao = localDb.mealDao()

            val localViewModel = LocalDetailsViewModel.provideFactory(localMeal!!, dao)
                .create(LocalDetailsViewModel::class.java)

            LocalMealRoute(
                localMeal = localMeal!!,
                viewModel = localViewModel,
                onClickFavorite = { meal ->
                    localViewModel.removeFromFavorite(meal)
                    apiMeal = meal.fromLocalToApi()
                    navController.popBackStack()
                    navController.navigate(route = SecondaryRoutes.MealDetails.name)

                    favoriteMeals.remove(localMeal)
                })
        }

    }
}
