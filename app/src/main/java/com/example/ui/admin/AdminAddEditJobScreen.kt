package com.example.ui.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.components.CategoryHelper
import com.example.ui.components.EMPLOYMENT_TYPES
import com.example.ui.components.EXPERIENCE_LEVELS
import com.example.ui.components.FilterSelectChip
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.DarkBorder
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.DarkSurfaceCard
import com.example.ui.theme.DarkSurfaceElevated
import com.example.ui.theme.EmeraldSecondary
import com.example.ui.theme.SkyPrimary
import com.example.ui.theme.StatusRed
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AdminAddEditJobScreen(
    viewModel: AdminAddEditJobViewModel,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val formState by viewModel.formState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = DarkBackground,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = TextPrimary
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (viewModel.isEditing) "Edit Job Vacancy" else "Post New Vacancy",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }

                Button(
                    onClick = { viewModel.saveJob(onSuccess = onNavigateBack) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SkyPrimary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.testTag("admin_save_job_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Save,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Save", fontWeight = FontWeight.Bold)
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (formState.errorMessage != null) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(StatusRed.copy(alpha = 0.15f))
                            .border(1.dp, StatusRed, RoundedCornerShape(10.dp))
                            .padding(12.dp)
                    ) {
                        Text(
                            text = formState.errorMessage!!,
                            color = StatusRed,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            // Title
            item {
                FormTextField(
                    label = "Job Title *",
                    value = formState.title,
                    onValueChange = { viewModel.updateForm { s -> s.copy(title = it, errorMessage = null) } },
                    placeholder = "e.g. Senior Android Engineer"
                )
            }

            // Company Name
            item {
                FormTextField(
                    label = "Company Name *",
                    value = formState.companyName,
                    onValueChange = { viewModel.updateForm { s -> s.copy(companyName = it, errorMessage = null) } },
                    placeholder = "e.g. Dialog Axiata PLC, WSO2, MillenniumIT"
                )
            }

            // Location
            item {
                FormTextField(
                    label = "Location (City / District) *",
                    value = formState.location,
                    onValueChange = { viewModel.updateForm { s -> s.copy(location = it, errorMessage = null) } },
                    placeholder = "e.g. Colombo 02, Sri Lanka"
                )
            }

            // Category Selection
            item {
                Column {
                    Text(
                        text = "Category *",
                        style = MaterialTheme.typography.labelMedium,
                        color = TextSecondary,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        CategoryHelper.ALL_CATEGORIES.filter { it != "All" }.forEach { cat ->
                            FilterSelectChip(
                                text = cat,
                                isSelected = formState.category == cat,
                                onClick = { viewModel.updateForm { s -> s.copy(category = cat) } }
                            )
                        }
                    }
                }
            }

            // Employment Type
            item {
                Column {
                    Text(
                        text = "Employment Type",
                        style = MaterialTheme.typography.labelMedium,
                        color = TextSecondary,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        EMPLOYMENT_TYPES.filter { it != "All" }.forEach { type ->
                            FilterSelectChip(
                                text = type,
                                isSelected = formState.employmentType == type,
                                onClick = { viewModel.updateForm { s -> s.copy(employmentType = type) } }
                            )
                        }
                    }
                }
            }

            // Experience Level
            item {
                Column {
                    Text(
                        text = "Experience Level",
                        style = MaterialTheme.typography.labelMedium,
                        color = TextSecondary,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        EXPERIENCE_LEVELS.filter { it != "All" }.forEach { level ->
                            FilterSelectChip(
                                text = level,
                                isSelected = formState.experienceLevel == level,
                                onClick = { viewModel.updateForm { s -> s.copy(experienceLevel = level) } }
                            )
                        }
                    }
                }
            }

            // Remote Switch
            item {
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
                            text = "Remote Work",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = TextPrimary
                        )
                        Text(
                            text = "Can this role be done 100% remotely from Sri Lanka?",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextMuted
                        )
                    }
                    Switch(
                        checked = formState.isRemote,
                        onCheckedChange = { viewModel.updateForm { s -> s.copy(isRemote = it) } },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = EmeraldSecondary
                        )
                    )
                }
            }

            // Salary Range
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FormTextField(
                        label = "Min Salary (LKR)",
                        value = formState.salaryMin,
                        onValueChange = { viewModel.updateForm { s -> s.copy(salaryMin = it) } },
                        keyboardType = KeyboardType.Number,
                        modifier = Modifier.weight(1f)
                    )
                    FormTextField(
                        label = "Max Salary (LKR)",
                        value = formState.salaryMax,
                        onValueChange = { viewModel.updateForm { s -> s.copy(salaryMax = it) } },
                        keyboardType = KeyboardType.Number,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Description
            item {
                FormTextField(
                    label = "Job Description *",
                    value = formState.description,
                    onValueChange = { viewModel.updateForm { s -> s.copy(description = it, errorMessage = null) } },
                    placeholder = "Describe the role, project overview, team environment...",
                    singleLine = false,
                    minLines = 4
                )
            }

            // Key Responsibilities
            item {
                FormTextField(
                    label = "Key Responsibilities (One per line)",
                    value = formState.responsibilitiesText,
                    onValueChange = { viewModel.updateForm { s -> s.copy(responsibilitiesText = it) } },
                    placeholder = "Design clean architectures\nCollaborate with product designers\nWrite clean unit tests",
                    singleLine = false,
                    minLines = 3
                )
            }

            // Requirements
            item {
                FormTextField(
                    label = "Requirements & Skills (One per line)",
                    value = formState.requirementsText,
                    onValueChange = { viewModel.updateForm { s -> s.copy(requirementsText = it) } },
                    placeholder = "3+ years Android or Kotlin\nFamiliarity with Jetpack Compose\nDegree in CS or equivalent",
                    singleLine = false,
                    minLines = 3
                )
            }

            // Benefits
            item {
                FormTextField(
                    label = "Perks & Benefits (One per line)",
                    value = formState.benefitsText,
                    onValueChange = { viewModel.updateForm { s -> s.copy(benefitsText = it) } },
                    placeholder = "Comprehensive medical cover\nFlexible working hours\nAnnual performance bonus",
                    singleLine = false,
                    minLines = 3
                )
            }

            // Apply URL & Source
            item {
                FormTextField(
                    label = "Application / Careers Portal URL *",
                    value = formState.applyUrl,
                    onValueChange = { viewModel.updateForm { s -> s.copy(applyUrl = it) } },
                    placeholder = "https://careers.company.lk/apply"
                )
            }

            item {
                FormTextField(
                    label = "Source Name",
                    value = formState.sourceName,
                    onValueChange = { viewModel.updateForm { s -> s.copy(sourceName = it) } },
                    placeholder = "e.g. Direct Employer, TopJobs, LinkedIn"
                )
            }

            // Featured & Published Switches
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(DarkSurfaceElevated)
                        .padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Mark as Featured ★",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = TextPrimary
                            )
                            Text(
                                text = "Showcase this vacancy on the Home screen carousel",
                                style = MaterialTheme.typography.bodySmall,
                                color = TextMuted
                            )
                        }
                        Switch(
                            checked = formState.isFeatured,
                            onCheckedChange = { viewModel.updateForm { s -> s.copy(isFeatured = it) } },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = SkyPrimary
                            )
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Publish Listing Immediately",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = TextPrimary
                            )
                            Text(
                                text = "If unchecked, listing remains as an internal draft",
                                style = MaterialTheme.typography.bodySmall,
                                color = TextMuted
                            )
                        }
                        Switch(
                            checked = formState.isPublished,
                            onCheckedChange = { viewModel.updateForm { s -> s.copy(isPublished = it) } },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = EmeraldSecondary
                            )
                        )
                    }
                }
            }

            // Save Action Button
            item {
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = { viewModel.saveJob(onSuccess = onNavigateBack) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SkyPrimary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text(
                        text = if (viewModel.isEditing) "Save Changes" else "Publish Vacancy to LankaJobs",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun FormTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    singleLine: Boolean = true,
    minLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = TextSecondary,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = placeholder?.let { { Text(it, color = TextMuted, fontSize = 13.sp) } },
            singleLine = singleLine,
            minLines = minLines,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary,
                focusedContainerColor = DarkSurfaceCard,
                unfocusedContainerColor = DarkSurfaceCard,
                focusedBorderColor = SkyPrimary,
                unfocusedBorderColor = DarkBorder
            )
        )
    }
}
