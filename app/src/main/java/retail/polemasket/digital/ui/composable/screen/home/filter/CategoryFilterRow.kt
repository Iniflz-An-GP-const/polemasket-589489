package retail.polemasket.digital.ui.composable.screen.home.filter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import retail.polemasket.digital.R
import retail.polemasket.digital.data.model.ServiceCategory
import retail.polemasket.digital.ui.theme.ControlDivider
import retail.polemasket.digital.ui.theme.LocalDimens
import retail.polemasket.digital.ui.theme.controlBorderStroke

@Composable
fun CategoryFilterRow(
    selectedCategory: ServiceCategory?,
    modifier: Modifier = Modifier,
    onCategoryClick: (ServiceCategory?) -> Unit,
) {
    val dimens = LocalDimens.current

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceContainer,
    ) {
        Column {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(
                    horizontal = dimens.screenPadding,
                    vertical = dimens.contentSpacing,
                ),
                horizontalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
            ) {
                item {
                    CategoryPill(
                        isSelected = selectedCategory == null,
                        title = stringResource(R.string.home_filter_all),
                        onClick = { onCategoryClick(null) },
                    )
                }

                items(items = ServiceCategory.entries) { category ->
                    CategoryPill(
                        isSelected = selectedCategory == category,
                        title = stringResource(category.titleRes),
                        onClick = { onCategoryClick(category) },
                    )
                }
            }

            ControlDivider()
        }
    }
}

@Composable
private fun CategoryPill(
    isSelected: Boolean,
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(LocalDimens.current.controlCornerRadius)

    Surface(
        modifier = modifier
            .clip(shape)
            .clickable(onClick = onClick),
        shape = shape,
        color = if (isSelected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.surfaceContainerLow
        },
        contentColor = if (isSelected) {
            MaterialTheme.colorScheme.onPrimary
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        },
        border = if (isSelected) null else controlBorderStroke(),
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            style = MaterialTheme.typography.labelLarge,
        )
    }
}
