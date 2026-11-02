package retail.polemasket.digital.ui.composable.screen.checkout.datetime

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import retail.polemasket.digital.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

private val displayFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH)

/**
 * The whole field acts as a button: a transparent overlay takes every tap,
 * so the text field never gains focus and the soft keyboard never opens.
 */
@Composable
fun BookingDateField(
    selectedDate: LocalDate?,
    modifier: Modifier = Modifier,
    onDateSelected: (LocalDate) -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedDate?.format(displayFormatter).orEmpty(),
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            label = {
                Text(
                    text = stringResource(R.string.checkout_text_field_date_time),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.titleSmall,
                )
            },
            placeholder = {
                Text(text = stringResource(R.string.checkout_date_time_placeholder))
            },
            trailingIcon = {
                Icon(imageVector = Icons.Outlined.CalendarMonth, contentDescription = null)
            },
            singleLine = true,
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable { showDialog = true },
        )
    }

    if (showDialog) {
        BookingDatePickerDialog(
            initialDate = selectedDate,
            onConfirm = { date ->
                showDialog = false
                onDateSelected(date)
            },
            onDismiss = { showDialog = false },
        )
    }
}
