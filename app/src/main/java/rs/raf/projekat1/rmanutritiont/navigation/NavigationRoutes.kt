package rs.raf.projekat1.rmanutritiont.navigation

enum class TopLevelRoutes() {
    Home,
    Favorites,
    FoodStatistics,
    Settings
}

enum class SecondaryRoutes {
    //  Home
    Filter,
    Category,

    //  Settings
    CreatePlan,

    //  MealDetails
    MealDetails,
    LocalMealDetails,
}
