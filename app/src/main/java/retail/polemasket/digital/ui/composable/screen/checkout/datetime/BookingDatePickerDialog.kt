package retail.polemasket.digital.ui.composable.screen.checkout.datetime

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import retail.polemasket.digital.R
import retail.polemasket.digital.data.model.BookingWindow
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

/**
 * Dates before today can't be picked; today itself only while it still has
 * a free hour slot (see [BookingWindow]).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingDatePickerDialog(
    initialDate: LocalDate?,
    onConfirm: (LocalDate) -> Unit,
    onDismiss: () -> Unit,
) {
    val firstDate = remember { BookingWindow.firstBookableDate() }

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = (initialDate ?: firstDate)
            .atStartOfDay(ZoneOffset.UTC)
            .toInstant()
            .toEpochMilli(),
        selectableDates = remember {
            object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    val date = Instant.ofEpochMilli(utcTimeMillis).atZone(ZoneOffset.UTC).toLocalDate()
                    return !date.isBefore(firstDate)
                }

                override fun isSelectableYear(year: Int): Boolean = year >= firstDate.year
            }
        },
    )

    val selectedDate = datePickerState.selectedDateMillis?.let { millis ->
        Instant.ofEpochMilli(millis).atZone(ZoneOffset.UTC).toLocalDate()
    }

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = { selectedDate?.let(onConfirm) },
                enabled = selectedDate != null,
            ) {
                Text(
                    text = stringResource(R.string.checkout_date_time_dialog_confirm),
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = stringResource(R.string.checkout_date_time_dialog_cancel),
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        },
    ) {
        DatePicker(
            state = datePickerState,
            showModeToggle = false,
        )
    }
}
