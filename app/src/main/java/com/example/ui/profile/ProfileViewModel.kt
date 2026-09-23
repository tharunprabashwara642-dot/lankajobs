package com.example.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.repository.JobRepository
import com.example.data.repository.UserProfileRepository
import com.example.domain.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class ProfileUiState(
    val userProfile: UserProfile = UserProfile(),
    val savedCount: Int = 0,
    val isEditing: Boolean = false,
    val infoMessage: String? = null
)

class ProfileViewModel(
    private val userProfileRepository: UserProfileRepository,
    private val jobRepository: JobRepository
) : ViewModel() {

    private val _infoMessage = MutableStateFlow<String?>(null)
    val infoMessage: StateFlow<String?> = _infoMessage.asStateFlow()

    private val _isEditing = MutableStateFlow(false)
    val isEditing: StateFlow<Boolean> = _isEditing.asStateFlow()

    val userProfile: StateFlow<UserProfile> = userProfileRepository.getUserProfile()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UserProfile()
        )

    val savedCount: StateFlow<Int> = kotlinx.coroutines.flow.flow {
        jobRepository.getSavedJobs().collect {
            emit(it.size)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0
    )

    fun startEditing() {
        _isEditing.value = true
    }

    fun cancelEditing() {
        _isEditing.value = false
    }

    fun saveProfile(
        name: String,
        headline: String,
        email: String,
        phone: String,
        preferredLocation: String,
        targetRole: String
    ) {
        viewModelScope.launch {
            val current = userProfile.value
            val updated = current.copy(
                fullName = name.trim(),
                headline = headline.trim(),
                email = email.trim(),
                phone = phone.trim(),
                preferredLocation = preferredLocation.trim(),
                targetRole = targetRole.trim()
            )
            userProfileRepository.updateUserProfile(updated)
            _isEditing.value = false
            _infoMessage.value = "Profile updated successfully!"
        }
    }

    fun toggleAlerts(enabled: Boolean) {
        viewModelScope.launch {
            val updated = userProfile.value.copy(dailyAlertsEnabled = enabled)
            userProfileRepository.updateUserProfile(updated)
            _infoMessage.value = if (enabled) "Daily job alerts turned ON" else "Daily job alerts paused"
        }
    }

    fun updateResumeMock(fileName: String) {
        viewModelScope.launch {
            val updated = userProfile.value.copy(resumeFileName = fileName)
            userProfileRepository.updateUserProfile(updated)
            _infoMessage.value = "Resume updated: $fileName"
        }
    }

    fun resetDemoData() {
        viewModelScope.launch {
            jobRepository.resetDemoData()
            _infoMessage.value = "Demo database reset to default 32 Sri Lankan jobs."
        }
    }

    fun clearInfoMessage() {
        _infoMessage.value = null
    }

    companion object {
        fun provideFactory(
            userProfileRepository: UserProfileRepository,
            jobRepository: JobRepository
        ): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ProfileViewModel(userProfileRepository, jobRepository) as T
                }
            }
    }
}
