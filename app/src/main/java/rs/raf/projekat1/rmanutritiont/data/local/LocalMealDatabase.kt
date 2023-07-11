package rs.raf.projekat1.rmanutritiont.data.local

import android.util.Log
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LocalFavoriteMeal::class],
    version = 1
)
abstract class LocalMealDatabase: RoomDatabase() {

//    abstract val dao: MealDao

    abstract fun mealDao() : MealDao

    fun printDbLogcat() {
        val mealDao = mealDao()

        val meals = mealDao.getAllMealsLocal()
        meals.forEach{
            Log.e("Db Log", "${it.id} -> ${it.mealApi?.name.toString()}")
        }
    }

}
