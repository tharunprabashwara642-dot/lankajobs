package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.UserProfile

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: Int = 1,
    val fullName: String,
    val headline: String,
    val email: String,
    val phone: String,
    val preferredLocation: String,
    val targetRole: String,
    val preferredCategories: String, // comma-separated
    val experienceLevel: String,
    val bio: String,
    val resumeFileName: String,
    val dailyAlertsEnabled: Boolean
) {
    fun toDomain(): UserProfile {
        return UserProfile(
            id = id,
            fullName = fullName,
            headline = headline,
            email = email,
            phone = phone,
            preferredLocation = preferredLocation,
            targetRole = targetRole,
            preferredCategories = preferredCategories.split(",").map { it.trim() }.filter { it.isNotBlank() },
            experienceLevel = experienceLevel,
            bio = bio,
            resumeFileName = resumeFileName,
            dailyAlertsEnabled = dailyAlertsEnabled
        )
    }

    companion object {
        fun fromDomain(profile: UserProfile): UserProfileEntity {
            return UserProfileEntity(
                id = profile.id,
                fullName = profile.fullName,
                headline = profile.headline,
                email = profile.email,
                phone = profile.phone,
                preferredLocation = profile.preferredLocation,
                targetRole = profile.targetRole,
                preferredCategories = profile.preferredCategories.joinToString(", "),
                experienceLevel = profile.experienceLevel,
                bio = profile.bio,
                resumeFileName = profile.resumeFileName,
                dailyAlertsEnabled = profile.dailyAlertsEnabled
            )
        }
    }
}
