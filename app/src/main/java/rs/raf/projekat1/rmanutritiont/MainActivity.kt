package rs.raf.projekat1.rmanutritiont

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import rs.raf.projekat1.rmanutritiont.navigation.RootComposable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RootComposable()
        }
    }
}
