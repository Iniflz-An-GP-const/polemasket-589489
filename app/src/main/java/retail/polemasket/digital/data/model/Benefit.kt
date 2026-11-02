package retail.polemasket.digital.data.model

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FactCheck
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material.icons.outlined.Verified

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
    Benefit(
        icon = Icons.Outlined.Verified,
        texts = mapOf(
            "en" to "Engineer-reviewed quality", "de" to "Ingenieurgeprüfte Qualität",
            "es" to "Calidad revisada por ingenieros", "fr" to "Qualité vérifiée par un ingénieur",
            "hi" to "इंजीनियर-समीक्षित गुणवत्ता", "id" to "Mutu ditinjau insinyur",
            "pt" to "Qualidade revista por engenheiros", "ar" to "جودة بمراجعة مهندس",
        ),
    ),
    Benefit(
        icon = Icons.Outlined.FactCheck,
        texts = mapOf(
            "en" to "Documented deliverables", "de" to "Dokumentierte Ergebnisse",
            "es" to "Entregables documentados", "fr" to "Livrables documentés",
            "hi" to "दस्तावेज़ित डिलिवरेबल", "id" to "Hasil kerja terdokumentasi",
            "pt" to "Entregáveis documentados", "ar" to "مخرجات موثقة",
        ),
    ),
    Benefit(
        icon = Icons.Outlined.SupportAgent,
        texts = mapOf(
            "en" to "Post-review support", "de" to "Support nach der Prüfung",
            "es" to "Soporte tras la revisión", "fr" to "Suivi après révision",
            "hi" to "समीक्षा के बाद सहायता", "id" to "Dukungan pascatinjauan",
            "pt" to "Apoio após a revisão", "ar" to "دعم بعد المراجعة",
        ),
    ),
)
