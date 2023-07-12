package rs.raf.projekat1.rmanutritiont.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import rs.raf.projekat1.rmanutritiont.R
import rs.raf.projekat1.rmanutritiont.data.local.LocalMealDatabase
import rs.raf.projekat1.rmanutritiont.ui.components.AppBottomNavBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutritionAppSignedIn(
    navController: NavHostController = rememberNavController(),
    logOutButtonClick: () -> Unit,
    localDb: LocalMealDatabase
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
            localDb = localDb,
            innerPadding = innerPadding,
            logOutButtonClick = logOutButtonClick
        )
    }
}
