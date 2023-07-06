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
import rs.raf.projekat1.rmanutritiont.data.model.Meal


class HomeViewModel : ViewModel() {
    private lateinit var mealApiService: MealApiService

    private val _randomMeal = MutableLiveData<Meal>()

    //    val randomMeal: LiveData<Meal> = _randomMeal
    var randomMeal: LiveData<Meal> = _randomMeal

    private val _mealsByIngredient = MutableLiveData<List<Meal>>()
    val mealByIngredient: LiveData<List<Meal>> = _mealsByIngredient

    fun fetchRandomMeal() {
        viewModelScope.launch {
            var test = Meal()
            try {
                mealApiService = MealApiClient.mealApiService
                val response = withContext(Dispatchers.IO) {
                    mealApiService.getRandomMeal()
                }

                val meal = response.body()?.meals?.get(0)

                meal?.let {
                    _randomMeal.value = Meal(
                        id = it.id,
                        name = it.name,
                        categories = it.categories,
                        area = it.area,
                        cookInstructions = it.cookInstructions,
                        thumbnailUrl = it.thumbnailUrl
                    )

//                    Log.e("Djura", "#################")
//                    Log.e("Djura", "ViewModel Meal response -> ${it}")
                }

//                Log.e("Djura", "#################")
//                Log.e("Djura", "ViewModel Meal response -> ${meal?.id}")


            } catch (e: Exception) {
                Log.e("Fetch Random Meal Error", e.toString())
            }
        }
    }
}

