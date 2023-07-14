package rs.raf.projekat1.rmanutritiont.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.TypeConverter
import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi

@Entity
data class LocalFavoriteMeal(
    @Embedded
    val mealApi: MealFromApi?,

    @PrimaryKey
    val id: String = mealApi?.idOnApi!!,

//    val date: String?,
//    val categoryTime: String?

//    val name: String?,
//    val area: String?,
//    val cookInstructions: String?,
//    val category: String?,
//    val thumbnailUrl: String?,
//    val tags: String?
) {
    @TypeConverter
    fun fromLocalToApi(): MealFromApi = MealFromApi(
        idOnApi = mealApi!!.idOnApi,
        name = mealApi?.name,
        category = mealApi?.category,
        area = mealApi?.area,
        cookInstructions = mealApi?.cookInstructions,
        thumbnailUrl = mealApi?.thumbnailUrl,
        tags = mealApi?.tags
    )
}

@Dao
interface MealDao {
    /**
     * Insert [meal] in the Database.
     * If there is no meal, new meal will be created, otherwise update the existing meal.
     * */

//    @Upsert()
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertMeal(meal: LocalFavoriteMeal)

    @Delete
    suspend fun deleteMeal(meal: LocalFavoriteMeal)

    @Query("SELECT * FROM LocalFavoriteMeal ORDER BY id DESC LIMIT 1")
    suspend fun getLatestMeal(): LocalFavoriteMeal?

    @Query("SELECT * FROM LocalFavoriteMeal")
    suspend fun getAllMealsLocal(): List<LocalFavoriteMeal>

    @Query("SELECT * FROM LocalFavoriteMeal WHERE name LIKE :searchParameter")
    suspend fun getMealsByName(searchParameter: String): List<LocalFavoriteMeal>


//    @Query("SELECT * from LocalMeal ORDER BY name ASC")
//    suspend fun getLocalMealsByName(): Flow<List<LocalMeal>>

//    @Query("SELECT * from LocalMeal ORDER BY tags ASC")
//    suspend fun getLocalMealsByTags(): Flow<List<LocalMeal>>

    //  TODO:   getLocalMealsByAlpha(): Flow<List<LocalMeal>>

}
