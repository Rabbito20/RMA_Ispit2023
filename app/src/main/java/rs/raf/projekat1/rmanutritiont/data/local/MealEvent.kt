package rs.raf.projekat1.rmanutritiont.data.local

sealed interface MealEvent {
    object SaveMeal : MealEvent

    data class SetMealName(val mealName: String) : MealEvent
    data class SetMealArea(val mealArea: String) : MealEvent
    data class SetMealCategory(val mealCategory: String) : MealEvent
    data class SetMealInstruct(val mealInstruct: String) : MealEvent
    data class SetMealThumb(val mealThumb: String) : MealEvent
    data class SetMealTags(val mealThumb: String?) : MealEvent

    object ShowDialog : MealEvent
    object HideDialog : MealEvent

    data class SortMeals(val sortType: SortType) : MealEvent
    data class DeleteMeal(val meal: LocalMeal) : MealEvent
}

//  TODO MealState in VM
