package rs.raf.projekat1.rmanutritiont.data.api

import com.squareup.moshi.Json
import rs.raf.projekat1.rmanutritiont.data.model.Meal

data class MealApiResponse(
    @Json(name = "meals")
    val meals: List<Meal>?
)

data class Meal(
    @Json(name = "idMeal")
    val id: Int?,
    @Json(name = "strMeal")
    val name: String?,
    @Json(name = "strCategory")
    val category: String?,
    @Json(name = "strArea")
    val area: String?,
    @Json(name = "strInstructions")
    val cookInstructions: String?,
    @Json(name = "strMealThumb")
    val thumbnailUrl: String?
)
