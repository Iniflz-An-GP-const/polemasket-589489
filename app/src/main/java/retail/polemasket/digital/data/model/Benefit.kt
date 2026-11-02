package retail.polemasket.digital.data.model

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * [texts] maps a two-letter language code ("en", "de", ...) to the benefit text.
 * The "en" entry is mandatory: it is the fallback.
 */
data class Benefit(
    val icon: ImageVector,
    val texts: Map<String, String>,
) {
    val text: String get() = texts.forCurrentLocale()
}

val defaultServiceBenefits: List<Benefit> = listOf(

)
