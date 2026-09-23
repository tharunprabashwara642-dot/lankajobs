package com.example.ui.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.repository.JobRepository
import com.example.domain.model.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

data class AddEditFormState(
    val title: String = "",
    val companyName: String = "",
    val location: String = "Colombo, Sri Lanka",
    val category: String = "IT & Software",
    val employmentType: String = "Full-time",
    val experienceLevel: String = "Mid Level",
    val isRemote: Boolean = false,
    val salaryMin: String = "150000",
    val salaryMax: String = "300000",
    val salaryCurrency: String = "LKR",
    val description: String = "",
    val requirementsText: String = "",
    val responsibilitiesText: String = "",
    val benefitsText: String = "",
    val applyUrl: String = "https://careers.example.lk",
    val sourceName: String = "Direct Employer",
    val isFeatured: Boolean = false,
    val isPublished: Boolean = true,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)

class AdminAddEditJobViewModel(
    private val jobRepository: JobRepository,
    private val jobId: String?
) : ViewModel() {

    private val _formState = MutableStateFlow(AddEditFormState())
    val formState: StateFlow<AddEditFormState> = _formState.asStateFlow()

    val isEditing: Boolean = jobId != null

    init {
        if (jobId != null) {
            viewModelScope.launch {
                jobRepository.getJobById(jobId).collect { existingJob ->
                    existingJob?.let { job ->
                        _formState.value = AddEditFormState(
                            title = job.title,
                            companyName = job.companyName,
                            location = job.location,
                            category = job.category,
                            employmentType = job.employmentType,
                            experienceLevel = job.experienceLevel,
                            isRemote = job.isRemote,
                            salaryMin = job.salaryMin?.toLong()?.toString() ?: "",
                            salaryMax = job.salaryMax?.toLong()?.toString() ?: "",
                            salaryCurrency = job.salaryCurrency,
                            description = job.description,
                            requirementsText = job.requirements.joinToString("\n"),
                            responsibilitiesText = job.responsibilities.joinToString("\n"),
                            benefitsText = job.benefits.joinToString("\n"),
                            applyUrl = job.applyUrl,
                            sourceName = job.sourceName,
                            isFeatured = job.isFeatured,
                            isPublished = job.isPublished
                        )
                    }
                }
            }
        }
    }

    fun updateForm(updater: (AddEditFormState) -> AddEditFormState) {
        _formState.value = updater(_formState.value)
    }

    fun saveJob(onSuccess: () -> Unit) {
        val current = _formState.value

        // Validate required fields
        if (current.title.isBlank()) {
            _formState.value = current.copy(errorMessage = "Job title is required")
            return
        }
        if (current.companyName.isBlank()) {
            _formState.value = current.copy(errorMessage = "Company name is required")
            return
        }
        if (current.location.isBlank()) {
            _formState.value = current.copy(errorMessage = "Location is required")
            return
        }
        if (current.description.isBlank()) {
            _formState.value = current.copy(errorMessage = "Job description is required")
            return
        }

        viewModelScope.launch {
            val minSal = current.salaryMin.toDoubleOrNull()
            val maxSal = current.salaryMax.toDoubleOrNull()

            val reqList = current.requirementsText.lines().map { it.trim() }.filter { it.isNotBlank() }
            val respList = current.responsibilitiesText.lines().map { it.trim() }.filter { it.isNotBlank() }
            val benList = current.benefitsText.lines().map { it.trim() }.filter { it.isNotBlank() }

            val targetId = jobId ?: ("lk-job-custom-" + UUID.randomUUID().toString().take(8))

            val job = Job(
                id = targetId,
                title = current.title.trim(),
                companyName = current.companyName.trim(),
                companyDescription = "${current.companyName.trim()} is an active employer based in ${current.location.trim()}.",
                companyWebsite = "https://example.lk",
                location = current.location.trim(),
                isRemote = current.isRemote,
                employmentType = current.employmentType,
                category = current.category,
                experienceLevel = current.experienceLevel,
                salaryMin = minSal,
                salaryMax = maxSal,
                salaryCurrency = current.salaryCurrency,
                description = current.description.trim(),
                responsibilities = respList,
                requirements = reqList,
                benefits = benList,
                applyUrl = current.applyUrl.trim(),
                sourceName = current.sourceName.trim().ifBlank { "Direct Listing" },
                postedDate = "Today",
                closingDate = "30 days left",
                isFeatured = current.isFeatured,
                isPublished = current.isPublished,
                isSaved = false,
                isDemo = false,
                createdAt = System.currentTimeMillis()
            )

            if (isEditing) {
                jobRepository.adminUpdateJob(job)
            } else {
                jobRepository.adminAddJob(job)
            }

            _formState.value = _formState.value.copy(isSuccess = true, errorMessage = null)
            onSuccess()
        }
    }

    companion object {
        fun provideFactory(jobRepository: JobRepository, jobId: String?): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return AdminAddEditJobViewModel(jobRepository, jobId) as T
                }
            }
    }
}
