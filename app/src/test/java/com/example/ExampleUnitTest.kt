package com.example

import com.example.data.local.SeedDataProvider
import com.example.domain.model.Job
import com.example.domain.model.JobFilter
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {
  @Test
  fun testSeedDataContains32Jobs() {
    val jobs = SeedDataProvider.getInitialJobs()
    assertEquals(32, jobs.size)
  }

  @Test
  fun testFormattedSalaryCalculation() {
    val jobWithRange = Job(
      id = "test-1",
      title = "Engineer",
      companyName = "Test Co",
      companyDescription = "",
      companyWebsite = "",
      location = "Colombo",
      isRemote = true,
      employmentType = "Full-time",
      category = "IT & Software",
      experienceLevel = "Mid Level",
      salaryMin = 150000.0,
      salaryMax = 250000.0,
      salaryCurrency = "LKR",
      description = "Desc",
      responsibilities = emptyList(),
      requirements = emptyList(),
      benefits = emptyList(),
      applyUrl = "https://example.com",
      sourceName = "Test",
      postedDate = "Today",
      closingDate = "30 days",
      isFeatured = true,
      isPublished = true,
      isSaved = false,
      isDemo = true,
      createdAt = System.currentTimeMillis()
    )

    assertEquals("LKR 150,000 - 250,000 / mo", jobWithRange.formattedSalary)
    assertEquals("TC", jobWithRange.companyInitials)
  }

  @Test
  fun testJobFilterActiveCount() {
    val defaultFilter = JobFilter()
    assertFalse(defaultFilter.hasActiveFilters)
    assertEquals(0, defaultFilter.activeFilterCount)

    val customFilter = JobFilter(
      query = "developer",
      category = "IT & Software",
      isRemoteOnly = true
    )
    assertTrue(customFilter.hasActiveFilters)
    assertEquals(3, customFilter.activeFilterCount)
  }
}
