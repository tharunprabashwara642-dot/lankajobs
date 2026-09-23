package com.example.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.repository.JobRepository
import com.example.domain.model.Job
import com.example.domain.model.JobFilter
import com.example.domain.model.JobSortOrder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class SearchUiState(
    val filter: JobFilter = JobFilter(),
    val results: List<Job> = emptyList(),
    val recentKeywords: List<String> = listOf("Android", "Remote", "Accountant", "Colombo", "Internship", "Civil Engineer"),
    val isFilterSheetVisible: Boolean = false,
    val isLoading: Boolean = false
)

class SearchViewModel(
    private val jobRepository: JobRepository,
    initialCategory: String? = null,
    initialQuery: String? = null
) : ViewModel() {

    private val _filter = MutableStateFlow(
        JobFilter(
            category = if (initialCategory.isNullOrBlank()) null else initialCategory,
            query = if (initialQuery.isNullOrBlank()) "" else initialQuery
        )
    )
    val filter: StateFlow<JobFilter> = _filter.asStateFlow()

    private val _isFilterSheetVisible = MutableStateFlow(false)
    val isFilterSheetVisible: StateFlow<Boolean> = _isFilterSheetVisible.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val results: StateFlow<List<Job>> = _filter.flatMapLatest { activeFilter ->
        jobRepository.getJobs(activeFilter)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun onQueryChange(newQuery: String) {
        _filter.value = _filter.value.copy(query = newQuery)
    }

    fun onApplyFilter(updatedFilter: JobFilter) {
        _filter.value = updatedFilter
    }

    fun onCategorySelect(category: String?) {
        _filter.value = _filter.value.copy(category = category)
    }

    fun onLocationSelect(location: String?) {
        _filter.value = _filter.value.copy(location = location)
    }

    fun onSortOrderSelect(sortOrder: JobSortOrder) {
        _filter.value = _filter.value.copy(sortOrder = sortOrder)
    }

    fun onKeywordChipClick(keyword: String) {
        _filter.value = _filter.value.copy(query = keyword)
    }

    fun clearAllFilters() {
        _filter.value = JobFilter()
    }

    fun setFilterSheetVisible(visible: Boolean) {
        _isFilterSheetVisible.value = visible
    }

    fun toggleSaveJob(jobId: String) {
        viewModelScope.launch {
            jobRepository.toggleSaveJob(jobId)
        }
    }

    companion object {
        fun provideFactory(
            jobRepository: JobRepository,
            category: String? = null,
            query: String? = null
        ): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SearchViewModel(jobRepository, category, query) as T
                }
            }
    }
}
