package rs.raf.projekat1.rmanutritiont.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import rs.raf.projekat1.rmanutritiont.screens.login.LoginScreen

@Composable
fun LoginAppContainer(navController: NavController, signedInState: () -> Unit) {

    //  TODO: Check login logic
    LoginScreen(onLoginClick = {
        signedInState()
//        navController.popBackStack()
    })

}
