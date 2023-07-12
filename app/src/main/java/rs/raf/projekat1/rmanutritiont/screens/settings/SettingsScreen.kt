package rs.raf.projekat1.rmanutritiont.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import rs.raf.projekat1.rmanutritiont.R
import rs.raf.projekat1.rmanutritiont.ui.components.RegularWidthButton

@Composable
fun SettingsScreen(
    onCreatePlanClick: () -> Unit,
    onLogOutClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(id = R.string.settings_title),
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 20.dp),
            fontSize = 32.sp
        )

        Spacer(modifier = Modifier.height(96.dp))
        RegularWidthButton(
            onClick = onCreatePlanClick,
            buttonText = stringResource(id = R.string.create_plan),
            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
        )
        Spacer(modifier = Modifier.height(96.dp))

        Text(
            text = stringResource(id = R.string.user),
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 20.dp),
            fontSize = 20.sp
        )

        Button(
            onClick = onLogOutClick,
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Text(text = stringResource(id = R.string.btn_logout))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsPrev() {
    SettingsScreen(onCreatePlanClick = {}, onLogOutClick = {})
}
