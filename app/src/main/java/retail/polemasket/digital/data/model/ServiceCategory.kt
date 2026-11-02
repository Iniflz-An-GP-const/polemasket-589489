package retail.polemasket.digital.data.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountTree
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.ElectricalServices
import androidx.compose.material.icons.outlined.PrecisionManufacturing
import androidx.compose.material.icons.outlined.Public
import retail.polemasket.digital.R

enum class ServiceCategory(
    @field:StringRes val titleRes: Int,
    val icon: ImageVector,
) {
    STRUCTURAL(R.string.service_category_structural_title, Icons.Outlined.Apartment),
    MECHANICAL(R.string.service_category_mechanical_title, Icons.Outlined.PrecisionManufacturing),
    ELECTRICAL(R.string.service_category_electrical_title, Icons.Outlined.ElectricalServices),
    CIVIL_SITE(R.string.service_category_civil_site_title, Icons.Outlined.Public),
    PROCESS_SAFETY(R.string.service_category_process_safety_title, Icons.Outlined.AccountTree),
}
