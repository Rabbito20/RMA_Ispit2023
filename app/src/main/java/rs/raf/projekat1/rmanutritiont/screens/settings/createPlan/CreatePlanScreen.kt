package rs.raf.projekat1.rmanutritiont.screens.settings.createPlan

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import rs.raf.projekat1.rmanutritiont.R
import rs.raf.projekat1.rmanutritiont.ui.components.RegularWidthButton
import rs.raf.projekat1.rmanutritiont.ui.components.SimpleButton
import java.util.Calendar
import java.util.Date

@Composable
fun CreatePlanScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 4.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.create_plan),
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )

        FormWeeklyComposable()

        SendMailForm()
    }
}

@Composable
private fun FormWeeklyComposable() {
    FormDayComposable(
        dayText = "${stringResource(id = R.string.day_text)} 1",
        onButtonClick = {}
    )
    FormDayComposable(
        dayText = "${stringResource(id = R.string.day_text)} 2",
        onButtonClick = {}
    )
    FormDayComposable(
        dayText = "${stringResource(id = R.string.day_text)} 3",
        onButtonClick = {}
    )
    FormDayComposable(
        dayText = "${stringResource(id = R.string.day_text)} 4",
        onButtonClick = {}
    )
    FormDayComposable(
        dayText = "${stringResource(id = R.string.day_text)} 5",
        onButtonClick = {}
    )
    FormDayComposable(
        dayText = "${stringResource(id = R.string.day_text)} 6",
        onButtonClick = {}
    )
    FormDayComposable(
        dayText = "${stringResource(id = R.string.day_text)} 7",
        onButtonClick = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FormDayComposable(
    dayText: String = "",
    onButtonClick: () -> Unit = {},
//    mealList: List<Meal> = emptyList(),
) {
    val mealsText by remember { mutableStateOf("") }
    var dateText by remember { mutableStateOf("") }

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

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SimpleButton(
            onClick = {
                onButtonClick()

                datePickerDialog.show()

            },
            buttonText = dayText,
            modifier = Modifier
                .padding(end = 4.dp)
                .width(100.dp)
        )

        //  TODO:   Kreiraj string u API-ju i prosledi ga kao tekst

        TextField(
            value = mealsText,
            onValueChange = {},
            shape = RoundedCornerShape(25),
            enabled = false,
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )

        //  DEBUG
        if (dateText != "") {
            Toast.makeText(
                context,
                "Creating plan for the meal on $dateText",
                Toast.LENGTH_SHORT
            ).show()

            Log.e("Djura", "Meal for date -> $dateText")
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendMailForm() {
    val toastContext = LocalContext.current
    var email by remember { mutableStateOf("") }

    Spacer(modifier = Modifier.padding(top = 20.dp))

    OutlinedTextField(
        value = email,
        onValueChange = { email = it },
        placeholder = { Text(text = "email@gmail.com") },
        textStyle = TextStyle(textAlign = TextAlign.Start),
        modifier = Modifier.fillMaxWidth()
    )

    RegularWidthButton(
        onClick = {
            Toast.makeText(
                toastContext,
                "Connecting...\nSending email...",
                Toast.LENGTH_SHORT
            ).show()
        },
        buttonText = stringResource(id = R.string.send_email_btn)
    )
}

/*@Preview(showBackground = true)
@Composable
fun CreatePlanPrev() {
    CreatePlanScreen()
//    FormDayComposable(dayText = "Monday")
}*/
