package rs.raf.projekat1.rmanutritiont.data.model

import com.squareup.moshi.Json

data class MealApiResponse(
    @Json(name = "meals")
    val meals: List<MealFromApi>?
)

data class CategoryApiResponse(
    @Json(name = "categories")
    val categories: List<CategoryFromApi>?
)

data class MealFromApi(
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
    val thumbnailUrl: String?,
    @Json(name = "strTags")
    val tags: String?
)

data class CategoryFromApi(
    @Json(name = "idCategory")
    val idCategory: Int?,
    @Json(name = "strCategory")
    val strCategory: String?,
    @Json(name = "strCategoryThumb")
    val strCategoryThumb: String?,
    @Json(name = "strCategoryDescription")
    val strCategoryDescription: String?,
)
