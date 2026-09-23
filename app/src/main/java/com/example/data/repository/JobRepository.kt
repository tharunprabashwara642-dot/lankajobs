package com.example.data.repository

import com.example.domain.model.AdminStats
import com.example.domain.model.Job
import com.example.domain.model.JobFilter
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for Job discovery and management.
 * Designed to seamlessly support future remote backends (e.g. Supabase, Firebase, or Ktor API)
 * without altering ViewModel or UI code.
 */
interface JobRepository {
    fun getJobs(filter: JobFilter): Flow<List<Job>>
    fun getFeaturedJobs(): Flow<List<Job>>
    fun getLatestJobs(limit: Int = 10): Flow<List<Job>>
    fun getJobById(id: String): Flow<Job?>
    fun getSavedJobs(): Flow<List<Job>>
    fun isJobSaved(jobId: String): Flow<Boolean>
    suspend fun toggleSaveJob(jobId: String)

    // Admin Operations
    fun adminGetAllJobs(): Flow<List<Job>>
    suspend fun adminAddJob(job: Job)
    suspend fun adminUpdateJob(job: Job)
    suspend fun adminDeleteJob(id: String)
    suspend fun adminTogglePublish(id: String, isPublished: Boolean)
    suspend fun adminToggleFeatured(id: String, isFeatured: Boolean)
    suspend fun adminDuplicateJob(id: String)
    suspend fun resetDemoData()
    fun getAdminStats(): Flow<AdminStats>
}
