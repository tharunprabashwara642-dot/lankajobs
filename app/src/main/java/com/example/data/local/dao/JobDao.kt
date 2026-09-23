package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.local.entity.JobEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JobDao {
    @Query("SELECT * FROM jobs WHERE isPublished = 1 ORDER BY createdAt DESC")
    fun getAllPublishedJobs(): Flow<List<JobEntity>>

    @Query("SELECT * FROM jobs WHERE isPublished = 1 AND isFeatured = 1 ORDER BY createdAt DESC")
    fun getFeaturedJobs(): Flow<List<JobEntity>>

    @Query("SELECT * FROM jobs ORDER BY createdAt DESC")
    fun getAllJobsForAdmin(): Flow<List<JobEntity>>

    @Query("SELECT * FROM jobs WHERE id = :id LIMIT 1")
    fun getJobById(id: String): Flow<JobEntity?>

    @Query("SELECT * FROM jobs WHERE id = :id LIMIT 1")
    suspend fun getJobByIdImmediate(id: String): JobEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(job: JobEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJobs(jobs: List<JobEntity>)

    @Update
    suspend fun updateJob(job: JobEntity)

    @Query("DELETE FROM jobs WHERE id = :id")
    suspend fun deleteJobById(id: String)

    @Query("UPDATE jobs SET isPublished = :isPublished WHERE id = :id")
    suspend fun updatePublishStatus(id: String, isPublished: Boolean)

    @Query("UPDATE jobs SET isFeatured = :isFeatured WHERE id = :id")
    suspend fun updateFeaturedStatus(id: String, isFeatured: Boolean)

    @Query("SELECT COUNT(*) FROM jobs")
    suspend fun getCount(): Int

    @Query("DELETE FROM jobs")
    suspend fun clearAll()
}
