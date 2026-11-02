package retail.polemasket.digital.data.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

/**
 * When a booking is allowed to start: not in the past, and only within the
 * hours the consultancy actually takes sessions. Shared by the date picker
 * and time slots (to grey out invalid choices) and `CheckoutViewModel` (to
 * re-validate before writing a booking, the same way email format is
 * re-validated there rather than trusted from the UI alone).
 */
object BookingWindow {
    const val START_HOUR = 8
    const val END_HOUR = 16

    fun isValid(dateTime: LocalDateTime): Boolean {
        return dateTime.isAfter(LocalDateTime.now()) && dateTime.hour in START_HOUR..END_HOUR
    }

    fun isValid(date: LocalDate, hour: Int): Boolean = isValid(LocalDateTime.of(date, LocalTime.of(hour, 0)))

    fun firstValidHour(date: LocalDate): Int? = (START_HOUR..END_HOUR).firstOrNull { isValid(date, it) }

    /** Today while it still has a free slot, otherwise tomorrow. */
    fun firstBookableDate(): LocalDate {
        val today = LocalDate.now()
        return if (firstValidHour(today) != null) today else today.plusDays(1)
    }
}
