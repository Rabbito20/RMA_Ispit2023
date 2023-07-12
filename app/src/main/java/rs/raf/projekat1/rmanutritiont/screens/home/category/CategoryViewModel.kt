package rs.raf.projekat1.rmanutritiont.screens.home.category

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import rs.raf.projekat1.rmanutritiont.data.api.MealApiClient
import rs.raf.projekat1.rmanutritiont.data.api.MealRepository
import rs.raf.projekat1.rmanutritiont.data.model.MealApiResponse
import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi

interface CategoryUiState {
    val isLoading: Boolean
    val categoryName: String
//    val mealsFeed: MealApiResponse

    data class NoMeals(
        override val isLoading: Boolean,
        override val categoryName: String,
    ) : CategoryUiState

    data class HasMeals(
        val mealsFeed: MealApiResponse?,
        val selectedMeal: MealFromApi?,
        override val isLoading: Boolean,
        override val categoryName: String,
    ) : CategoryUiState
}

private data class CategoryViewModelState(
    val isLoading: Boolean = false,
    val categoryName: String,
    val mealsFeed: MealApiResponse? = null,
    val selectedMeal: MealFromApi? = null,
) {
    fun toUiState(): CategoryUiState = if (mealsFeed == null) {
        CategoryUiState.NoMeals(isLoading = isLoading, categoryName = categoryName)
    } else {
        CategoryUiState.HasMeals(
            isLoading = isLoading,
            mealsFeed = mealsFeed,
            categoryName = categoryName,
            selectedMeal = selectedMeal
        )
    }
}

class CategoryViewModel(
    private var categoryName: String
) : ViewModel() {

    private var mealApiRepo: MealRepository

    private var _mealList = MutableLiveData<List<MealFromApi>>()
    val mealList: LiveData<List<MealFromApi>> = _mealList

    private var _selectedMeal = MutableLiveData<MealFromApi>()
    private val selectedMeal: LiveData<MealFromApi> = _selectedMeal

    private val viewModelState = MutableStateFlow(
        CategoryViewModelState(
            isLoading = true,
            categoryName = categoryName
        )
    )

    val uiState = viewModelState
        .map(CategoryViewModelState::toUiState)
        .stateIn(viewModelScope, SharingStarted.Eagerly, viewModelState.value.toUiState())

    init {
        mealApiRepo = MealApiClient.mealApiService
        onRefresh()

        //  Observe for changes
        viewModelScope.launch {
            _selectedMeal.value
            _mealList.value
        }
    }

    fun getSelectedMeal(): MealFromApi? {
//        viewModelState.update { it.copy(isLoading = false, selectedMeal = selectedMeal.value) }
        return _selectedMeal.value
    }

    fun fetchSingleMealData(name: String?) {
        viewModelState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                mealApiRepo = MealApiClient.mealApiService
                val response = withContext(Dispatchers.IO) {
                    mealApiRepo.getMealsByName(name.toString())
                }
                val resp = response.body()?.meals?.first()!!
                _selectedMeal.value = MealFromApi(
                    idOnApi = resp.idOnApi,
                    name = resp.name,
                    category = resp.category,
                    area = resp.area,
                    cookInstructions = resp.cookInstructions,
                    thumbnailUrl = resp.thumbnailUrl,
                    tags = resp.tags
                )

                viewModelState.update { it.copy(isLoading = false, selectedMeal = resp) }
            } catch (e: Exception) {
                Log.e("Meals fetch error", e.message.toString())
            }
        }
    }

    fun onRefresh() {
        getMealsByCategory()
//        Log.e("Djura", "==================== ViewModel ====================")
//        Log.e("Djura", "New meal is\n${selectedMeal.value}")
//        Log.e("Djura", "===================================================")
    }

    private fun getMealsByCategory() {
        viewModelState.update { it.copy(isLoading = true) }
        repeat(2) {
            viewModelScope.launch {
                try {
                    mealApiRepo = MealApiClient.mealApiService
                    val response = withContext(Dispatchers.IO) {
                        mealApiRepo.searchRecipesByCategory(categoryName)
                    }

                    val meals = response.body()?.meals
                    val mealsByCategory = mutableListOf<MealFromApi>()

                    meals.orEmpty().forEach { meal ->
                        val m = MealFromApi(
                            idOnApi = meal.idOnApi,
                            name = meal.name,
                            category = categoryName,
                            area = meal.area,
                            cookInstructions = meal.cookInstructions,
                            thumbnailUrl = meal.thumbnailUrl,
                            tags = meal.tags
                        )
                        mealsByCategory.add(m)
                    }

                    _mealList.value = mealsByCategory

                } catch (e: Exception) {
                    Log.e("Meals fetch error", e.message.toString())
                }
                viewModelState.update { it.copy(isLoading = false) }
            }
        }
    }

    companion object {
        fun provideFactory(
            categoryName: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CategoryViewModel(categoryName = categoryName) as T
            }
        }
    }
}
