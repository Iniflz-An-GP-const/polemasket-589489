package retail.polemasket.digital.data.model

data class ServiceText(
    val name: String,
    val description: String,

    // Briefly describe what the service includes - short phrases, not paragraphs.
    val includedItems: List<String> = emptyList(),
)

/**
 * [texts] maps a two-letter language code ("en", "de", ...) to the service text.
 * The "en" entry is mandatory: it is the fallback and the source of the service
 * name used to look up the photo.
 */
data class ServiceModel(
    val id: Int,
    val texts: Map<String, ServiceText>,
    val serviceCategory: ServiceCategory,
    val pricePerHour: Double,
    val imageUrl: String,
) {
    val name: String get() = texts.forCurrentLocale().name
    val description: String get() = texts.forCurrentLocale().description
    val includedItems: List<String> get() = texts.forCurrentLocale().includedItems
}
