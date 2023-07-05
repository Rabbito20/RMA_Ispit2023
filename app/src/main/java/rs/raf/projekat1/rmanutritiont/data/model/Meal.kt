package rs.raf.projekat1.rmanutritiont.data.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Meal(
    val name: String,
    val imageVector: ImageVector?,   //  Might change later
    val ingredients: List<String>?,
    val cookInstructions: String = "Lorem ipsum text cook this, cook that. Lorem ipsum text cook this, cook that. ",
    val categories: List<String> = emptyList(),
    val area: String? = "Area test",
    val tagList: List<String> = emptyList(),
)
