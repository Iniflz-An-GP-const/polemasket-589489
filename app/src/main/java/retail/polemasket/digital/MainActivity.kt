package retail.polemasket.digital

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import retail.polemasket.digital.ui.composable.approot.AppRoot
import retail.polemasket.digital.ui.theme.RLADLAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RLADLAppTheme {
                AppRoot()
            }
        }
    }
}