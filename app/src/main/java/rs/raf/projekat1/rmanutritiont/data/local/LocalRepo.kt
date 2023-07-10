package rs.raf.projekat1.rmanutritiont.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi

@Entity
data class LocalRepo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val meals: Set<MealFromApi>?,
)

@Entity
data class LocalMeal(
    @PrimaryKey
    val id: Int,

    val name: String?,
    val area: String?,
    val cookInstructions: String?,
    val category: String?,
    val thumbnailUrl: String?,
    val tags: String?
//    val meal: MealFromApi?,
)

@Entity
data class FavoritesRepo(
    @PrimaryKey
    val id: Int,

//    val userName: String? = null,
    val meals: Set<LocalMeal>?
)

@Dao
interface MealDao {
    /**
     * Insert [meal] in the Database.
     * If there is no meal, new meal will be created, otherwise update the existing meal.
     * */
    @Upsert
    suspend fun upsertMeal(meal: LocalMeal)

    @Delete
    suspend fun deleteMeal(meal: LocalMeal)


    @Query("SELECT * from LocalMeal ORDER BY name ASC")
    fun getLocalMealsByName(): Flow<List<LocalMeal>>

    @Query("SELECT * from LocalMeal ORDER BY tags ASC")
    fun getLocalMealsByTags(): Flow<List<LocalMeal>>

    //  TODO:   getLocalMealsByAlpha(): Flow<List<LocalMeal>>

}
