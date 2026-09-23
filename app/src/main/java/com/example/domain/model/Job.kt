package com.example.domain.model

data class Job(
    val id: String,
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
    val responsibilities: List<String>,
    val requirements: List<String>,
    val benefits: List<String>,
    val postedDate: String,
    val closingDate: String,
    val applyUrl: String,
    val sourceName: String,
    val companyWebsite: String,
    val isFeatured: Boolean = false,
    val isRemote: Boolean = false,
    val isPublished: Boolean = true,
    val isDemo: Boolean = true,
    val isSaved: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
) {
    val formattedSalary: String
        get() {
            if (salaryMin == null && salaryMax == null) return "Salary Negotiable"
            val currency = salaryCurrency.ifBlank { "LKR" }
            return when {
                salaryMin != null && salaryMax != null -> {
                    "$currency %,.0f - %,.0f / mo".format(salaryMin, salaryMax)
                }
                salaryMin != null -> {
                    "From $currency %,.0f / mo".format(salaryMin)
                }
                salaryMax != null -> {
                    "Up to $currency %,.0f / mo".format(salaryMax)
                }
                else -> "Salary Negotiable"
            }
        }

    val companyInitials: String
        get() = companyName.split(" ")
            .filter { it.isNotBlank() }
            .take(2)
            .map { it.first().uppercaseChar() }
            .joinToString("")
            .ifEmpty { "LJ" }
}

enum class JobSortOrder(val displayName: String) {
    LATEST("Latest Posted"),
    SALARY_HIGH_TO_LOW("Salary: High to Low"),
    SALARY_LOW_TO_HIGH("Salary: Low to High")
}

data class JobFilter(
    val query: String = "",
    val category: String? = null,
    val location: String? = null,
    val employmentType: String? = null,
    val experienceLevel: String? = null,
    val isRemoteOnly: Boolean = false,
    val minSalary: Double? = null,
    val sortOrder: JobSortOrder = JobSortOrder.LATEST
) {
    val hasActiveFilters: Boolean
        get() = query.isNotBlank() ||
                category != null ||
                location != null ||
                employmentType != null ||
                experienceLevel != null ||
                isRemoteOnly ||
                minSalary != null ||
                sortOrder != JobSortOrder.LATEST

    val activeFilterCount: Int
        get() {
            var count = 0
            if (query.isNotBlank()) count++
            if (category != null) count++
            if (location != null) count++
            if (employmentType != null) count++
            if (experienceLevel != null) count++
            if (isRemoteOnly) count++
            if (minSalary != null) count++
            if (sortOrder != JobSortOrder.LATEST) count++
            return count
        }
}

data class UserProfile(
    val id: Int = 1,
    val fullName: String = "Kasun Perera",
    val headline: String = "Senior Android Developer | Kotlin Specialist",
    val email: String = "kasun.perera@example.com",
    val phone: String = "+94 77 123 4567",
    val preferredLocation: String = "Colombo, Western Province",
    val targetRole: String = "Mobile Engineering",
    val preferredCategories: List<String> = listOf("IT & Software", "Engineering"),
    val experienceLevel: String = "Mid-Senior Level",
    val bio: String = "Passionate mobile engineer with 4+ years of Kotlin experience exploring modern tech opportunities in Sri Lanka.",
    val resumeFileName: String = "Kasun_Perera_CV_2026.pdf",
    val dailyAlertsEnabled: Boolean = true
)

data class AdminStats(
    val totalJobs: Int = 0,
    val publishedJobs: Int = 0,
    val draftJobs: Int = 0,
    val featuredJobs: Int = 0,
    val remoteJobs: Int = 0,
    val totalCategories: Int = 0,
    val demoJobsCount: Int = 0,
    val customJobsCount: Int = 0
)
