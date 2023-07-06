package rs.raf.projekat1.rmanutritiont.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import rs.raf.projekat1.rmanutritiont.data.utils.Constants

/*
object RetrofitInstance {
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val mealDbApi: MealDbApiService by lazy {
        retrofit.create(MealDbApiService::class.java)
    }
}
*/
