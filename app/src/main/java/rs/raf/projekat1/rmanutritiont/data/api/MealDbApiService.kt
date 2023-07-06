package rs.raf.projekat1.rmanutritiont.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import rs.raf.projekat1.rmanutritiont.data.model.FoodCategory
import rs.raf.projekat1.rmanutritiont.data.utils.Constants

//class MealDbApiService : MealApiService {}

interface MealApiService {
    @GET("random.php")
    suspend fun getRandomMeal(): Response<MealApiResponse>

    @GET("filter.php")
    suspend fun searchRecipesByIngredient(@Query("i") ingredient: String): Response<List<MealApiResponse>>

    @GET("categories.php")
    suspend fun getMealCategories(): Response<List<FoodCategory>>

}

object MealApiClient {
//    val mealApiService: MealApiService = retrofit.create(MealApiService::class.java)

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val retrofit: Retrofit =
        Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()

    val mealApiService: MealApiService = retrofit.create(MealApiService::class.java)


    //  Hmmmmmmmm
    /*suspend fun getRandomMealClient(): Response<Meal> {
        return withContext(Dispatchers.IO) {
            mealApiService.getRandomMeal()
        }
    }*/

//    suspend fun searchMealsByIngredient(ingredient: String)
}
