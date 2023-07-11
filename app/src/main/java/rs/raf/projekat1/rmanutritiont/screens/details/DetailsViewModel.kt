package rs.raf.projekat1.rmanutritiont.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import rs.raf.projekat1.rmanutritiont.data.api.MealApiClient
import rs.raf.projekat1.rmanutritiont.data.api.MealRepository
import rs.raf.projekat1.rmanutritiont.data.local.LocalFavoriteMeal
import rs.raf.projekat1.rmanutritiont.data.local.MealDao
import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi

data class DetailsViewModelState(
    val meal: LocalFavoriteMeal,
//    val meal: MealFromApi,
    val isFavorite: Boolean
)

class DetailsViewModel(
    meal: LocalFavoriteMeal,
//    meal: MealFromApi,
    isFavorite: Boolean,
    private val dao: MealDao
) : ViewModel() {

    private var mealApiRepo: MealRepository = MealApiClient.mealApiService

    private val _isFavorite = MutableLiveData(false)
    private val isFavorite: LiveData<Boolean> = _isFavorite

    //  This screen won't open until we have opened at least one meal
    private val viewModelState = if (meal == null)
        MutableStateFlow(
            DetailsViewModelState(
                dao.getLatestMeal()?.mealApi?.fromApiToLocal()!!,
                isFavorite
            )
        )
    else
        MutableStateFlow(DetailsViewModelState(meal, isFavorite))

    val uiState =
        viewModelState.stateIn(viewModelScope, SharingStarted.Eagerly, viewModelState.value)

    fun isFavoriteChangeState(meal: MealFromApi) {
        _isFavorite.value = !_isFavorite.value!!

        viewModelScope.launch {
            if (isFavorite.value!!)
                dao.upsertMeal(meal.fromApiToLocal())
            else
                dao.deleteMeal(meal.fromApiToLocal())
        }
    }

    companion object {
        fun provideFactory(
            meal: LocalFavoriteMeal,
//            meal: MealFromApi,
            isFavorite: Boolean,
            dao: MealDao
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return DetailsViewModel(meal, isFavorite, dao) as T
            }
        }
    }
}

