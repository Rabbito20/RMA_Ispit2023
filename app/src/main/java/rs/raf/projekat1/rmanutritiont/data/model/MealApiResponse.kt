package rs.raf.projekat1.rmanutritiont.data.model

import androidx.room.TypeConverter
import com.squareup.moshi.Json
import rs.raf.projekat1.rmanutritiont.data.local.LocalFavoriteMeal

data class MealApiResponse(
    @Json(name = "meals")
    val meals: List<MealFromApi>?
)

data class CategoryApiResponse(
    @Json(name = "categories")
    val categories: List<CategoryFromApi>?
)

data class ResponseById(
    @Json(name = "idMeal")
    val apiId: String,
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
    val tags: String?,
    @Json(name = "strIngredients")
    val ingredients: List<String>?,
) {
    fun fromApiByIdToRegularApiMeal(): MealFromApi {
        return MealFromApi(
            idOnApi = apiId,
            name = name,
            category = category,
            area = area,
            cookInstructions = cookInstructions,
            thumbnailUrl = thumbnailUrl,
            tags = tags,
        )
    }
}

data class MealFromApi(
    @Json(name = "idMeal")
    val idOnApi: String,
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
    fun fromApiToLocal(): LocalFavoriteMeal {
        return LocalFavoriteMeal(id = idOnApi, mealApi = this)
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

