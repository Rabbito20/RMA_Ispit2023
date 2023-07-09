package rs.raf.projekat1.rmanutritiont.data.local

import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi

data class LocalRepo(
    val meals: List<MealFromApi>?
)

data class FavoritesRepo(
    val meals: List<MealFromApi>?
)

class LocalRepoImpl() {}