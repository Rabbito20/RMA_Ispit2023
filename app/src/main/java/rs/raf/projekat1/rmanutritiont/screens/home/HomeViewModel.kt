package rs.raf.projekat1.rmanutritiont.screens.home

/*
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
    private val repository: Repository,
//    categoryListRepo: List<FoodCategory>,
) : ViewModel() {
    private val searchInput = MutableStateFlow("")

    val apiResponse: MutableLiveData<Response<MealCategory>> = MutableLiveData()

//    private val viewModelState: StateFlow<HomeViewModelState> = categoryListRepo   //  todo: get list elements

//    val uiState = viewModelState.map(HomeViewModelState::toUiState)

    fun onSearchInputChanged(searchInput: String) {
        this.searchInput.tryEmit(searchInput.trim())
    }

    fun getCategories() {
        viewModelScope.launch {
            val response = repository.getCategories()
            apiResponse.value = response
        }
    }

    /*companion object {
        fun provideFactory(
//            categoryListRepo: List<FoodCategory>
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel() as T
            }
        }
    }*/
}

class HomeViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}
*/