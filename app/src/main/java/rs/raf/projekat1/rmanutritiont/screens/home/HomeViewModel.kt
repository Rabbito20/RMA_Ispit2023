package rs.raf.projekat1.rmanutritiont.screens.home

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
import rs.raf.projekat1.rmanutritiont.data.model.CategoryApiResponse
import rs.raf.projekat1.rmanutritiont.data.model.CategoryFromApi
import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi

interface HomeUiState {
    val isLoading: Boolean
//    val searchInput: String

    data class HasCategories(
        val categoriesFeed: CategoryApiResponse,
//        val selectedCategory: CategoryInFeed?,
        override val isLoading: Boolean,
    ) : HomeUiState

    data class NoCategories(
        override val isLoading: Boolean
    ) : HomeUiState
}


//private data class HomeUiState(
//    val isLoading: Boolean
//)

private data class HomeViewModelState(
    val isLoading: Boolean = false,
    val categoriesFeed: CategoryApiResponse? = null,
//    val selectedCategory: CategoryInFeed
) {
    fun toUiState(): HomeUiState = if (categoriesFeed == null) {
        HomeUiState.NoCategories(isLoading = isLoading)
    } else {
        HomeUiState.HasCategories(
            isLoading = isLoading,
            categoriesFeed = categoriesFeed
        )
    }
}

class HomeViewModel(
//    private var mealApiRepo: MealRepository
) : ViewModel() {
    private lateinit var mealApiRepo: MealRepository

    private val _randomMeal = MutableLiveData<MealFromApi>()
    val randomMeal: LiveData<MealFromApi> = _randomMeal

    private val _mealsByIngredient = MutableLiveData<List<MealFromApi>>()
    val mealByIngredient: LiveData<List<MealFromApi>> = _mealsByIngredient

    private var _categoryList = MutableLiveData<List<CategoryFromApi>>()
    val categoryList: LiveData<List<CategoryFromApi>> = _categoryList

    private val viewModelState = MutableStateFlow(HomeViewModelState(isLoading = true))


    val uiState = viewModelState
        .map(HomeViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        mealApiRepo = MealApiClient.mealApiService
        onRefresh()

        //  Observe for changes
        viewModelScope.launch {
            mealApiRepo.getMealCategories()
        }
    }

    fun onRefresh() {
        viewModelState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val resultCategories = mealApiRepo.getMealCategories()
            viewModelState.update {
                if (resultCategories.isSuccessful) {
                    val homeViewModelState: HomeViewModelState = try {
                        it.copy(isLoading = false, categoriesFeed = resultCategories.body())
                    } catch (e: Exception) {
                        Log.e("Fetching error", e.message.toString())
                        it.copy(isLoading = true)
                    }
                    homeViewModelState
                } else {
                    it.copy(isLoading = true)
                }
            }
        }
    }

    fun fetchRandomMeal() {
        viewModelScope.launch {
            try {
                mealApiRepo = MealApiClient.mealApiService
                val response = withContext(Dispatchers.IO) {
                    mealApiRepo.getRandomMeal()
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
                mealApiRepo = MealApiClient.mealApiService
                val response = withContext(Dispatchers.IO) {
                    mealApiRepo.getMealCategories()
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
//            mealApiRepo: MealRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(/*mealApiRepo = mealApiRepo*/) as T
//                return super.create(modelClass)     // debug
            }
        }
    }

}
