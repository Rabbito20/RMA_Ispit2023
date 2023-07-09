package rs.raf.projekat1.rmanutritiont.data.local

import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi

data class LocalRepo(
    val meals: Set<MealFromApi>?
)

data class FavoritesRepo(
    val meals: Set<MealFromApi>?
)

class LocalRepoImpl() {}
