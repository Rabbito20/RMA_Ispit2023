package rs.raf.projekat1.rmanutritiont.screens.home.filter

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

interface FilterUiState {
    val isLoading: Boolean
//    val searchInput: String

    data class NoMeals(
        override val isLoading: Boolean
    ) : FilterUiState

    data class HasMeals(
        val mealsFeed: MealApiResponse,
        override val isLoading: Boolean,
    ) : FilterUiState
}

private data class FilterViewModelState(
    val isLoading: Boolean = false,
    val mealsFeed: MealApiResponse? = null
) {
    /*
    fun toUiState(): FilterUiState = if (mealsFeed == null) {
        FilterUiState.NoMeals(isLoading = isLoading)
    } else {
        FilterUiState.HasMeals(isLoading = isLoading, mealsFeed = mealsFeed)
    }*/

    fun toUiState(): FilterUiState = if (mealsFeed == null) {
        FilterUiState.NoMeals(isLoading = isLoading)
    } else {
        FilterUiState.HasMeals(
            isLoading = isLoading,
            mealsFeed = mealsFeed
        )
    }
}

class FilterViewModel() : ViewModel() {

    private var mealApiRepo: MealRepository

    private val _searchParameter = MutableLiveData("")
    val searchParameter: LiveData<String> = _searchParameter

    private val _mealsByIngredient = MutableLiveData<List<MealFromApi>>()
    val mealByIngredient: LiveData<List<MealFromApi>> = _mealsByIngredient

    private var _mealList = MutableLiveData<List<MealFromApi>>()
    val mealList: LiveData<List<MealFromApi>> = _mealList

    private val viewModelState = MutableStateFlow(FilterViewModelState(isLoading = true))

    val uiState = viewModelState
        .map(FilterViewModelState::toUiState)
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
            mealApiRepo.getAllMeals(searchParameter = searchParameter.value.toString())
        }
    }

    fun onRefresh() {
        viewModelState.update {
            it.copy(isLoading = true)
        }
        fetchAllMeals(searchString = "")
        viewModelScope.launch {
            val resultMeals = mealApiRepo.getAllMeals(searchParameter.value.toString())
            viewModelState.update {

                if (resultMeals.isSuccessful) {
                    val filterViewmModelState: FilterViewModelState = try {
//                        delay(3000)     //  For demo
                        it.copy(isLoading = false, mealsFeed = resultMeals.body())
                    } catch (e: Exception) {
                        Log.e("All meals fetch error", e.message.toString())
                        it.copy(isLoading = true)
                    }
                    filterViewmModelState
                } else {
                    it.copy(isLoading = true, mealsFeed = null)
                }

            }
        }   //  launch end
        Log.e("Filter call", "Refresh called")
    }

    fun fetchAllMeals(searchString: String = "") {

        viewModelScope.launch {
            try {
                mealApiRepo = MealApiClient.mealApiService
                val response = withContext(Dispatchers.IO) {
                    mealApiRepo.getAllMeals(searchString)
//                    mealApiRepo.getAllMeals("")
                }

                val meals = response.body()?.meals
                _mealList.value = meals.orEmpty()

            } catch (e: Exception) {
                Log.e("All meals fetch error", e.message.toString())
            }
        }
    }

    fun fetchMealsByName() {}
    fun fetchMealsByAlphabet() {}
    fun fetchMealsByTags(tags: List<String>? = null) {}

    companion object {
        fun provideFactory(): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FilterViewModel() as T
            }
        }
    }

}
