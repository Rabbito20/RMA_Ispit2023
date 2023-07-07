package rs.raf.projekat1.rmanutritiont.screens

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import rs.raf.projekat1.rmanutritiont.R
import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi
import rs.raf.projekat1.rmanutritiont.ui.theme.BlueOp85
import rs.raf.projekat1.rmanutritiont.ui.theme.ColorFavorite

@Composable
fun MealScreenDetails(meal: MealFromApi?, onFavoriteClicked: (MealFromApi) -> Unit = {}) {
    val toastContext = LocalContext.current
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 0.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        var favoriteMeal by remember { mutableStateOf(false) }

        MealHeader(
            mealThumbnailUrl = meal?.thumbnailUrl!!,
            onFavoriteClick = {
                favoriteMeal = !favoriteMeal
                onFavoriteClicked(meal)

                Toast.makeText(toastContext, "Favorite is $favoriteMeal", Toast.LENGTH_SHORT)
                    .show()
            },
            favoriteMeal = favoriteMeal
        )
        MealTitle(name = meal.name!!)
        MealCategories(mealCategory = meal.category!!)

        Text(
            text = meal.area!!,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 4.dp, end = 20.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )

        Divider(
            color = MaterialTheme.colorScheme.onBackground,
            thickness = 2.dp,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
        )

        Text(
            text = meal.cookInstructions!!,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 8.dp, end = 20.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        if (meal.tags != null && meal.tags != "")
            TagsComposable(tags = meal.tags)
    }
}

@Composable
fun MealTitle(name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 16.dp, end = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            modifier = Modifier,
            style = MaterialTheme.typography.titleLarge
        )

    }
}

@Composable
private fun MealCategories(mealCategory: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 16.dp, end = 20.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "${mealCategory!!}\t\t", fontSize = 12.sp, fontWeight = FontWeight.Bold)
//        listOfCategories.forEach { category ->
//        }
    }
}

@Composable
private fun MealHeader(
    mealThumbnailUrl: String,
    onFavoriteClick: () -> Unit,
    favoriteMeal: Boolean
) {
    val favColor = if (favoriteMeal) ColorFavorite else Color.White
    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = mealThumbnailUrl,
            contentDescription = "Thumbnail image",
            contentScale = ContentScale.FillWidth,
            filterQuality = FilterQuality.Low,
            placeholder = painterResource(id = R.drawable.ic_meal_placeholder_48),
            modifier = Modifier.fillMaxSize()
        )

        IconButton(
            onClick = onFavoriteClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
                .size(64.dp)
                .clip(RoundedCornerShape(50))
                .background(BlueOp85)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_favorites_24),
                contentDescription = "",
                tint = favColor,
                modifier = Modifier
                    .size(48.dp)
            )
        }
    }
}

@Composable
private fun TagsComposable(tags: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${stringResource(id = R.string.tags)}\t\t",
            modifier = Modifier,
            textAlign = TextAlign.Start
        )

        Text(
            text = tags!!,
            modifier = Modifier.padding(start = 4.dp, end = 4.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PrevMealScreen() {
    MealHeader(mealThumbnailUrl = "Title", onFavoriteClick = { /*TODO*/ }, favoriteMeal = false)
//    TagsComposable(tagList = "Soup,SideDish,Lunch")
}
