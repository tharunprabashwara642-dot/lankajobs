package com.example.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.repository.JobRepository
import com.example.domain.model.Job
import com.example.domain.model.JobFilter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class HomeUiState(
    val featuredJobs: List<Job> = emptyList(),
    val latestJobs: List<Job> = emptyList(),
    val selectedQuickFilter: String = "All",
    val savedCount: Int = 0,
    val isLoading: Boolean = false
)

class HomeViewModel(
    private val jobRepository: JobRepository
) : ViewModel() {

    private val _selectedQuickFilter = MutableStateFlow("All")
    val selectedQuickFilter: StateFlow<String> = _selectedQuickFilter.asStateFlow()

    private val _quickFilterJobFilter = MutableStateFlow(JobFilter())

    val uiState: StateFlow<HomeUiState> = combine(
        jobRepository.getFeaturedJobs(),
        jobRepository.getJobs(_quickFilterJobFilter.value),
        jobRepository.getSavedJobs(),
        _selectedQuickFilter
    ) { featured, filteredLatest, saved, quickFilter ->
        HomeUiState(
            featuredJobs = featured,
            latestJobs = filteredLatest,
            selectedQuickFilter = quickFilter,
            savedCount = saved.size,
            isLoading = false
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState(isLoading = true)
    )

    fun onQuickFilterSelect(filterName: String) {
        _selectedQuickFilter.value = filterName
        val newFilter = when (filterName) {
            "Remote" -> JobFilter(isRemoteOnly = true)
            "Colombo" -> JobFilter(location = "Colombo")
            "IT & Software" -> JobFilter(category = "IT & Software")
            "Internships" -> JobFilter(category = "Internships")
            else -> JobFilter()
        }
        _quickFilterJobFilter.value = newFilter
        viewModelScope.launch {
            jobRepository.getJobs(newFilter).collect { jobs ->
                // Handled reactively
            }
        }
    }

    fun toggleSaveJob(jobId: String) {
        viewModelScope.launch {
            jobRepository.toggleSaveJob(jobId)
        }
    }

    companion object {
        fun provideFactory(jobRepository: JobRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return HomeViewModel(jobRepository) as T
                }
            }
    }
}
