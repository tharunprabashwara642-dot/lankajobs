package com.example.data.repository

import com.example.data.local.SeedDataProvider
import com.example.data.local.dao.UserProfileDao
import com.example.data.local.entity.UserProfileEntity
import com.example.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface UserProfileRepository {
    fun getUserProfile(): Flow<UserProfile>
    suspend fun updateUserProfile(profile: UserProfile)
}

class UserProfileRepositoryImpl(
    private val userProfileDao: UserProfileDao
) : UserProfileRepository {

    override fun getUserProfile(): Flow<UserProfile> {
        return userProfileDao.getUserProfile().map { entity ->
            entity?.toDomain() ?: SeedDataProvider.getDefaultProfile().toDomain()
        }
    }

    override suspend fun updateUserProfile(profile: UserProfile) {
        userProfileDao.insertOrUpdateProfile(UserProfileEntity.fromDomain(profile))
    }
}
