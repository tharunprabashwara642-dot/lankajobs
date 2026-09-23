package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.JobEntity
import com.example.data.local.entity.SavedJobEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedJobDao {
    @Query("SELECT jobId FROM saved_jobs")
    fun getAllSavedJobIds(): Flow<List<String>>

    @Query("SELECT EXISTS(SELECT 1 FROM saved_jobs WHERE jobId = :jobId)")
    fun isJobSaved(jobId: String): Flow<Boolean>

    @Query("SELECT EXISTS(SELECT 1 FROM saved_jobs WHERE jobId = :jobId)")
    suspend fun isJobSavedImmediate(jobId: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveJob(savedJob: SavedJobEntity)

    @Query("DELETE FROM saved_jobs WHERE jobId = :jobId")
    suspend fun deleteSavedJob(jobId: String)

    @Query("SELECT j.* FROM jobs j INNER JOIN saved_jobs s ON j.id = s.jobId ORDER BY s.savedAt DESC")
    fun getSavedJobs(): Flow<List<JobEntity>>
}
