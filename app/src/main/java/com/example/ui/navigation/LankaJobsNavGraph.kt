package com.example.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.LankaJobsApp
import com.example.ui.admin.AdminAddEditJobScreen
import com.example.ui.admin.AdminAddEditJobViewModel
import com.example.ui.admin.AdminDashboardScreen
import com.example.ui.admin.AdminDashboardViewModel
import com.example.ui.admin.AdminLoginScreen
import com.example.ui.details.JobDetailsScreen
import com.example.ui.details.JobDetailsViewModel
import com.example.ui.home.HomeScreen
import com.example.ui.home.HomeViewModel
import com.example.ui.profile.ProfileScreen
import com.example.ui.profile.ProfileViewModel
import com.example.ui.saved.SavedJobsScreen
import com.example.ui.saved.SavedJobsViewModel
import com.example.ui.search.SearchScreen
import com.example.ui.search.SearchViewModel
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.DarkBorder
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.SkyPrimary
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextSecondary

@Composable
fun LankaJobsNavGraph(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val app = context.applicationContext as LankaJobsApp
    val container = app.container

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Hide bottom navigation bar for details and admin routes
    val showBottomBar = currentRoute in listOf(
        Screen.Home.route,
        AppDestinations.SEARCH_ROUTE,
        Screen.Search.route,
        Screen.Saved.route,
        Screen.Profile.route
    )

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = DarkBackground,
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar,
                enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
            ) {
                NavigationBar(
                    containerColor = DarkSurface,
                    tonalElevation = 0.dp,
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .border(1.dp, DarkBorder, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                ) {
                    BOTTOM_NAV_ITEMS.forEach { screen ->
                        val isSelected = when (screen) {
                            Screen.Home -> currentRoute == Screen.Home.route
                            Screen.Search -> currentRoute?.startsWith("search") == true
                            Screen.Saved -> currentRoute == Screen.Saved.route
                            Screen.Profile -> currentRoute == Screen.Profile.route
                        }

                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = if (isSelected) screen.selectedIcon else screen.unselectedIcon,
                                    contentDescription = screen.title
                                )
                            },
                            label = {
                                Text(
                                    text = screen.title,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            selected = isSelected,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = SkyPrimary,
                                unselectedIconColor = TextMuted,
                                selectedTextColor = SkyPrimary,
                                unselectedTextColor = TextMuted,
                                indicatorColor = SkyPrimary.copy(alpha = 0.15f)
                            ),
                            modifier = Modifier.testTag("bottom_nav_${screen.title.lowercase()}")
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Home Screen
            composable(Screen.Home.route) {
                val viewModel: HomeViewModel = viewModel(
                    factory = HomeViewModel.provideFactory(container.jobRepository)
                )
                HomeScreen(
                    viewModel = viewModel,
                    onJobClick = { jobId ->
                        navController.navigate(AppDestinations.jobDetails(jobId))
                    },
                    onNavigateToSearch = { category, query ->
                        navController.navigate(AppDestinations.searchWith(category, query))
                    },
                    onNavigateToSaved = {
                        navController.navigate(Screen.Saved.route)
                    },
                    onNavigateToAdmin = {
                        navController.navigate(AppDestinations.ADMIN_LOGIN_ROUTE)
                    }
                )
            }

            // Search Screen
            composable(
                route = AppDestinations.SEARCH_ROUTE,
                arguments = listOf(
                    navArgument("category") {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument("query") {
                        type = NavType.StringType
                        defaultValue = ""
                    }
                )
            ) { backStackEntry ->
                val categoryParam = backStackEntry.arguments?.getString("category")
                val queryParam = backStackEntry.arguments?.getString("query")

                val viewModel: SearchViewModel = viewModel(
                    key = "search_${categoryParam}_$queryParam",
                    factory = SearchViewModel.provideFactory(
                        jobRepository = container.jobRepository,
                        category = categoryParam,
                        query = queryParam
                    )
                )

                SearchScreen(
                    viewModel = viewModel,
                    onJobClick = { jobId ->
                        navController.navigate(AppDestinations.jobDetails(jobId))
                    }
                )
            }

            // Saved Jobs Screen
            composable(Screen.Saved.route) {
                val viewModel: SavedJobsViewModel = viewModel(
                    factory = SavedJobsViewModel.provideFactory(container.jobRepository)
                )
                SavedJobsScreen(
                    viewModel = viewModel,
                    onJobClick = { jobId ->
                        navController.navigate(AppDestinations.jobDetails(jobId))
                    },
                    onExploreJobs = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }

            // Profile Screen
            composable(Screen.Profile.route) {
                val viewModel: ProfileViewModel = viewModel(
                    factory = ProfileViewModel.provideFactory(
                        userProfileRepository = container.userProfileRepository,
                        jobRepository = container.jobRepository
                    )
                )
                ProfileScreen(
                    viewModel = viewModel,
                    onNavigateToSaved = {
                        navController.navigate(Screen.Saved.route)
                    },
                    onNavigateToAdmin = {
                        navController.navigate(AppDestinations.ADMIN_LOGIN_ROUTE)
                    }
                )
            }

            // Job Details Screen
            composable(
                route = AppDestinations.JOB_DETAILS_ROUTE,
                arguments = listOf(
                    navArgument("jobId") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                val jobId = backStackEntry.arguments?.getString("jobId") ?: ""
                val viewModel: JobDetailsViewModel = viewModel(
                    key = "details_$jobId",
                    factory = JobDetailsViewModel.provideFactory(container.jobRepository, jobId)
                )
                JobDetailsScreen(
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() }
                )
            }

            // Admin Login Screen
            composable(AppDestinations.ADMIN_LOGIN_ROUTE) {
                AdminLoginScreen(
                    onLoginSuccess = {
                        navController.navigate(AppDestinations.ADMIN_DASHBOARD_ROUTE) {
                            popUpTo(AppDestinations.ADMIN_LOGIN_ROUTE) { inclusive = true }
                        }
                    },
                    onBack = { navController.popBackStack() }
                )
            }

            // Admin Dashboard Screen
            composable(AppDestinations.ADMIN_DASHBOARD_ROUTE) {
                val viewModel: AdminDashboardViewModel = viewModel(
                    factory = AdminDashboardViewModel.provideFactory(container.jobRepository)
                )
                AdminDashboardScreen(
                    viewModel = viewModel,
                    onAddNewJob = {
                        navController.navigate(AppDestinations.adminEditJob(null))
                    },
                    onEditJob = { jobId ->
                        navController.navigate(AppDestinations.adminEditJob(jobId))
                    },
                    onBackToUserApp = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    }
                )
            }

            // Admin Add/Edit Job Screen
            composable(
                route = AppDestinations.ADMIN_ADD_EDIT_ROUTE,
                arguments = listOf(
                    navArgument("jobId") {
                        type = NavType.StringType
                        nullable = true
                        defaultValue = null
                    }
                )
            ) { backStackEntry ->
                val jobId = backStackEntry.arguments?.getString("jobId")
                val viewModel: AdminAddEditJobViewModel = viewModel(
                    key = "admin_add_edit_$jobId",
                    factory = AdminAddEditJobViewModel.provideFactory(container.jobRepository, jobId)
                )
                AdminAddEditJobScreen(
                    viewModel = viewModel,
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }
    }
}
