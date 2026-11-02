package retail.polemasket.digital.ui.composable.screen.home.slider

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import retail.polemasket.digital.R
import retail.polemasket.digital.data.model.ServiceModel
import retail.polemasket.digital.ui.composable.screen.shared.ServiceImage
import retail.polemasket.digital.ui.theme.LocalDimens
import retail.polemasket.digital.ui.theme.cardBorderStroke
import retail.polemasket.digital.ui.theme.controlBorderStroke

@Composable
fun FeaturedSlider(
    services: List<ServiceModel>,
    modifier: Modifier = Modifier,
    onServiceClick: (serviceId: Int) -> Unit,
) {
    val pagerState = rememberPagerState { services.size }
    val dimens = LocalDimens.current

    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = dimens.screenPadding),
        pageSpacing = dimens.contentSpacing,
    ) { page ->
        val service = services[page]

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clickable { onServiceClick(service.id) },
            shape = RoundedCornerShape(dimens.cardCornerRadius),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            ),
            border = cardBorderStroke(),
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                ServiceImage(
                    imageUrl = service.imageUrl,
                    modifier = Modifier.matchParentSize(),
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(MaterialTheme.colorScheme.scrim.copy(alpha = 0.35f))
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Surface(
                        shape = RoundedCornerShape(dimens.controlCornerRadius),
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        border = controlBorderStroke(color = MaterialTheme.colorScheme.onSecondaryContainer),
                    ) {
                        Text(
                            text = stringResource(service.serviceCategory.titleRes),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }

                    Spacer(modifier = Modifier.height(dimens.itemSpacing))

                    Text(
                        text = service.name,
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Spacer(modifier = Modifier.height(dimens.tightSpacing))

                    Text(
                        text = stringResource(R.string.price_per_hour, service.pricePerHour),
                        color = Color.White,
                        style = MaterialTheme.typography.titleSmall,
                    )
                }

                PagerIndicator(
                    pageCount = services.size,
                    currentPage = pagerState.currentPage,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = dimens.cardPadding),
                )
            }
        }
    }
}

@Composable
private fun PagerIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(pageCount) { page ->
            val isCurrent = page == currentPage

            Box(
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .size(if (isCurrent) 8.dp else 6.dp)
                    .clip(CircleShape)
                    .background(
                        color = if (isCurrent) Color.White else Color.White.copy(alpha = 0.5f)
                    )
            )
        }
    }
}
