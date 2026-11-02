package retail.polemasket.digital

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import retail.polemasket.digital.ui.composable.approot.AppRoot
import retail.polemasket.digital.ui.theme.Primary
import retail.polemasket.digital.ui.theme.RLADLAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Primary.toArgb(), Color.Black.toArgb())
        )
        setContent {
            RLADLAppTheme {
                AppRoot()
            }
        }
    }
}
