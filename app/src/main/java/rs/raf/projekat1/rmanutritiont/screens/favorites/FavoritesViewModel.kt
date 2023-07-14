package rs.raf.projekat1.rmanutritiont.screens.favorites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import rs.raf.projekat1.rmanutritiont.data.local.LocalFavoriteMeal
import rs.raf.projekat1.rmanutritiont.data.local.MealDao

interface FavoritesUiState {
    val isLoading: Boolean

    data class NoMeals(
        override val isLoading: Boolean
    ) : FavoritesUiState

    data class HasMeals(
        val mealsFeed: MutableSet<LocalFavoriteMeal>,
        override val isLoading: Boolean
    ) : FavoritesUiState
}

private data class FavoritesViewModelState(
    val isLoading: Boolean = false,
    val mealsFeed: MutableSet<LocalFavoriteMeal>? = null
) {
    fun toUiState(): FavoritesUiState = if (mealsFeed == null) {
        FavoritesUiState.NoMeals(isLoading = isLoading)
    } else {
        FavoritesUiState.HasMeals(isLoading = isLoading, mealsFeed = mealsFeed)
    }
}


class FavoritesViewModel(
    mealsFeed: MutableSet<LocalFavoriteMeal>,
    private val dao: MealDao
) : ViewModel() {

    private val viewModelState = MutableStateFlow(
        FavoritesViewModelState(
            isLoading = true,
            mealsFeed = mealsFeed
        )
    )

    val uiState = viewModelState
        .map(FavoritesViewModelState::toUiState)
        .stateIn(viewModelScope, SharingStarted.Eagerly, viewModelState.value.toUiState())

    private val _searchParameter = MutableLiveData<String>("")
    val searchParameter: LiveData<String> = _searchParameter

    init {
        onRefresh()
        onSearchInputChanged(_searchParameter.value.toString())
    }

    fun onRefresh() {
        fetchMealsFromLocalDb(viewModelState.value.mealsFeed)
    }

    private fun onSearchInputChanged(searchString: String) {
        viewModelState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                dao.getMealsByName(searchString)
                _searchParameter.value = searchString.trim()

                viewModelState.update {
                    it.copy(isLoading = false)
                }
            } catch (e: Exception) {
                Log.e("Fetch meal by name error", e.message.toString())
            }
        }
    }

    fun removeFromDb(meal: LocalFavoriteMeal) {
        viewModelScope.launch {
            viewModelState.update { it.copy(isLoading = true) }
            try {
                dao.deleteMeal(meal)
            } catch (e: Exception) {
                Log.e("Favorites DB deletion error", e.message.toString())
            }
            viewModelState.update { it.copy(isLoading = false) }
        }
    }

    private fun fetchMealsFromLocalDb(dbList: MutableSet<LocalFavoriteMeal>?) {
        viewModelScope.launch {
            viewModelState.update { it.copy(isLoading = true) }
            try {
                val localMeals = dao.getAllMealsLocal()

                localMeals.toSet().forEach {
                    dbList?.add(it)
                }

            } catch (e: Exception) {
                Log.e("Favorites DB fetch error", e.message.toString())
            }

            viewModelState.update { it.copy(isLoading = false) }
        }
    }

    companion object {
        fun provideFactory(
            mealsFeed: MutableSet<LocalFavoriteMeal>,
            dao: MealDao
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FavoritesViewModel(mealsFeed, dao) as T
            }
        }
    }
}
