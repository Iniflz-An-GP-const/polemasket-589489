package retail.polemasket.digital.ui.viewmodel

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import retail.polemasket.digital.data.entity.BookingEntity
import retail.polemasket.digital.data.model.BookingWindow
import retail.polemasket.digital.data.repository.BookingRepository
import retail.polemasket.digital.ui.state.DataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class CheckoutViewModel(
    private val bookingRepository: BookingRepository,
) : ViewModel() {
    private val _bookingState = MutableStateFlow<DataUiState<BookingEntity>>(DataUiState.Initial)
    val orderState: StateFlow<DataUiState<BookingEntity>>
        get() = _bookingState.asStateFlow()

    private val _emailInvalidState = MutableStateFlow(false)
    val emailInvalidState: StateFlow<Boolean>
        get() = _emailInvalidState.asStateFlow()

    var customerFirstName by mutableStateOf("")
        private set

    var customerLastName by mutableStateOf("")
        private set

    var customerEmail by mutableStateOf("")
        private set

    // Nullable, not defaulted to "today": the user must pick the date, same
    // as the empty-string fields above. The hour always has a slot selected.
    var customerDate by mutableStateOf<LocalDate?>(null)
        private set

    var customerHour by mutableIntStateOf(BookingWindow.START_HOUR)
        private set

    val customerDateTime: LocalDateTime?
        get() = customerDate?.let { LocalDateTime.of(it, LocalTime.of(customerHour, 0)) }

    fun updateCustomerFirstName(input: String) {
        customerFirstName = input
    }

    fun updateCustomerLastName(input: String) {
        customerLastName = input
    }

    fun updateCustomerEmail(input: String) {
        customerEmail = input
        if (_emailInvalidState.value) {
            _emailInvalidState.value = false
        }
    }

    fun updateCustomerDate(date: LocalDate) {
        customerDate = date
        // Keep the selected slot if it is still free on the new date,
        // otherwise move to the first free one.
        if (!BookingWindow.isValid(date, customerHour)) {
            BookingWindow.firstValidHour(date)?.let { customerHour = it }
        }
    }

    fun updateCustomerHour(hour: Int) {
        customerHour = hour
    }

    fun placeBooking(serviceId: Int) {
        viewModelScope.launch {
            val dateTime = customerDateTime
            if (isEmailValid() && dateTime != null && BookingWindow.isValid(dateTime)) {
                _emailInvalidState.update { false }
                val booking = BookingEntity(
                    serviceId = serviceId,
                    bookingNumber = generateBookingsNumber(),
                    customerFirstName = customerFirstName,
                    customerLastName = customerLastName,
                    customerEmail = customerEmail,
                    timestamp = dateTime,
                )

                bookingRepository.save(booking)
                _bookingState.update { DataUiState.Populated(data = booking) }
            } else if (!isEmailValid()) {
                _emailInvalidState.update { true }
            }
        }
    }

    private fun isEmailValid(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(customerEmail).matches()
    }

    private fun generateBookingsNumber(): String {
        val chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
        return (1..8)
            .map { chars.random() }
            .joinToString("")
    }
}