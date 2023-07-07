package rs.raf.projekat1.rmanutritiont.screens.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import rs.raf.projekat1.rmanutritiont.data.api.MealApiClient
import rs.raf.projekat1.rmanutritiont.data.api.MealApiService
import rs.raf.projekat1.rmanutritiont.data.model.CategoryFromApi
import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi


class HomeViewModel(
//    private var mealApiService: MealApiService
) : ViewModel() {
    private lateinit var mealApiService: MealApiService

    private val _randomMeal = MutableLiveData<MealFromApi>()
    val randomMeal: LiveData<MealFromApi> = _randomMeal

    private val _mealsByIngredient = MutableLiveData<List<MealFromApi>>()
    val mealByIngredient: LiveData<List<MealFromApi>> = _mealsByIngredient

    private var _categoryList = MutableLiveData<List<CategoryFromApi>>()
    val categoryList: LiveData<List<CategoryFromApi>> = _categoryList

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
                        category = it.category,
                        area = it.area,
                        cookInstructions = it.cookInstructions,
                        thumbnailUrl = it.thumbnailUrl,
                        tags = it.tags
                    )
                }
            } catch (e: Exception) {
                Log.e("Fetch Error", e.toString())
            }
        }
    }

    fun fetchCategories() {
        viewModelScope.launch {
            try {
                mealApiService = MealApiClient.mealApiService
                val response = withContext(Dispatchers.IO) {
                    mealApiService.getMealCategories()
                }

                val categories = response.body()?.categories
                _categoryList.value = categories.orEmpty()

            } catch (e: Exception) {
                Log.e("Fetch Error", e.toString())
            }
        }
    }

    //  TODO:   Finish [provideFactory()] method
    companion object {
        fun provideFactory(
//            mealApiService: MealApiService,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel() as T
            }
        }
    }

}
