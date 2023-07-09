package rs.raf.projekat1.rmanutritiont.data.model

data class CategoryInFeed(
    val categoryFromApi: CategoryFromApi
)

data class CategoriesFeed(
    val categories: List<CategoryFromApi>
)
