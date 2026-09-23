package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Job

@Entity(tableName = "jobs")
data class JobEntity(
    @PrimaryKey val id: String,
    val title: String,
    val companyName: String,
    val companyDescription: String,
    val location: String,
    val category: String,
    val employmentType: String,
    val experienceLevel: String,
    val salaryMin: Double?,
    val salaryMax: Double?,
    val salaryCurrency: String = "LKR",
    val description: String,
    val responsibilities: String, // newline-separated or delimited
    val requirements: String,
    val benefits: String,
    val postedDate: String,
    val closingDate: String,
    val applyUrl: String,
    val sourceName: String,
    val companyWebsite: String,
    val isFeatured: Boolean = false,
    val isRemote: Boolean = false,
    val isPublished: Boolean = true,
    val isDemo: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
) {
    fun toDomain(isSaved: Boolean = false): Job {
        return Job(
            id = id,
            title = title,
            companyName = companyName,
            companyDescription = companyDescription,
            location = location,
            category = category,
            employmentType = employmentType,
            experienceLevel = experienceLevel,
            salaryMin = salaryMin,
            salaryMax = salaryMax,
            salaryCurrency = salaryCurrency,
            description = description,
            responsibilities = responsibilities.lines().map { it.trim().removePrefix("•").removePrefix("-").trim() }.filter { it.isNotBlank() },
            requirements = requirements.lines().map { it.trim().removePrefix("•").removePrefix("-").trim() }.filter { it.isNotBlank() },
            benefits = benefits.lines().map { it.trim().removePrefix("•").removePrefix("-").trim() }.filter { it.isNotBlank() },
            postedDate = postedDate,
            closingDate = closingDate,
            applyUrl = applyUrl,
            sourceName = sourceName,
            companyWebsite = companyWebsite,
            isFeatured = isFeatured,
            isRemote = isRemote,
            isPublished = isPublished,
            isDemo = isDemo,
            isSaved = isSaved,
            createdAt = createdAt
        )
    }

    companion object {
        fun fromDomain(job: Job): JobEntity {
            return JobEntity(
                id = job.id,
                title = job.title,
                companyName = job.companyName,
                companyDescription = job.companyDescription,
                location = job.location,
                category = job.category,
                employmentType = job.employmentType,
                experienceLevel = job.experienceLevel,
                salaryMin = job.salaryMin,
                salaryMax = job.salaryMax,
                salaryCurrency = job.salaryCurrency,
                description = job.description,
                responsibilities = job.responsibilities.joinToString("\n"),
                requirements = job.requirements.joinToString("\n"),
                benefits = job.benefits.joinToString("\n"),
                postedDate = job.postedDate,
                closingDate = job.closingDate,
                applyUrl = job.applyUrl,
                sourceName = job.sourceName,
                companyWebsite = job.companyWebsite,
                isFeatured = job.isFeatured,
                isRemote = job.isRemote,
                isPublished = job.isPublished,
                isDemo = job.isDemo,
                createdAt = job.createdAt
            )
        }
    }
}
