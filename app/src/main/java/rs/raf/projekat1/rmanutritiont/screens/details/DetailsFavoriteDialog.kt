package rs.raf.projekat1.rmanutritiont.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import rs.raf.projekat1.rmanutritiont.R
import rs.raf.projekat1.rmanutritiont.ui.components.RegularWidthButton
import rs.raf.projekat1.rmanutritiont.ui.components.SimpleButton

@Composable
fun FavoriteDialog(
    isFavorite: Boolean,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
) {
    when (isFavorite) {
        false -> AddToFavorites(onDismiss = onDismiss, onOkClick = onOkClick)
        true -> RemoveFromFavoritesDialog(onOkClick = onOkClick, onDismiss = onDismiss)
    }

}

@Composable
fun RemoveFromFavoritesDialog(onOkClick: () -> Unit, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(modifier = Modifier.padding(20.dp), shape = RoundedCornerShape(10)) {
            Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.Center) {
                Text(
                    text = stringResource(id = R.string.remove_from_favorites_text),
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center,
                )
                OkCancelButtons(onOkClick = onOkClick, onCancelClick = onDismiss)
            }
        }
    }
}

@Composable
fun AddToFavorites(onDismiss: () -> Unit, onOkClick: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(modifier = Modifier.padding(20.dp), shape = RoundedCornerShape(10)) {
            Column(
                modifier = Modifier
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center
            ) {
                //  Pick date
                DatePicker()

                //  Pick category (Breakfast, Lunch, Dinner)
                Text(
                    text = stringResource(id = R.string.pick_category_text),
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center,
                )
                CategoryPicker(onCategoryClick = {})

                OkCancelButtons(
                    onOkClick = {
                        //  + return previous data
                        onOkClick()
                        onDismiss()
                    },
                    onCancelClick = onDismiss
                )
            }
        }
    }
}

@Composable
private fun DatePicker() {
    //todo
}

@Composable
private fun CategoryPicker(onCategoryClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RegularWidthButton(
            onClick = { onCategoryClick() },
            buttonText = stringResource(id = R.string.breakfast),
        )
        RegularWidthButton(
            onClick = { onCategoryClick() },
            buttonText = stringResource(id = R.string.lunch),
        )
        RegularWidthButton(
            onClick = { onCategoryClick() },
            buttonText = stringResource(id = R.string.dinner),
        )
    }
}

@Composable
private fun OkCancelButtons(onOkClick: () -> Unit, onCancelClick: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        SimpleButton(
            onClick = onOkClick,
            buttonText = stringResource(id = R.string.ok_text),
            modifier = Modifier.width(100.dp)
        )
        SimpleButton(
            onClick = onCancelClick,
            buttonText = stringResource(id = R.string.cancel_text),
            modifier = Modifier.width(100.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrevFavDialogFalse() {
    FavoriteDialog(isFavorite = false, onDismiss = {}, onOkClick = {})
}

@Preview(showBackground = true)
@Composable
fun PrevFavDialogTrue() {
    FavoriteDialog(isFavorite = true, onDismiss = {}, onOkClick = {})
}
