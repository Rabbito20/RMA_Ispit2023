package rs.raf.projekat1.rmanutritiont.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
            
            //  Provera da li je ulogovan
//            val isSignedIn: Boolean       //    todo
            AuthenticatedContainer(navController = navController)
            
        }
    }
}
