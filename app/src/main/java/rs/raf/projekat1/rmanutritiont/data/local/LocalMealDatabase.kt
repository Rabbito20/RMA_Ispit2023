package rs.raf.projekat1.rmanutritiont.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LocalFavoriteMeal::class],
    version = 1
)
abstract class LocalMealDatabase: RoomDatabase() {

    abstract val dao: MealDao

}
