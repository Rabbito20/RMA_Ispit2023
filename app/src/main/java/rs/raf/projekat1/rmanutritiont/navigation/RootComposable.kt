package rs.raf.projekat1.rmanutritiont.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import rs.raf.projekat1.rmanutritiont.data.local.LocalMealDatabase
import rs.raf.projekat1.rmanutritiont.ui.theme.RmaNutritionTTheme

@Composable
fun RootComposable(localDb: LocalMealDatabase) {
    RmaNutritionTTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()

            //  true while developing
//            var isSignedIn: Boolean by remember { mutableStateOf(true) }
            var isSignedIn: Boolean by remember { mutableStateOf(false) }

            when (isSignedIn) {
                true -> NutritionAppSignedIn(
                    navController = navController,
                    localDb = localDb,
                    logOutButtonClick = { isSignedIn = false })

                false -> LoginAppContainer(
                    navController,
                    signedInState = {
                        //  TODO: Add sign in logic
                        isSignedIn = true
                    }
                )
            }

        }
    }
}
