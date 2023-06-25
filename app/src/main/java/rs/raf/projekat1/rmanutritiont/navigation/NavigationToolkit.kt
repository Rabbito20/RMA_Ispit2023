package rs.raf.projekat1.rmanutritiont.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import rs.raf.projekat1.rmanutritiont.R
import rs.raf.projekat1.rmanutritiont.favorites.FavoritesScreen
import rs.raf.projekat1.rmanutritiont.home.CategoryScreen
import rs.raf.projekat1.rmanutritiont.home.FiltersScreen
import rs.raf.projekat1.rmanutritiont.home.HomeScreen
import rs.raf.projekat1.rmanutritiont.settings.SettingsScreen
import rs.raf.projekat1.rmanutritiont.statistics.StatisticsScreen


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
            icon = Icons.Default.Home
        ),
        BottomNavItem(
            name = stringResource(id = R.string.favorites_title),
            route = TopLevelRoutes.Favorites.name,
            icon = Icons.Default.Favorite
        ),
        BottomNavItem(
            name = stringResource(id = R.string.stats_title),
            route = TopLevelRoutes.FoodStatistics.name,
            icon = Icons.Filled.CheckCircle
        ),
        BottomNavItem(
            name = stringResource(id = R.string.settings_title),
            route = TopLevelRoutes.Settings.name,
            icon = Icons.Default.Settings
        ),
    )
    Scaffold(
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

//        val uiState by viewModel

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
        composable(route = TopLevelRoutes.Home.name) {
            HomeScreen(
                onFilterClick = {
                    navController.navigate(
                        route =
                        "${TopLevelRoutes.Home.name}/${SecondaryRoutes.Filter.name}"
                    )
                },
                onCategoryClicked = {
                    categoryRoute = it
                    navController.navigate(
                        route =
                        "${TopLevelRoutes.Home.name}/${SecondaryRoutes.Category.name}"
                    )
                })
        }
        composable(route = "${TopLevelRoutes.Home.name}/${SecondaryRoutes.Filter.name}") {
            FiltersScreen(navController)
        }
        composable(route = "${TopLevelRoutes.Home.name}/${SecondaryRoutes.Category.name}") {
            CategoryScreen(categoryName = categoryRoute.toString())
        }

        composable(route = TopLevelRoutes.Favorites.name) {
            FavoritesScreen()
        }

        composable(route = TopLevelRoutes.FoodStatistics.name) {
            StatisticsScreen()
        }

        composable(route = TopLevelRoutes.Settings.name) {
            SettingsScreen()
        }
    }
}

@Composable
fun AppBottomNavBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    BottomAppBar(
        modifier = Modifier.background(color = Color.DarkGray),
        tonalElevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item) },
                icon = {
                    Column(
                        modifier = Modifier.size(64.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(imageVector = item.icon, contentDescription = item.name)
                        if (selected) {
                            Text(text = item.name, textAlign = TextAlign.Center, fontSize = 12.sp)
                        }
                    }
                }
            )
        }
    }
}
