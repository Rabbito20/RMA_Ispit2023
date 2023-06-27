package rs.raf.projekat1.rmanutritiont.data

import androidx.compose.ui.graphics.vector.ImageVector

data class Meal(
    val name: String,
    val imageVector: ImageVector?,   //  Might change later
    val ingredients: List<String>?,
)
