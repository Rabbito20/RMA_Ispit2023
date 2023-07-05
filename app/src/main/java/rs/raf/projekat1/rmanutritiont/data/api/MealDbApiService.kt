package rs.raf.projekat1.rmanutritiont.data.api

import retrofit2.Response
import retrofit2.http.GET
import rs.raf.projekat1.rmanutritiont.data.model.MealCategory
import rs.raf.projekat1.rmanutritiont.data.utils.Constants

class MealDbApiService : MealDbApiServiceInterface {
    //    private val client =
    override suspend fun getCategories(): Response<MealCategory> {
        TODO("Not yet implemented")
    }


}


interface MealDbApiServiceInterface {
    @GET("api/json/v1/${Constants.API_KEY}/categories.php")
    suspend fun getCategories(): Response<MealCategory>

    /*@GET("api/json/v1/${Constants.API_KEY}/categories.php")
    suspend fun getMealByCategory(@Query("c") category: String): Response<List<Meal>>*/

    /*@GET("api/json/v1/${Constants.API_KEY}/categories.php")
    suspend fun getCategories(): Response<MealCategory>*/

}
