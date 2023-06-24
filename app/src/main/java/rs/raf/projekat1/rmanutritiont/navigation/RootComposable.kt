package rs.raf.projekat1.rmanutritiont.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import rs.raf.projekat1.rmanutritiont.R
import rs.raf.projekat1.rmanutritiont.home.HomeScreen
import rs.raf.projekat1.rmanutritiont.statistics.StatisticsScreen
import rs.raf.projekat1.rmanutritiont.ui.theme.RmaNutritionTTheme

@Composable
fun RootComposable(/*appContainer: AppContainer*/) {
    RmaNutritionTTheme {

        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()

            //  Provera da li je ulogovan
//            val isSignedIn: Boolean       //    todo
//            AuthenticatedContainer(navController = navController)

            //  #############
            /*NavHost(navController = navController as NavHostController, startDestination = "home") {
                navigation(startDestination = "login", route = "auth") {

                    composable(route = "login") {
                        Text(text = "Login screen")
                    }

                    composable(route = "home") {}

                    composable(route = "favorites") {}

                    composable(route = "statistics") {}
                }
            }*/
            //  #############

            NutritionApp()


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutritionAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back button")
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutritionApp(
//    viewModel: ViewModel
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            //  Ovo je za topAppBar
//            NutritionAppBar(canNavigateBack = false, navigateUp = { /*TODO: Back navigation*/ })
            BottomAppBar {/*TODO*/}
        }) { innerPadding ->

//        val uiState by viewModel

        NavHost(navController = navController, startDestination = NutritionScreen.FoodStatistics.name) {
            composable(route = NutritionScreen.Home.name) {
                HomeScreen()
            }

            composable(route = NutritionScreen.FoodStatistics.name) {
                StatisticsScreen()
            }
        }

    }
}

enum class NutritionScreen() {
    Login,
    Home,
    RecipeDetail,
    FoodStatistics
}
