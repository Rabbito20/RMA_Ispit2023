package rs.raf.projekat1.rmanutritiont.data.model

//  Ovo koristimo za staticke podatke
//  Obrisati kasnije
data class FoodCategory(
    val cateoryList: List<Meal> = emptyList(),
    val categoryName: String,
    val categoryImage: String = "",      //  Remove default later
    val categoryDescription: String = "Category description etc."
)

//  Za podatke sa API-ja
data class MealCategory(
    val idCategory: Int,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String,
)
