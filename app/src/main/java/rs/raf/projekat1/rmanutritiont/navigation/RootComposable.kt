package rs.raf.projekat1.rmanutritiont.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
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
            val isSignedIn: Boolean = true
//            val isSignedIn: Boolean = false

            when (isSignedIn) {
                true -> NutritionAppSignedIn(navController)
                false -> LoginAppContainer(navController)
                else -> {
                    /* TODO: loading */
                }
            }
        }
    }
}
