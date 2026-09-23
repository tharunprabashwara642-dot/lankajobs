package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.JobFilter
import com.example.domain.model.JobSortOrder
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.DarkBorder
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.DarkSurfaceElevated
import com.example.ui.theme.SkyPrimary
import com.example.ui.theme.SkyPrimaryContainer
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

val SRI_LANKA_LOCATIONS = listOf(
    "All",
    "Colombo",
    "Kandy",
    "Galle",
    "Gampaha",
    "Kurunegala",
    "Kegalle",
    "Jaffna",
    "Negombo",
    "Remote"
)

val EMPLOYMENT_TYPES = listOf(
    "All",
    "Full-time",
    "Part-time",
    "Contract",
    "Internship"
)

val EXPERIENCE_LEVELS = listOf(
    "All",
    "Entry Level",
    "Mid Level",
    "Senior Level",
    "Lead/Manager",
    "Internship"
)

val MIN_SALARY_OPTIONS = listOf(
    null to "Any",
    50000.0 to "50k+",
    100000.0 to "100k+",
    150000.0 to "150k+",
    250000.0 to "250k+",
    350000.0 to "350k+"
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FilterBottomSheet(
    initialFilter: JobFilter,
    onDismiss: () -> Unit,
    onApply: (JobFilter) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var selectedCategory by remember { mutableStateOf(initialFilter.category) }
    var selectedLocation by remember { mutableStateOf(initialFilter.location) }
    var selectedType by remember { mutableStateOf(initialFilter.employmentType) }
    var selectedExperience by remember { mutableStateOf(initialFilter.experienceLevel) }
    var isRemoteOnly by remember { mutableStateOf(initialFilter.isRemoteOnly) }
    var minSalary by remember { mutableStateOf(initialFilter.minSalary) }
    var sortOrder by remember { mutableStateOf(initialFilter.sortOrder) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = DarkSurface,
        dragHandle = null
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Filter & Sort Jobs",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )

                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = TextSecondary
                    )
                }
            }

            HorizontalDivider(color = DarkBorder)

            // Scrollable Options
            Column(
                modifier = Modifier
                    .weight(1f, fill = false)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 16.dp)
            ) {
                // Sort Order
                Text(
                    text = "Sort By",
                    style = MaterialTheme.typography.labelLarge,
                    color = TextPrimary,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    JobSortOrder.values().forEach { order ->
                        FilterSelectChip(
                            text = order.displayName,
                            isSelected = sortOrder == order,
                            onClick = { sortOrder = order }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Remote Switch
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(DarkSurfaceElevated)
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Remote Only",
                            style = MaterialTheme.typography.labelLarge,
                            color = TextPrimary,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "Show only work-from-home opportunities",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextMuted
                        )
                    }
                    Switch(
                        checked = isRemoteOnly,
                        onCheckedChange = { isRemoteOnly = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = SkyPrimary
                        )
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Location
                Text(
                    text = "Location (Sri Lanka)",
                    style = MaterialTheme.typography.labelLarge,
                    color = TextPrimary,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SRI_LANKA_LOCATIONS.forEach { loc ->
                        val isSelected = if (loc == "All") selectedLocation == null else selectedLocation == loc
                        FilterSelectChip(
                            text = loc,
                            isSelected = isSelected,
                            onClick = {
                                selectedLocation = if (loc == "All") null else loc
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Category
                Text(
                    text = "Category",
                    style = MaterialTheme.typography.labelLarge,
                    color = TextPrimary,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CategoryHelper.ALL_CATEGORIES.forEach { cat ->
                        val isSelected = if (cat == "All") selectedCategory == null else selectedCategory == cat
                        FilterSelectChip(
                            text = cat,
                            isSelected = isSelected,
                            onClick = {
                                selectedCategory = if (cat == "All") null else cat
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Employment Type
                Text(
                    text = "Employment Type",
                    style = MaterialTheme.typography.labelLarge,
                    color = TextPrimary,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    EMPLOYMENT_TYPES.forEach { type ->
                        val isSelected = if (type == "All") selectedType == null else selectedType == type
                        FilterSelectChip(
                            text = type,
                            isSelected = isSelected,
                            onClick = {
                                selectedType = if (type == "All") null else type
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Minimum Salary (LKR)
                Text(
                    text = "Minimum Monthly Salary (LKR)",
                    style = MaterialTheme.typography.labelLarge,
                    color = TextPrimary,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    MIN_SALARY_OPTIONS.forEach { (amount, label) ->
                        val isSelected = minSalary == amount
                        FilterSelectChip(
                            text = label,
                            isSelected = isSelected,
                            onClick = { minSalary = amount }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Experience Level
                Text(
                    text = "Experience Level",
                    style = MaterialTheme.typography.labelLarge,
                    color = TextPrimary,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    EXPERIENCE_LEVELS.forEach { level ->
                        val isSelected = if (level == "All") selectedExperience == null else selectedExperience == level
                        FilterSelectChip(
                            text = level,
                            isSelected = isSelected,
                            onClick = {
                                selectedExperience = if (level == "All") null else level
                            }
                        )
                    }
                }
            }

            HorizontalDivider(color = DarkBorder)

            // Bottom Actions: Reset & Apply
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        selectedCategory = null
                        selectedLocation = null
                        selectedType = null
                        selectedExperience = null
                        isRemoteOnly = false
                        minSalary = null
                        sortOrder = JobSortOrder.LATEST
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = TextSecondary
                    ),
                    border = androidx.compose.foundation.BorderStroke(1.dp, DarkBorder),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Reset All")
                }

                Button(
                    onClick = {
                        onApply(
                            initialFilter.copy(
                                category = selectedCategory,
                                location = selectedLocation,
                                employmentType = selectedType,
                                experienceLevel = selectedExperience,
                                isRemoteOnly = isRemoteOnly,
                                minSalary = minSalary,
                                sortOrder = sortOrder
                            )
                        )
                        onDismiss()
                    },
                    modifier = Modifier.weight(1.5f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SkyPrimary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Apply Filters",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun FilterSelectChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(if (isSelected) SkyPrimaryContainer else DarkSurfaceElevated)
            .border(
                1.dp,
                if (isSelected) SkyPrimary else DarkBorder,
                RoundedCornerShape(10.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            color = if (isSelected) TextPrimary else TextSecondary,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}
