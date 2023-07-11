package rs.raf.projekat1.rmanutritiont.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.Upsert
import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi

@Entity
data class LocalFavoriteMeal(
    @Embedded
    val meal: MealFromApi?,

    @PrimaryKey
    val id: Int = meal?.idOnApi!!

//    val name: String?,
//    val area: String?,
//    val cookInstructions: String?,
//    val category: String?,
//    val thumbnailUrl: String?,
//    val tags: String?
) {
    @TypeConverter
    fun fromLocalToApi(): MealFromApi = MealFromApi(
        idOnApi = meal?.idOnApi,
        name = meal?.name,
        category = meal?.category,
        area = meal?.area,
        cookInstructions = meal?.cookInstructions,
        thumbnailUrl = meal?.thumbnailUrl,
        tags = meal?.tags
    )
}

@Dao
interface MealDao {
    /**
     * Insert [meal] in the Database.
     * If there is no meal, new meal will be created, otherwise update the existing meal.
     * */
    @Upsert
    suspend fun upsertMeal(meal: LocalFavoriteMeal)

    @Delete
    suspend fun deleteMeal(meal: LocalFavoriteMeal)


//    @Query("SELECT * from LocalMeal ORDER BY name ASC")
//    suspend fun getLocalMealsByName(): Flow<List<LocalMeal>>

//    @Query("SELECT * from LocalMeal ORDER BY tags ASC")
//    suspend fun getLocalMealsByTags(): Flow<List<LocalMeal>>

    //  TODO:   getLocalMealsByAlpha(): Flow<List<LocalMeal>>

}
