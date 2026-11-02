package retail.polemasket.digital.ui.composable.screen.checkout.datetime

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import retail.polemasket.digital.R
import retail.polemasket.digital.data.model.BookingWindow
import retail.polemasket.digital.ui.theme.LocalDimens
import java.time.LocalDate

/**
 * Hour slots (8:00–16:00) shown right on the booking screen. Slots already
 * in the past for the selected date are shown but disabled.
 */
@Composable
fun BookingTimeSlots(
    selectedDate: LocalDate?,
    selectedHour: Int,
    modifier: Modifier = Modifier,
    onHourSelected: (Int) -> Unit,
) {
    val dimens = LocalDimens.current

    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.checkout_date_time_dialog_time_label),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelMedium,
        )

        Spacer(modifier = Modifier.height(dimens.tightSpacing))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
            verticalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
        ) {
            (BookingWindow.START_HOUR..BookingWindow.END_HOUR).forEach { hour ->
                val slotEnabled = selectedDate == null || BookingWindow.isValid(selectedDate, hour)
                val isSelected = selectedHour == hour

                FilterChip(
                    selected = isSelected,
                    onClick = { onHourSelected(hour) },
                    enabled = slotEnabled,
                    label = {
                        Text(
                            text = "%02d:00".format(hour),
                            style = MaterialTheme.typography.labelSmall,
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                        labelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        enabled = slotEnabled,
                        selected = isSelected,
                        borderColor = MaterialTheme.colorScheme.outlineVariant,
                        selectedBorderColor = MaterialTheme.colorScheme.onPrimary,
                        borderWidth = dimens.controlBorderWidth,
                        selectedBorderWidth = dimens.controlBorderWidth * 2,
                    ),
                )
            }
        }
    }
}
