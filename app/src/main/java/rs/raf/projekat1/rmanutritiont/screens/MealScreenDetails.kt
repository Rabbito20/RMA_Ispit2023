package rs.raf.projekat1.rmanutritiont.screens

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import rs.raf.projekat1.rmanutritiont.R
import rs.raf.projekat1.rmanutritiont.data.model.Meal
import rs.raf.projekat1.rmanutritiont.ui.theme.BlueOp85
import rs.raf.projekat1.rmanutritiont.ui.theme.ColorFavorite

@Composable
fun MealScreenDetails(navController: NavController, meal: Meal?) {
    val toastContext = LocalContext.current
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        var favoriteMeal by remember { mutableStateOf(false) }
        val testCategories = listOf("Street food", "Breakfast")

        MealHeader(meal = meal)
        MealTitle(
            name = meal?.name!!,
            onFavoriteClick = {
                favoriteMeal = !favoriteMeal
                Toast.makeText(toastContext, "Favorite is $favoriteMeal", Toast.LENGTH_SHORT)
                    .show()
            },
            favoriteMeal = favoriteMeal
        )
//        MealCategories(listOfCategories = meal.categories)
        MealCategories(listOfCategories = testCategories)

        Text(
            text = meal.area!!,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 4.dp, end = 20.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )

        Text(
            text = meal.cookInstructions,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 8.dp, end = 20.dp),
            style = MaterialTheme.typography.headlineMedium
        )

//        TagList(tagList = meal.tagList)
        TagList(tagList = listOf("Tag1", "Tag2", "Tag3"))
    }
}

@Composable
fun MealTitle(name: String, onFavoriteClick: () -> Unit, favoriteMeal: Boolean) {
    val favColor = if (favoriteMeal) ColorFavorite else Color.White
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

        IconButton(
            onClick = onFavoriteClick,
            modifier = Modifier
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
private fun MealCategories(listOfCategories: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 16.dp, end = 20.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        listOfCategories.forEach { category ->
            Text(text = "$category\t\t", fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun MealHeader(meal: Meal?) {
    Box(modifier = Modifier.fillMaxWidth()) {
        if (meal?.thumbnailUrl != null) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_favorites_24),      //  Swap for async image later
                contentDescription = "Meal image placeholder",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.ic_meal_placeholder_48),
                contentDescription = "Meal image placeholder",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
private fun TagList(tagList: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${stringResource(id = R.string.tags)}\t\t",
            modifier = Modifier,
            textAlign = TextAlign.Start
        )

        tagList.forEach { tag ->
            Text(
                text = tag,
                modifier = Modifier.padding(end = 4.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PrevMealScreen() {
    MealScreenDetails(
        navController = rememberNavController(),
        meal = Meal(
            1,
            "Naziv obroka",
            "",
            categories = listOf("Street food", "On the go"),
            ingredients = emptyList(),
            area = "Some area",
            tagList = listOf("tag1", "tag2")
        )
    )
}
