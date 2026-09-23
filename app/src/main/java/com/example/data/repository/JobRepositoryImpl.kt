package com.example.data.repository

import com.example.data.local.SeedDataProvider
import com.example.data.local.dao.JobDao
import com.example.data.local.dao.SavedJobDao
import com.example.data.local.entity.JobEntity
import com.example.data.local.entity.SavedJobEntity
import com.example.domain.model.AdminStats
import com.example.domain.model.Job
import com.example.domain.model.JobFilter
import com.example.domain.model.JobSortOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import java.util.UUID

class JobRepositoryImpl(
    private val jobDao: JobDao,
    private val savedJobDao: SavedJobDao
) : JobRepository {

    override fun getJobs(filter: JobFilter): Flow<List<Job>> {
        return combine(
            jobDao.getAllPublishedJobs(),
            savedJobDao.getAllSavedJobIds()
        ) { jobEntities, savedIds ->
            val savedSet = savedIds.toSet()
            var result = jobEntities.map { it.toDomain(isSaved = savedSet.contains(it.id)) }

            // Apply Search Query
            if (filter.query.isNotBlank()) {
                val q = filter.query.trim().lowercase()
                result = result.filter { job ->
                    job.title.lowercase().contains(q) ||
                    job.companyName.lowercase().contains(q) ||
                    job.location.lowercase().contains(q) ||
                    job.category.lowercase().contains(q) ||
                    job.description.lowercase().contains(q) ||
                    job.requirements.any { it.lowercase().contains(q) } ||
                    job.responsibilities.any { it.lowercase().contains(q) }
                }
            }

            // Apply Category Filter
            filter.category?.let { cat ->
                if (cat.isNotBlank() && !cat.equals("All", ignoreCase = true)) {
                    result = result.filter { it.category.equals(cat, ignoreCase = true) }
                }
            }

            // Apply Location Filter
            filter.location?.let { loc ->
                if (loc.isNotBlank() && !loc.equals("All", ignoreCase = true)) {
                    result = result.filter { it.location.contains(loc, ignoreCase = true) }
                }
            }

            // Apply Employment Type
            filter.employmentType?.let { empType ->
                if (empType.isNotBlank() && !empType.equals("All", ignoreCase = true)) {
                    result = result.filter { it.employmentType.equals(empType, ignoreCase = true) }
                }
            }

            // Apply Experience Level
            filter.experienceLevel?.let { expLevel ->
                if (expLevel.isNotBlank() && !expLevel.equals("All", ignoreCase = true)) {
                    result = result.filter { it.experienceLevel.contains(expLevel, ignoreCase = true) }
                }
            }

            // Apply Remote Only
            if (filter.isRemoteOnly) {
                result = result.filter { it.isRemote || it.location.contains("Remote", ignoreCase = true) }
            }

            // Apply Minimum Salary Filter
            filter.minSalary?.let { minSal ->
                result = result.filter { job ->
                    (job.salaryMax != null && job.salaryMax >= minSal) ||
                    (job.salaryMin != null && job.salaryMin >= minSal)
                }
            }

            // Apply Sorting
            when (filter.sortOrder) {
                JobSortOrder.LATEST -> result.sortedByDescending { it.createdAt }
                JobSortOrder.SALARY_HIGH_TO_LOW -> result.sortedByDescending {
                    it.salaryMax ?: it.salaryMin ?: 0.0
                }
                JobSortOrder.SALARY_LOW_TO_HIGH -> result.sortedBy {
                    it.salaryMin ?: it.salaryMax ?: Double.MAX_VALUE
                }
            }
        }
    }

    override fun getFeaturedJobs(): Flow<List<Job>> {
        return combine(
            jobDao.getFeaturedJobs(),
            savedJobDao.getAllSavedJobIds()
        ) { entities, savedIds ->
            val savedSet = savedIds.toSet()
            entities.map { it.toDomain(isSaved = savedSet.contains(it.id)) }
        }
    }

    override fun getLatestJobs(limit: Int): Flow<List<Job>> {
        return combine(
            jobDao.getAllPublishedJobs(),
            savedJobDao.getAllSavedJobIds()
        ) { entities, savedIds ->
            val savedSet = savedIds.toSet()
            entities.take(limit).map { it.toDomain(isSaved = savedSet.contains(it.id)) }
        }
    }

    override fun getJobById(id: String): Flow<Job?> {
        return combine(
            jobDao.getJobById(id),
            savedJobDao.isJobSaved(id)
        ) { entity, isSaved ->
            entity?.toDomain(isSaved = isSaved)
        }
    }

    override fun getSavedJobs(): Flow<List<Job>> {
        return savedJobDao.getSavedJobs().map { entities ->
            entities.map { it.toDomain(isSaved = true) }
        }
    }

    override fun isJobSaved(jobId: String): Flow<Boolean> {
        return savedJobDao.isJobSaved(jobId)
    }

    override suspend fun toggleSaveJob(jobId: String) {
        val isCurrentlySaved = savedJobDao.isJobSavedImmediate(jobId)
        if (isCurrentlySaved) {
            savedJobDao.deleteSavedJob(jobId)
        } else {
            savedJobDao.saveJob(SavedJobEntity(jobId = jobId))
        }
    }

    override fun adminGetAllJobs(): Flow<List<Job>> {
        return jobDao.getAllJobsForAdmin().map { entities ->
            entities.map { it.toDomain(isSaved = false) }
        }
    }

    override suspend fun adminAddJob(job: Job) {
        jobDao.insertJob(JobEntity.fromDomain(job))
    }

    override suspend fun adminUpdateJob(job: Job) {
        jobDao.updateJob(JobEntity.fromDomain(job))
    }

    override suspend fun adminDeleteJob(id: String) {
        jobDao.deleteJobById(id)
        savedJobDao.deleteSavedJob(id)
    }

    override suspend fun adminTogglePublish(id: String, isPublished: Boolean) {
        jobDao.updatePublishStatus(id, isPublished)
    }

    override suspend fun adminToggleFeatured(id: String, isFeatured: Boolean) {
        jobDao.updateFeaturedStatus(id, isFeatured)
    }

    override suspend fun adminDuplicateJob(id: String) {
        val original = jobDao.getJobByIdImmediate(id) ?: return
        val newId = "lk-job-" + UUID.randomUUID().toString().take(8)
        val duplicate = original.copy(
            id = newId,
            title = "${original.title} (Copy)",
            isPublished = false,
            createdAt = System.currentTimeMillis(),
            isDemo = false
        )
        jobDao.insertJob(duplicate)
    }

    override suspend fun resetDemoData() {
        jobDao.clearAll()
        jobDao.insertJobs(SeedDataProvider.getInitialJobs())
    }

    override fun getAdminStats(): Flow<AdminStats> {
        return jobDao.getAllJobsForAdmin().map { entities ->
            val total = entities.size
            val published = entities.count { it.isPublished }
            val draft = entities.count { !it.isPublished }
            val featured = entities.count { it.isFeatured }
            val remote = entities.count { it.isRemote || it.location.contains("Remote", ignoreCase = true) }
            val categories = entities.map { it.category }.distinct().size
            val demo = entities.count { it.isDemo }
            val custom = entities.count { !it.isDemo }

            AdminStats(
                totalJobs = total,
                publishedJobs = published,
                draftJobs = draft,
                featuredJobs = featured,
                remoteJobs = remote,
                totalCategories = categories,
                demoJobsCount = demo,
                customJobsCount = custom
            )
        }
    }
}
