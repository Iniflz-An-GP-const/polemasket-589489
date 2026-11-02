package retail.polemasket.digital.ui.composable.screen.servicedetails.image

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import retail.polemasket.digital.ui.composable.screen.shared.ServiceImage
import retail.polemasket.digital.ui.theme.LocalDimens
import retail.polemasket.digital.ui.theme.cardBorderStroke
import retail.polemasket.digital.ui.theme.imageBorder

private const val ImageAspectRatio = 4f / 3f

@Composable
fun ServiceDetailsImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val innerShape = RoundedCornerShape(dimens.cornerRadius)

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(dimens.cardCornerRadius),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        ),
        border = cardBorderStroke(),
    ) {
        ServiceImage(
            imageUrl = imageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimens.cardPadding)
                .aspectRatio(ImageAspectRatio)
                .clip(innerShape)
                .imageBorder(innerShape),
        )
    }
}
