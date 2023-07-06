package rs.raf.projekat1.rmanutritiont.screens.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import rs.raf.projekat1.rmanutritiont.data.api.MealApiClient
import rs.raf.projekat1.rmanutritiont.data.api.MealApiService
import rs.raf.projekat1.rmanutritiont.data.api.MealFromApi


class HomeViewModel : ViewModel() {
    private lateinit var mealApiService: MealApiService

    private val _randomMeal = MutableLiveData<MealFromApi>()
    //    val randomMeal: LiveData<Meal> = _randomMeal
    val randomMeal: LiveData<MealFromApi> = _randomMeal

    private val _mealsByIngredient = MutableLiveData<List<MealFromApi>>()
    val mealByIngredient: LiveData<List<MealFromApi>> = _mealsByIngredient

    fun fetchRandomMeal() {
        viewModelScope.launch {
            try {
                mealApiService = MealApiClient.mealApiService
                val response = withContext(Dispatchers.IO) {
                    mealApiService.getRandomMeal()
                }

                val meal = response.body()?.meals?.get(0)

                meal?.let {
                    _randomMeal.value = MealFromApi(
                        id = it.id,
                        name = it.name,
                        categories = it.categories,
                        area = it.area,
                        cookInstructions = it.cookInstructions,
                        thumbnailUrl = it.thumbnailUrl
                    )
                }

            } catch (e: Exception) {
                Log.e("Fetch Random Meal Error", e.toString())
            }
        }
    }
}

