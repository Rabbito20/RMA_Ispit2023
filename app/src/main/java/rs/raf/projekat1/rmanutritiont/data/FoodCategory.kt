package rs.raf.projekat1.rmanutritiont.data

data class FoodCategory(
    val categoryName: String,
    val categoryImage: String = "",      //  Remove default later
    val categoryDescription: String = "Category description etc."
)
