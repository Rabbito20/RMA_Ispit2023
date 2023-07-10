package rs.raf.projekat1.rmanutritiont.data.model

import androidx.room.TypeConverter
import com.squareup.moshi.Json
import rs.raf.projekat1.rmanutritiont.data.local.LocalMeal

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
    val idOnApi: Int?,
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
) {

    @TypeConverter
    fun fromApiToLocal(mealFromApi: MealFromApi): LocalMeal {
        return LocalMeal(id = mealFromApi.idOnApi!!, meal = mealFromApi)
    }
}

data class AreaFromApi(
    @Json(name = "strArea")
    val areaList: List<String>?
)

data class TagsFromApi(
    @Json(name = "strTags")
    val tagsList: List<String>?
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

