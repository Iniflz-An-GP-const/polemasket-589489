package retail.polemasket.digital.ui.composable.screen.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import retail.polemasket.digital.ui.composable.approot.LocalContentBottomPadding
import retail.polemasket.digital.ui.composable.screen.settings.card.AppInfoCard
import retail.polemasket.digital.ui.theme.LocalDimens

@Composable
fun RLADLSettingsScreen(modifier: Modifier = Modifier) {
    val dimens = LocalDimens.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimens.screenPadding)
            .padding(bottom = LocalContentBottomPadding.current),
    ) {
        AppInfoCard()
    }
}
