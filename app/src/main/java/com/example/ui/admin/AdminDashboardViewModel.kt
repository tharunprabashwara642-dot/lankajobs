package com.example.ui.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.repository.JobRepository
import com.example.domain.model.AdminStats
import com.example.domain.model.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class AdminDashboardUiState(
    val stats: AdminStats = AdminStats(),
    val jobs: List<Job> = emptyList(),
    val searchQuery: String = "",
    val filterTab: String = "All", // "All", "Published", "Draft", "Featured"
    val actionMessage: String? = null
)

class AdminDashboardViewModel(
    private val jobRepository: JobRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _filterTab = MutableStateFlow("All")
    val filterTab: StateFlow<String> = _filterTab.asStateFlow()

    private val _actionMessage = MutableStateFlow<String?>(null)
    val actionMessage: StateFlow<String?> = _actionMessage.asStateFlow()

    val uiState: StateFlow<AdminDashboardUiState> = combine(
        jobRepository.getAdminStats(),
        jobRepository.adminGetAllJobs(),
        _searchQuery,
        _filterTab
    ) { stats, allJobs, query, tab ->
        var filtered = allJobs

        if (query.isNotBlank()) {
            val q = query.trim().lowercase()
            filtered = filtered.filter {
                it.title.lowercase().contains(q) ||
                it.companyName.lowercase().contains(q) ||
                it.category.lowercase().contains(q) ||
                it.location.lowercase().contains(q)
            }
        }

        filtered = when (tab) {
            "Published" -> filtered.filter { it.isPublished }
            "Draft" -> filtered.filter { !it.isPublished }
            "Featured" -> filtered.filter { it.isFeatured }
            else -> filtered
        }

        AdminDashboardUiState(
            stats = stats,
            jobs = filtered,
            searchQuery = query,
            filterTab = tab
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AdminDashboardUiState()
    )

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun onFilterTabChange(tab: String) {
        _filterTab.value = tab
    }

    fun togglePublish(jobId: String, currentStatus: Boolean) {
        viewModelScope.launch {
            jobRepository.adminTogglePublish(jobId, !currentStatus)
            _actionMessage.value = if (!currentStatus) "Job published" else "Job moved to drafts"
        }
    }

    fun toggleFeatured(jobId: String, currentStatus: Boolean) {
        viewModelScope.launch {
            jobRepository.adminToggleFeatured(jobId, !currentStatus)
            _actionMessage.value = if (!currentStatus) "Job marked as Featured" else "Featured status removed"
        }
    }

    fun duplicateJob(jobId: String) {
        viewModelScope.launch {
            jobRepository.adminDuplicateJob(jobId)
            _actionMessage.value = "Listing duplicated as Draft"
        }
    }

    fun deleteJob(jobId: String) {
        viewModelScope.launch {
            jobRepository.adminDeleteJob(jobId)
            _actionMessage.value = "Job listing deleted"
        }
    }

    fun resetDemoData() {
        viewModelScope.launch {
            jobRepository.resetDemoData()
            _actionMessage.value = "Reset to 32 default Sri Lankan demo jobs"
        }
    }

    fun clearActionMessage() {
        _actionMessage.value = null
    }

    companion object {
        fun provideFactory(jobRepository: JobRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return AdminDashboardViewModel(jobRepository) as T
                }
            }
    }
}
