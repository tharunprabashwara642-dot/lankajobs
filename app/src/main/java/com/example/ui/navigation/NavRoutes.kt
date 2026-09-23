package com.example.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    object Home : Screen("home", "Home", Icons.Filled.Home, Icons.Outlined.Home)
    object Search : Screen("search", "Search", Icons.Filled.Search, Icons.Outlined.Search)
    object Saved : Screen("saved", "Saved", Icons.Filled.Bookmark, Icons.Outlined.BookmarkBorder)
    object Profile : Screen("profile", "Profile", Icons.Filled.Person, Icons.Outlined.Person)
}

val BOTTOM_NAV_ITEMS = listOf(
    Screen.Home,
    Screen.Search,
    Screen.Saved,
    Screen.Profile
)

object AppDestinations {
    const val HOME_ROUTE = "home"
    const val SEARCH_ROUTE = "search?category={category}&query={query}"
    const val SAVED_ROUTE = "saved"
    const val PROFILE_ROUTE = "profile"
    const val JOB_DETAILS_ROUTE = "job_details/{jobId}"

    const val ADMIN_LOGIN_ROUTE = "admin_login"
    const val ADMIN_DASHBOARD_ROUTE = "admin_dashboard"
    const val ADMIN_ADD_EDIT_ROUTE = "admin_add_edit?jobId={jobId}"

    fun searchWith(category: String? = null, query: String? = null): String {
        val catParam = category ?: ""
        val qParam = query ?: ""
        return "search?category=$catParam&query=$qParam"
    }

    fun jobDetails(jobId: String): String = "job_details/$jobId"

    fun adminEditJob(jobId: String? = null): String {
        return if (jobId != null) "admin_add_edit?jobId=$jobId" else "admin_add_edit"
    }
}
