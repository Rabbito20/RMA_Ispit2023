package rs.raf.projekat1.rmanutritiont

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.room.Room
import rs.raf.projekat1.rmanutritiont.data.local.LocalMealDatabase
import rs.raf.projekat1.rmanutritiont.navigation.RootComposable

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            LocalMealDatabase::class.java,
            "meals.db"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            RootComposable(db)
        }
    }
}
