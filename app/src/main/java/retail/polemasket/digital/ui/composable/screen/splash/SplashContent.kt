package retail.polemasket.digital.ui.composable.screen.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import retail.polemasket.digital.R
import retail.polemasket.digital.ui.theme.LocalDimens
import kotlinx.coroutines.delay

private const val LOGO_ANIMATION_DURATION_MS = 450
private const val TEXT_ANIMATION_DURATION_MS = 200
private const val RULE_ANIMATION_DURATION_MS = 200

private const val STAGGER_DELAY_MS = 50L

// Total splash time: 450 + 50 + 200 + 50 + 200 + 50 + 200 = 1200 ms.

private val LogoSize = 132.dp
private val LogoCornerRadius = 36.dp
private val SplashCornerRadius = 48.dp
private val TextRise = 16.dp
private val RuleWidth = 72.dp
private val RuleHeight = 3.dp

@Composable
fun SplashContent(
    modifier: Modifier = Modifier,
    onAnimationFinished: () -> Unit,
) {
    val dimens = LocalDimens.current

    val logo = remember { Animatable(0f) }
    val title = remember { Animatable(0f) }
    val rule = remember { Animatable(0f) }
    val tagline = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        logo.animateTo(
            1f,
            animationSpec = tween(LOGO_ANIMATION_DURATION_MS, easing = LinearOutSlowInEasing)
        )

        delay(STAGGER_DELAY_MS)
        title.animateTo(1f, animationSpec = tween(TEXT_ANIMATION_DURATION_MS))

        delay(STAGGER_DELAY_MS)
        rule.animateTo(
            1f,
            animationSpec = tween(RULE_ANIMATION_DURATION_MS, easing = FastOutSlowInEasing)
        )

        delay(STAGGER_DELAY_MS)
        tagline.animateTo(1f, animationSpec = tween(TEXT_ANIMATION_DURATION_MS))

        onAnimationFinished()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(SplashCornerRadius))
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.icon),
                contentDescription = stringResource(R.string.rladl_app_logo_description),
                modifier = Modifier
                    .size(LogoSize)
                    .clip(RoundedCornerShape(LogoCornerRadius))
                    .graphicsLayer {
                        alpha = logo.value
                        val logoScale = 0.9f + 0.1f * logo.value
                        scaleX = logoScale
                        scaleY = logoScale
                    },
            )

            Spacer(modifier = Modifier.height(dimens.sectionGap))

            Text(
                text = stringResource(R.string.app_name),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.graphicsLayer {
                    alpha = title.value
                    translationY = (1f - title.value) * TextRise.toPx()
                },
            )

            Spacer(modifier = Modifier.height(dimens.contentSpacing))

            Box(
                modifier = Modifier
                    .width(RuleWidth * rule.value)
                    .height(RuleHeight)
                    .clip(RoundedCornerShape(RuleHeight))
                    .background(MaterialTheme.colorScheme.primary),
            )

            Spacer(modifier = Modifier.height(dimens.contentSpacing))

            Text(
                text = stringResource(R.string.splash_screen_tagline),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = dimens.screenPadding)
                    .graphicsLayer {
                        alpha = tagline.value
                        translationY = (1f - tagline.value) * TextRise.toPx()
                    },
            )
        }
    }
}
