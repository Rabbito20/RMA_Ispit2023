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
            NutritionApp(navController)
        }
    }
}

//  Ovo nam vrv ne treba onda
/*@OptIn(ExperimentalMaterial3Api::class)
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
}*/

