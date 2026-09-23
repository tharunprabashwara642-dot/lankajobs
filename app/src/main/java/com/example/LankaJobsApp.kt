package com.example

import android.app.Application
import android.content.Context
import com.example.data.local.AppDatabase
import com.example.data.repository.JobRepository
import com.example.data.repository.JobRepositoryImpl
import com.example.data.repository.UserProfileRepository
import com.example.data.repository.UserProfileRepositoryImpl

class AppContainer(context: Context) {
    private val database: AppDatabase by lazy {
        AppDatabase.getInstance(context)
    }

    val jobRepository: JobRepository by lazy {
        JobRepositoryImpl(
            jobDao = database.jobDao(),
            savedJobDao = database.savedJobDao()
        )
    }

    val userProfileRepository: UserProfileRepository by lazy {
        UserProfileRepositoryImpl(
            userProfileDao = database.userProfileDao()
        )
    }
}

class LankaJobsApp : Application() {
    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}
