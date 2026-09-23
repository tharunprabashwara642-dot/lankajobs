package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.local.dao.JobDao
import com.example.data.local.dao.SavedJobDao
import com.example.data.local.dao.UserProfileDao
import com.example.data.local.entity.JobEntity
import com.example.data.local.entity.SavedJobEntity
import com.example.data.local.entity.UserProfileEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        JobEntity::class,
        SavedJobEntity::class,
        UserProfileEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun jobDao(): JobDao
    abstract fun savedJobDao(): SavedJobDao
    abstract fun userProfileDao(): UserProfileDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "lankajobs_database"
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Seed initial demo data
                            CoroutineScope(Dispatchers.IO).launch {
                                val database = getInstance(context)
                                database.jobDao().insertJobs(SeedDataProvider.getInitialJobs())
                                database.userProfileDao().insertOrUpdateProfile(SeedDataProvider.getDefaultProfile())
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                // Also verify that seed jobs exist on startup in case database was pre-created
                CoroutineScope(Dispatchers.IO).launch {
                    if (instance.jobDao().getCount() == 0) {
                        instance.jobDao().insertJobs(SeedDataProvider.getInitialJobs())
                    }
                    if (instance.userProfileDao().getUserProfileImmediate() == null) {
                        instance.userProfileDao().insertOrUpdateProfile(SeedDataProvider.getDefaultProfile())
                    }
                }
                instance
            }
        }
    }
}
