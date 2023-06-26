package rs.raf.projekat1.rmanutritiont.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import rs.raf.projekat1.rmanutritiont.data.FoodCategory

data class HomeUiState(
    val isLoading: Boolean,
    val searchInputString: String,
    val categoryList: List<FoodCategory>,
)

private data class HomeViewModelState(
    val isLoading: Boolean,
    val searchInputString: String = "",
    val categoryList: List<FoodCategory> = emptyList(),
) {
    //  Converts this ViewModelState to a UiState.

    fun toUiState(): HomeUiState = HomeUiState(
        isLoading = isLoading,
        searchInputString = searchInputString,
        categoryList = categoryList
    )
}

//  Contains companion object and provides a factory method
class HomeViewModel(
    categoryListRepo: List<FoodCategory>,
) : ViewModel() {
    private val searchInput = MutableStateFlow("")

//    private val viewModelState: StateFlow<HomeViewModelState> = categoryListRepo   //  todo: get list elements

//    val uiState = viewModelState.map(HomeViewModelState::toUiState)

    fun onSearchInputChanged(searchInput: String) {
        this.searchInput.tryEmit(searchInput.trim())
    }

    companion object {
        fun provideFactory(
            categoryListRepo: List<FoodCategory>
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(categoryListRepo) as T
            }
        }
    }
}
