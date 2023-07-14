package rs.raf.projekat1.rmanutritiont.screens.details

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import rs.raf.projekat1.rmanutritiont.R
import rs.raf.projekat1.rmanutritiont.ui.components.RegularWidthButton
import rs.raf.projekat1.rmanutritiont.ui.components.SimpleButton
import java.util.Calendar
import java.util.Date

@Composable
fun IsFavoriteDialog(
    isFavorite: Boolean,
//    viewModel: DetailsViewModel,
    onDismiss: () -> Unit,
//    onOkClick: () -> Unit,
    onOkClick: (String, String) -> Unit,
) {

//    var timeOfDayText by remember { mutableStateOf("") }
    when (isFavorite) {
        false -> AddToFavorites(
            onDismiss = onDismiss,
            onOkClick = { dayTime, date ->
//            timeOfDayText = dayTime       //  DEBUG
//                onOkClick(dayTime, date)
                onOkClick(dayTime, date)
            })

        true -> RemoveFromFavoritesDialog(
            onOkClick = { onOkClick("", "") },
//            onOkClick = { onOkClick() },
            onDismiss = onDismiss
        )
    }

}

@Composable
private fun RemoveFromFavoritesDialog(onOkClick: () -> Unit, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(modifier = Modifier.padding(20.dp), shape = RoundedCornerShape(10)) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
private fun AddToFavorites(
    onDismiss: () -> Unit,
//    date: (String) -> Unit,
//    dayTime: (String) -> Unit,
    onOkClick: (String, String) -> Unit        //  HMMMMMMM
//    onOkClick: () -> Unit        //  HMMMMMMM
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(modifier = Modifier.padding(20.dp), shape = RoundedCornerShape(10)) {
            Column(
                modifier = Modifier
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center
            ) {
                var dayTimeText by remember { mutableStateOf("") }
                var dateString by remember { mutableStateOf("") }
                //  Pick date
                DatePicker(onDatePickClick = { dateString = it })

                //  Pick category (Breakfast, Lunch, Dinner)
                Text(
                    text = stringResource(id = R.string.pick_category_text),
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center,
                )
                CategoryPicker(onCategoryClick = { dayTimeText = it })

                OkCancelButtons(
                    onOkClick = {
//                        Log.e("Djura", "DayTime ->  $dayTimeText    |   Date    ->  $dateString")
                        onOkClick(dayTimeText, dateString)
//                        onOkClick()
                        onDismiss()
                    },
                    onCancelClick = onDismiss
                )
            }
        }
    }
}

@Composable
private fun DatePicker(onDatePickClick: (String) -> Unit) {
    val placeholder = stringResource(id = R.string.pick_date)
    var dateText by remember { mutableStateOf("/") }
    val context = LocalContext.current

    val mCalendar = Calendar.getInstance()
    val year = mCalendar.get(Calendar.YEAR)
    val month = mCalendar.get(Calendar.MONTH)
    val day = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            dateText = "$mDayOfMonth/${mMonth + 1}/$mYear"
        },
        year, month, day
    )

    SimpleButton(
        onClick = {
            datePickerDialog.show()
            onDatePickClick(dateText)
        },
        buttonText = if (dateText != "/") dateText else placeholder,
        modifier = Modifier
            .padding(bottom = 32.dp)
            .fillMaxWidth()
    )
}

@Composable
private fun CategoryPicker(onCategoryClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val breakfastTxt = stringResource(id = R.string.lunch)
        RegularWidthButton(
            onClick = { onCategoryClick(breakfastTxt) },
            buttonText = stringResource(id = R.string.breakfast),
        )
        val lunchTxt = stringResource(id = R.string.lunch)
        RegularWidthButton(
            onClick = { onCategoryClick(lunchTxt) },
            buttonText = stringResource(id = R.string.lunch),
        )
        val dinnerTxt = stringResource(id = R.string.dinner)
        RegularWidthButton(
            onClick = { onCategoryClick(dinnerTxt) },
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

//@Preview(showBackground = true)
//@Composable
//fun PrevFavDialogFalse() {
//    IsFavoriteDialog(isFavorite = false, onDismiss = {}, onOkClick = { _, _ -> })
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PrevFavDialogTrue() {
//    IsFavoriteDialog(isFavorite = true, viewModel = DetailsViewModel(null, dao = ), onDismiss = {}, onOkClick = { _, _ -> })
//}
