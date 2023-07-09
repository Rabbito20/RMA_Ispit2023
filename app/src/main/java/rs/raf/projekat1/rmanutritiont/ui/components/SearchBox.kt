package rs.raf.projekat1.rmanutritiont.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import rs.raf.projekat1.rmanutritiont.R

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchBox(
    modifier: Modifier = Modifier,
    hint: String = "",
    onNewQuery: (String) -> Unit,
    keyboardActions: () -> Unit = {}
) {
    val placeholderSource = stringResource(id = R.string.search)
    val placeholderText by remember { mutableStateOf(placeholderSource) }
    var searchParameter by remember { mutableStateOf(hint) }

    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = searchParameter.trim('\n'),
        onValueChange = { query ->
            searchParameter = query
            onNewQuery(searchParameter)
        },
        placeholder = {
            Text(text = placeholderText, color = MaterialTheme.colorScheme.secondary)
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search icon")
        },
        singleLine = true,
        textStyle = TextStyle(textAlign = TextAlign.Start),
        shape = RoundedCornerShape(percent = 50),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            keyboardController?.hide()
            keyboardActions()
        }),
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    )
}
