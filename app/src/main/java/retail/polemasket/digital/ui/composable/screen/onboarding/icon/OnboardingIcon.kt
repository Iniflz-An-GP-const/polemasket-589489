package retail.polemasket.digital.ui.composable.screen.onboarding.icon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import retail.polemasket.digital.ui.theme.imageBorder
import kotlin.math.cos
import kotlin.math.sin

private val CompositionSize = 152.dp
private val BackdropSize = 88.dp
private val IconSize = 40.dp
private val DotSize = 6.dp
private val DotRadius = 68.dp

private const val DotCount = 8

@Composable
fun OnboardingIcon(
    icons: List<ImageVector>,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.size(CompositionSize),
        contentAlignment = Alignment.Center,
    ) {
        repeat(DotCount) { index ->
            val angle = Math.toRadians((360.0 / DotCount) * index)
            val offsetX = (DotRadius.value * cos(angle)).toFloat().dp
            val offsetY = (DotRadius.value * sin(angle)).toFloat().dp

            Box(
                modifier = Modifier
                    .offset(x = offsetX, y = offsetY)
                    .size(DotSize)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.outlineVariant),
            )
        }

        Box(
            modifier = Modifier
                .size(BackdropSize)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .imageBorder(CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = icons.first(),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.size(IconSize),
            )
        }
    }
}
