package rs.raf.projekat1.rmanutritiont.screens.home.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import rs.raf.projekat1.rmanutritiont.R
import rs.raf.projekat1.rmanutritiont.ui.components.RegularWidthButton

@Composable
fun SortDialog(
    closeDialog: () -> Unit,
    onSortByNameAlphabetClick: () -> Unit,
    onSortByNameClick: () -> Unit,
    onSortByTagsClick: () -> Unit,
) {
    Dialog(onDismissRequest = closeDialog) {
        Card(modifier = Modifier.padding(20.dp), shape = RoundedCornerShape(10)) {
            //  Sort po abecedi, nazivu, tagovima
            Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.Center) {
                Text(
                    text = stringResource(id = R.string.sort_text),
                    modifier = Modifier.fillMaxWidth()
                )

                RegularWidthButton(
                    onClick = {
                        onSortByNameAlphabetClick()
                        closeDialog()
                    },
                    buttonText = stringResource(id = R.string.sort_alphabetical)
                )
                RegularWidthButton(
                    onClick = {
                        onSortByNameClick()
                        closeDialog()
                    },
                    buttonText = stringResource(id = R.string.sort_by_name)
                )
                RegularWidthButton(
                    onClick = {
                        onSortByTagsClick()
                        closeDialog()
                    },
                    buttonText = stringResource(id = R.string.sort_by_tag)
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDialog() {
    SortDialog(
        onSortByTagsClick = {},
        onSortByNameClick = {},
        onSortByNameAlphabetClick = {},
        closeDialog = {})
}
