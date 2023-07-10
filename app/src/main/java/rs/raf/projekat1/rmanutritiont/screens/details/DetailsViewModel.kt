package rs.raf.projekat1.rmanutritiont.screens.details

//  TODO: This VM from scratch

/*
data class DetailsViewModelState(
    val mealId: Int = 0,
    val mealName: String = "",
    val mealArea: String = "",
    val mealCategory: String = "",
    val mealInstruct: String = "",
    val mealThumb: String = "",
    val mealTags: String = "",
)
*/

/*
class DetailsViewModel(
    private val dao: MealDao
) : ViewModel() {
    private val _state = MutableStateFlow(DetailsViewModelState())

    fun onEvent(event: MealEvent) {
        when (event) {
            is MealEvent.DeleteMeal -> {
                viewModelScope.launch {
                    dao.deleteMeal(event.meal)
                }
            }

            MealEvent.SaveMeal -> {
                val id = _state.value.mealId
                val name = _state.value.mealName
                val area = _state.value.mealName
                val category = _state.value.mealCategory
                val instruct = _state.value.mealInstruct
                val thumb = _state.value.mealThumb
                val tags = _state.value.mealTags

                if (name.isBlank() || area.isBlank() || category.isBlank() || instruct.isBlank() || thumb.isBlank())
                    return

                val meal = LocalMeal(
                    id = id,
                    name = name,
                    area = area,
                    category = category,
                    cookInstructions = instruct,
                    thumbnailUrl = thumb,
                    tags = tags
                )

                _state.update {
                    it.copy()
                }

            }

            is MealEvent.SetMealArea -> TODO()
            is MealEvent.SetMealCategory -> TODO()
            is MealEvent.SetMealInstruct -> TODO()
            is MealEvent.SetMealName -> TODO()
            is MealEvent.SetMealTags -> TODO()
            is MealEvent.SetMealThumb -> TODO()

            is MealEvent.SortMeals -> TODO()
//            MealEvent.HideDialog -> TODO()
//            MealEvent.ShowDialog -> TODO()
        }
    }
}
*/
