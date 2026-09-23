package com.example.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.components.CategoryChip
import com.example.ui.components.CategoryHelper
import com.example.ui.components.EmptyStateView
import com.example.ui.components.FilterBottomSheet
import com.example.ui.components.JobCard
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.DarkBorder
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.DarkSurfaceCard
import com.example.ui.theme.DarkSurfaceElevated
import com.example.ui.theme.EmeraldSecondary
import com.example.ui.theme.SkyPrimary
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onJobClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val filter by viewModel.filter.collectAsStateWithLifecycle()
    val results by viewModel.results.collectAsStateWithLifecycle()
    val isFilterSheetVisible by viewModel.isFilterSheetVisible.collectAsStateWithLifecycle()

    val quickKeywords = listOf("Android", "Remote", "Accountant", "Colombo", "Internship", "Civil Engineer")

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            // Header Search Input + Filter Toggle
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = filter.query,
                    onValueChange = { viewModel.onQueryChange(it) },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("search_text_field"),
                    placeholder = {
                        Text(
                            text = "Title, company, skill, location...",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextMuted
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = SkyPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    trailingIcon = {
                        if (filter.query.isNotBlank()) {
                            IconButton(onClick = { viewModel.onQueryChange("") }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Clear search",
                                    tint = TextSecondary,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = DarkSurfaceCard,
                        unfocusedContainerColor = DarkSurfaceCard,
                        focusedBorderColor = SkyPrimary,
                        unfocusedBorderColor = DarkBorder,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary,
                        cursorColor = SkyPrimary
                    )
                )

                Spacer(modifier = Modifier.width(10.dp))

                // Filter Modal Button
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(
                            if (filter.hasActiveFilters) SkyPrimary.copy(alpha = 0.15f) else DarkSurfaceElevated
                        )
                        .border(
                            1.dp,
                            if (filter.hasActiveFilters) SkyPrimary else DarkBorder,
                            RoundedCornerShape(14.dp)
                        )
                        .clickable { viewModel.setFilterSheetVisible(true) }
                        .testTag("filter_button"),
                    contentAlignment = Alignment.Center
                ) {
                    BadgedBox(
                        badge = {
                            if (filter.activeFilterCount > 0) {
                                Badge(
                                    containerColor = SkyPrimary,
                                    contentColor = Color.White
                                ) {
                                    Text("${filter.activeFilterCount}")
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = "Filters",
                            tint = if (filter.hasActiveFilters) SkyPrimary else TextSecondary
                        )
                    }
                }
            }

            // Quick Category Filter Bar
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(CategoryHelper.ALL_CATEGORIES) { category ->
                    val isSelected = if (category == "All") filter.category == null else filter.category == category
                    CategoryChip(
                        category = category,
                        isSelected = isSelected,
                        onClick = {
                            viewModel.onCategorySelect(if (category == "All") null else category)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Active Filter summary or Quick Keyword tags
            if (filter.hasActiveFilters) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Active filters (${filter.activeFilterCount})",
                        style = MaterialTheme.typography.labelSmall,
                        color = SkyPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Clear all",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextMuted,
                        modifier = Modifier
                            .clickable { viewModel.clearAllFilters() }
                            .padding(4.dp)
                    )
                }
            } else {
                // Quick Suggestion Chips
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    items(quickKeywords) { kw ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(DarkSurfaceElevated)
                                .clickable { viewModel.onKeywordChipClick(kw) }
                                .padding(horizontal = 10.dp, vertical = 5.dp)
                        ) {
                            Text(
                                text = "#$kw",
                                style = MaterialTheme.typography.bodySmall,
                                color = TextMuted,
                                fontSize = 11.sp
                            )
                        }
                    }
                }
            }

            // Result Count and Sort Indicator
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Showing ${results.size} jobs",
                    style = MaterialTheme.typography.titleSmall,
                    color = TextPrimary,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = filter.sortOrder.displayName,
                    style = MaterialTheme.typography.labelSmall,
                    color = EmeraldSecondary,
                    modifier = Modifier
                        .clickable { viewModel.setFilterSheetVisible(true) }
                        .padding(4.dp)
                )
            }

            // Results List
            if (results.isEmpty()) {
                EmptyStateView(
                    title = "No Matching Jobs Found",
                    description = "We couldn't find any job openings matching your search criteria. Try removing some filters or searching with a different term.",
                    actionButtonText = "Clear All Filters",
                    onActionClick = { viewModel.clearAllFilters() },
                    modifier = Modifier.weight(1f)
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    contentPadding = PaddingValues(bottom = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(results, key = { it.id }) { job ->
                        JobCard(
                            job = job,
                            onJobClick = onJobClick,
                            onToggleSave = { viewModel.toggleSaveJob(it) }
                        )
                    }
                }
            }
        }

        // Filter Bottom Sheet
        if (isFilterSheetVisible) {
            FilterBottomSheet(
                initialFilter = filter,
                onDismiss = { viewModel.setFilterSheetVisible(false) },
                onApply = { updatedFilter ->
                    viewModel.onApplyFilter(updatedFilter)
                }
            )
        }
    }
}
