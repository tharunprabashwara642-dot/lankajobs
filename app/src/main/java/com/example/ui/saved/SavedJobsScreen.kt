package com.example.ui.saved

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.components.EmptyStateView
import com.example.ui.components.JobCard
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.SkyPrimary
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary

@Composable
fun SavedJobsScreen(
    viewModel: SavedJobsViewModel,
    onJobClick: (String) -> Unit,
    onExploreJobs: () -> Unit,
    modifier: Modifier = Modifier
) {
    val savedJobs by viewModel.savedJobs.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
            .statusBarsPadding()
    ) {
        // Screen Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Bookmark,
                contentDescription = null,
                tint = SkyPrimary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "Saved Jobs",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Text(
                    text = "${savedJobs.size} bookmarks stored on this device",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextMuted
                )
            }
        }

        if (savedJobs.isEmpty()) {
            EmptyStateView(
                title = "No Saved Jobs Yet",
                description = "Tap the bookmark icon on any job card to save it for later review or offline tracking.",
                icon = Icons.Default.BookmarkBorder,
                actionButtonText = "Explore Jobs",
                onActionClick = onExploreJobs,
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
                items(savedJobs, key = { it.id }, contentType = { "job" }) { job ->
                    JobCard(
                        job = job,
                        onJobClick = onJobClick,
                        onToggleSave = { viewModel.toggleSave(it) }
                    )
                }
            }
        }
    }
}
