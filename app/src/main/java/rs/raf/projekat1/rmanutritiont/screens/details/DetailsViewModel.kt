package rs.raf.projekat1.rmanutritiont.screens.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import rs.raf.projekat1.rmanutritiont.data.api.MealApiClient
import rs.raf.projekat1.rmanutritiont.data.api.MealRepository
import rs.raf.projekat1.rmanutritiont.data.local.MealDao
import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi

data class DetailsViewModelState(
//    val meal: LocalFavoriteMeal,
    val meal: MealFromApi?,
    val isLoading: Boolean,
    val isFavorite: Boolean,
)

class DetailsViewModel(
//    meal: LocalFavoriteMeal?,
    meal: MealFromApi,
    isLoading: Boolean,
    isFavorite: Boolean,
    private val dao: MealDao,
) : ViewModel() {

    private var mealApiRepo: MealRepository = MealApiClient.mealApiService

    private val _isFavorite = MutableLiveData(false)
    private val isFavorite: LiveData<Boolean> = _isFavorite

//    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
//    val isLoading: LiveData<Boolean> = _isLoading

    //  This screen won't open until we have opened at least one meal
    private val viewModelState = if (meal == null)
        MutableStateFlow(
            DetailsViewModelState(
//                dao.getLatestMeal()?.mealApi?.fromApiToLocal()!!,
//                dao.getLatestMeal()?.mealApi,
                meal = meal,
                isLoading = true,
                isFavorite,
            )
        )
    else
        MutableStateFlow(
            DetailsViewModelState(
                meal,
                isFavorite = isFavorite,
                isLoading = isLoading
            )
        )

    val uiState =
        viewModelState.stateIn(viewModelScope, SharingStarted.Eagerly, viewModelState.value)

    init {
        mealApiRepo = MealApiClient.mealApiService
        onRefresh()
    }

    fun onRefresh() {
        viewModelScope.launch {
            viewModelState.update { it.copy(isLoading = true) }
            try {
                val response = withContext(Dispatchers.IO) {
                    mealApiRepo.getMealsByName(uiState.value.meal?.name.toString())
                }

                val meal = response.body()?.meals?.first()

                if (response.isSuccessful)
                    viewModelState.update { it.copy(meal = meal, isLoading = false) }

            } catch (e: Exception) {
                Log.e("Details fetch error", e.message.toString())
            }
        }
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
                val mealData = MealFromApi(
                    idOnApi = resp.idOnApi,
                    name = resp.name,
                    category = resp.category,
                    area = resp.area,
                    cookInstructions = resp.cookInstructions,
                    thumbnailUrl = resp.thumbnailUrl,
                    tags = resp.tags
                )

                viewModelState.update { it.copy(meal = mealData, isLoading = false) }
            } catch (e: Exception) {
                Log.e("Meals fetch error", e.message.toString())
            }
        }
    }

    fun isFavoriteChangeState(meal: MealFromApi) {
        _isFavorite.value = !_isFavorite.value!!

        //  Some Db error, java.lang.IllegalStateException: Room cannot verify the data integrity
        viewModelScope.launch {
            dao.upsertMeal(meal.fromApiToLocal())

//            if (isFavorite.value!!)
//                dao.upsertMeal(meal.fromApiToLocal())
//            else
//                dao.deleteMeal(meal.fromApiToLocal())
        }
    }

    companion object {
        fun provideFactory(
//            meal: LocalFavoriteMeal,
            meal: MealFromApi,
            isFavorite: Boolean,
            isLoading: Boolean,
            dao: MealDao,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return DetailsViewModel(meal, isFavorite, isLoading, dao) as T
            }
        }
    }
}

