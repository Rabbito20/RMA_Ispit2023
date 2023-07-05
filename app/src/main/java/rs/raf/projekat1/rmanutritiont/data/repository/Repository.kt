package rs.raf.projekat1.rmanutritiont.data.repository

import retrofit2.Response
import rs.raf.projekat1.rmanutritiont.data.api.RetrofitInstance
import rs.raf.projekat1.rmanutritiont.data.model.MealCategory

class Repository {
    suspend fun getCategories(): Response<MealCategory> {
        return RetrofitInstance.mealDbApi.getCategories()
    }
}
