package rs.raf.projekat1.rmanutritiont.data.api

import com.squareup.moshi.Json

data class MealApiResponse(
    @Json(name = "meals")
    val meals: List<MealFromApi>?
)

data class MealFromApi(
    @Json(name = "idMeal")
    val id: Int?,
    @Json(name = "strMeal")
    val name: String?,
    @Json(name = "strCategory")
    val categories: String?,
//    val categories: List<String>?,
    @Json(name = "strArea")
    val area: String?,
    @Json(name = "strInstructions")
    val cookInstructions: String?,
    @Json(name = "strMealThumb")
    val thumbnailUrl: String?
)
