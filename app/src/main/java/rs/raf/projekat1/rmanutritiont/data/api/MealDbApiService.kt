package rs.raf.projekat1.rmanutritiont.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import rs.raf.projekat1.rmanutritiont.data.model.AreaFromApi
import rs.raf.projekat1.rmanutritiont.data.model.CategoryApiResponse
import rs.raf.projekat1.rmanutritiont.data.model.MealApiResponse
import rs.raf.projekat1.rmanutritiont.data.utils.Constants

//  MealApiService
interface MealRepository {
    @GET("random.php")
    suspend fun getRandomMeal(): Response<MealApiResponse>

    @GET("categories.php")
    suspend fun getMealCategories(): Response<CategoryApiResponse>

    @GET("search.php")
    suspend fun getAllMeals(@Query("s") searchParameter: String): Response<MealApiResponse>

    @GET("list.php")
    suspend fun getMealAreas(@Query("a") searchParameter: String): Response<AreaFromApi>

    @GET("list.php")
    suspend fun getMealsByCategory(@Query("c") searchParameter: String): Response<MealApiResponse>
    //  Think about the category name

    @GET("list.php")
    suspend fun getMealIngredients(@Query("i") searchParameter: String): Response<MealApiResponse>

    @GET("filter.php")
    suspend fun searchRecipesByCategory(@Query("i") category: String): Response<MealApiResponse>

    //  TODO
//    @GET("filter.php")
//    suspend fun searchRecipesByIngredient(@Query("i") ingredient: String): Response<MealApiResponse>
}

object MealApiClient {
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val retrofit: Retrofit =
        Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()

    val mealApiService: MealRepository = retrofit.create(MealRepository::class.java)
}
