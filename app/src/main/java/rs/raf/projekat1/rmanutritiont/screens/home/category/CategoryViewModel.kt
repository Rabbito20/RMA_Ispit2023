package rs.raf.projekat1.rmanutritiont.screens.home.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import rs.raf.projekat1.rmanutritiont.data.api.MealApiClient
import rs.raf.projekat1.rmanutritiont.data.api.MealRepository
import rs.raf.projekat1.rmanutritiont.data.model.MealApiResponse

interface CategoryUiState {
    val isLoading: Boolean
    val categoryName: String
//    val mealsFeed: MealApiResponse

    data class NoMeals(
        override val isLoading: Boolean,
        override val categoryName: String,
    ) : CategoryUiState

    data class HasMeals(
        override val isLoading: Boolean,
        override val categoryName: String,
        val mealsFeed: MealApiResponse,
    ) : CategoryUiState
}

private class CategoryViewModelState(
    val isLoading: Boolean,
    val categoryName: String,
    val mealsFeed: MealApiResponse? = null,
) {
    fun toUiState(): CategoryUiState = if (mealsFeed == null)
        CategoryUiState.NoMeals(isLoading = isLoading, categoryName = categoryName)
    else
        CategoryUiState.HasMeals(
            isLoading = isLoading,
            categoryName = categoryName,
            mealsFeed = mealsFeed
        )
}

class CategoryViewModel() : ViewModel() {

    private var mealApiRepo: MealRepository

    private val _categoryName = MutableLiveData("")
    val categoryName: LiveData<String> = _categoryName

    private val viewModelState =
        MutableStateFlow(
            CategoryViewModelState(
                isLoading = true,
                categoryName = _categoryName.value.toString()
            )
        )

    val uiState = viewModelState
        .map(CategoryViewModelState::toUiState)
        .stateIn(viewModelScope, SharingStarted.Eagerly, viewModelState.value.toUiState())

    init {
        mealApiRepo = MealApiClient.mealApiService
    }

    companion object {
        fun provideFactory(
            //  todo: args
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CategoryViewModel() as T
            }
        }
    }
}
