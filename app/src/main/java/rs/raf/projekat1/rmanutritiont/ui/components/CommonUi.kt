package rs.raf.projekat1.rmanutritiont.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import rs.raf.projekat1.rmanutritiont.R
import rs.raf.projekat1.rmanutritiont.data.model.MealFromApi

@Composable
fun RegularWidthButton(
    onClick: () -> Unit,
    buttonText: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .then(modifier)
    ) {
        Text(text = buttonText, textAlign = TextAlign.Center)
    }
}

@Composable
fun SimpleButton(
    onClick: () -> Unit,
    buttonText: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(top = 8.dp)
            .then(modifier)
    ) {
        Text(text = buttonText, textAlign = TextAlign.Center)
    }
}

//  Contains picture, meal name
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleMealCard(modifier: Modifier = Modifier, meal: MealFromApi?, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .then(modifier),
        onClick = onClick
//        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.tertiary),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(84.dp)
            ) {
                AsyncImage(
                    model = meal?.thumbnailUrl.toString(),
                    contentDescription = "Thumbnail image",
                    contentScale = ContentScale.FillBounds,
                    placeholder = painterResource(id = R.drawable.ic_meal_placeholder_48),
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp))
                )
            }
            Text(
                text = meal?.name.toString(),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .padding(start = 12.dp, end = 20.dp)
                    .fillMaxWidth()
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun prevSingleMealCard() {
    SingleMealCard(
        meal = MealFromApi(
            "1",
            "Name of the meal",
            "Category",
            "Area",
            "Instructions",
            "www.url.com",
            "tag1, tag2..."
        ),
        onClick = {}
    )
}
