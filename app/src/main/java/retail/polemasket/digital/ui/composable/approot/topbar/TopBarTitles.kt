package retail.polemasket.digital.ui.composable.approot.topbar

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import retail.polemasket.digital.R
import retail.polemasket.digital.ui.composable.navigation.NavRoute
import kotlin.reflect.KClass

internal val canNavigateBackRoutes: List<KClass<out NavRoute>> = listOf(
    NavRoute.ServiceDetails::class,
    NavRoute.Checkout::class,
)

internal fun topBarTitleRes(currentDestination: NavDestination?): Int? {
    return when {
        currentDestination == null -> null

        currentDestination.hierarchy.any { it.hasRoute(NavRoute.Home::class) } -> {
            R.string.rladl_top_bar_home_title
        }

        currentDestination.hierarchy.any { it.hasRoute(NavRoute.ServiceDetails::class) } -> {
            R.string.rladl_top_bar_service_details_title
        }

        currentDestination.hierarchy.any { it.hasRoute(NavRoute.Checkout::class) } -> {
            R.string.rladl_top_bar_checkout_title
        }

        currentDestination.hierarchy.any { it.hasRoute(NavRoute.Bookings::class) } -> {
            R.string.rladl_top_bar_bookings_title
        }

        currentDestination.hierarchy.any { it.hasRoute(NavRoute.Settings::class) } -> {
            R.string.rladl_top_bar_settings_title
        }

        else -> null
    }
}
