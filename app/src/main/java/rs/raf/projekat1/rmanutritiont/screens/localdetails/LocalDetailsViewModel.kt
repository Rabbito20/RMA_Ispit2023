package rs.raf.projekat1.rmanutritiont.screens.localdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import rs.raf.projekat1.rmanutritiont.data.api.MealApiClient
import rs.raf.projekat1.rmanutritiont.data.api.MealRepository
import rs.raf.projekat1.rmanutritiont.data.local.LocalFavoriteMeal
import rs.raf.projekat1.rmanutritiont.data.local.MealDao


data class LocalDetailsUiState(
    val isFavorite: Boolean,
    val isLoading: Boolean,
    val meal: LocalFavoriteMeal,
)

/*
private data class LocalDetailsViewModelState(
    val isLoading: Boolean = false,
    val isFavorite: Boolean = false,
    val meal: LocalFavoriteMeal? = null,
) {
    fun toUiState(): LocalDetailsUiState = if (isFavorite) {
        LocalDetailsUiState.IsFavorite(
            isLoading = isLoading,
            isFavorite = isFavorite,
            meal = meal!!
        )
    } else {
        LocalDetailsUiState.NotFavorite(
            isLoading = isLoading,
            isFavorite = isFavorite,
            meal = meal!!
        )
    }
}*/

class LocalDetailsViewModel(
    meal: LocalFavoriteMeal,
    private val dao: MealDao,
) : ViewModel() {
//    private var mealApiRepo: MealRepository = MealApiClient.mealApiService

    private val viewModelState =
        MutableStateFlow(LocalDetailsUiState(isFavorite = false, isLoading = false, meal = meal))

    val uiState =
        viewModelState.stateIn(viewModelScope, SharingStarted.Eagerly, viewModelState.value)

    //  Functions
    fun removeFromFavorite(meal: LocalFavoriteMeal) {
        viewModelScope.launch {
            viewModelState.update { it.copy(isFavorite = false, isLoading = true) }
            dao.deleteMeal(meal)
            viewModelState.update { it.copy(isFavorite = false, isLoading = false) }

        }
    }


    //  Factory
    companion object {
        fun provideFactory(
            meal: LocalFavoriteMeal,
            dao: MealDao,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return LocalDetailsViewModel(meal, dao) as T
            }
        }
    }
}

