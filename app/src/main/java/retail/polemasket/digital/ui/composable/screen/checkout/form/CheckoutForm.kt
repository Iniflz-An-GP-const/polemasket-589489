package retail.polemasket.digital.ui.composable.screen.checkout.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import retail.polemasket.digital.R
import retail.polemasket.digital.ui.composable.screen.checkout.datetime.BookingDateTimeField
import retail.polemasket.digital.ui.composable.screen.checkout.field.CheckoutTextField
import retail.polemasket.digital.ui.theme.LocalDimens
import retail.polemasket.digital.ui.theme.cardBorderStroke
import java.time.LocalDateTime

@Composable
fun CheckoutForm(
    customerFirstName: String,
    customerLastName: String,
    customerEmail: String,
    isEmailInvalid: Boolean,
    selectedDateTime: LocalDateTime?,
    modifier: Modifier = Modifier,
    focusManager: FocusManager,
    onFirstNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onDateTimeSelected: (LocalDateTime) -> Unit,
) {
    val dimens = LocalDimens.current

    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.checkout_headline),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineSmall,
        )

        Spacer(modifier = Modifier.height(dimens.tightSpacing))

        Text(
            text = stringResource(R.string.checkout_invitation),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
        )

        Spacer(modifier = Modifier.height(dimens.sectionGap))

        HorizontalDivider(
            thickness = dimens.controlBorderWidth.coerceAtLeast(1.dp),
            color = MaterialTheme.colorScheme.outlineVariant,
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(dimens.cardCornerRadius),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                ),
                border = cardBorderStroke(),
            ) {
                Column(
                    modifier = Modifier.padding(dimens.cardPadding),
                    verticalArrangement = Arrangement.spacedBy(dimens.contentSpacing),
                ) {
                    CheckoutTextField(
                        input = customerFirstName,
                        onInputChange = onFirstNameChanged,
                        labelText = stringResource(R.string.checkout_text_field_first_name),
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = Icons.Outlined.Person,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Words,
                            imeAction = ImeAction.Next,
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                    )

                    CheckoutTextField(
                        input = customerLastName,
                        onInputChange = onLastNameChanged,
                        labelText = stringResource(R.string.checkout_text_field_last_name),
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = Icons.Outlined.Badge,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Words,
                            imeAction = ImeAction.Next,
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                    )

                    CheckoutTextField(
                        input = customerEmail,
                        onInputChange = onEmailChanged,
                        labelText = stringResource(R.string.checkout_text_field_email),
                        modifier = Modifier.fillMaxWidth(),
                        isError = isEmailInvalid,
                        leadingIcon = Icons.Outlined.MailOutline,
                        supportingText = if (isEmailInvalid) {
                            stringResource(R.string.checkout_email_error_text)
                        } else {
                            stringResource(R.string.checkout_email_supporting_text)
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Done,
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { focusManager.clearFocus() }
                        ),
                    )

                    BookingDateTimeField(
                        selectedDateTime = selectedDateTime,
                        modifier = Modifier.fillMaxWidth(),
                        onDateTimeSelected = onDateTimeSelected,
                    )
                }
            }
        }
    }
}
