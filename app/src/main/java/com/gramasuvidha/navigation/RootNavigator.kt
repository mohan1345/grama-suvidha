package com.gramasuvidha.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gramasuvidha.datastore.SessionManager
import com.gramasuvidha.ui.screens.SettingsScreen
import com.gramasuvidha.ui.screens.admin.AddEditProjectScreen
import com.gramasuvidha.ui.screens.admin.AdminDashboardScreen
import com.gramasuvidha.ui.screens.admin.FeedbackManagementScreen
import com.gramasuvidha.ui.screens.admin.IssueManagementScreen
import com.gramasuvidha.ui.screens.admin.ManageProjectsScreen
import com.gramasuvidha.ui.screens.auth.LoginScreen
import com.gramasuvidha.ui.screens.auth.RegisterScreen
import com.gramasuvidha.ui.screens.auth.SplashScreen
import com.gramasuvidha.ui.screens.citizen.FeedbackScreen
import com.gramasuvidha.ui.screens.citizen.HomeScreen
import com.gramasuvidha.ui.screens.citizen.ProjectDetailScreen
import com.gramasuvidha.ui.screens.citizen.ReportIssueScreen

import com.gramasuvidha.data.repository.AuthRepository
import com.gramasuvidha.data.repository.ProjectRepository

@Composable
fun RootNavigator(
    sessionManager: SessionManager,
    projectRepository: ProjectRepository,
    authRepository: AuthRepository
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        
        // Splash
        composable("splash") {
            SplashScreen(
                sessionManager = sessionManager,
                projectRepository = projectRepository,
                authRepository = authRepository,
                onNavigateToHome = {
                    navController.navigate("home") {
                        popUpTo("splash") { inclusive = true }
                    }
                },
                onNavigateToAdmin = {
                    navController.navigate("admin_dashboard") {
                        popUpTo("splash") { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.navigate("login") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }

        // Auth
        composable("login") {
            LoginScreen(
                onNavigateToHome = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToAdmin = {
                    navController.navigate("admin_dashboard") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = { navController.navigate("register") }
            )
        }
        composable("register") {
            RegisterScreen(
                onNavigateToLogin = { navController.popBackStack() }
            )
        }

        // Citizen Flow
        composable("home") {
            HomeScreen(
                onNavigateToProjectDetail = { id -> navController.navigate("project_detail/$id") },
                onNavigateToSettings = { navController.navigate("settings") }
            )
        }
        composable(
            "project_detail/{projectId}",
            arguments = listOf(navArgument("projectId") { type = NavType.StringType })
        ) { backStackEntry ->
            val projectId = backStackEntry.arguments?.getString("projectId") ?: return@composable
            ProjectDetailScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToFeedback = { id -> navController.navigate("feedback/$id") },
                onNavigateToReportIssue = { id -> navController.navigate("report_issue/$id") }
            )
        }
        composable(
            "feedback/{projectId}",
            arguments = listOf(navArgument("projectId") { type = NavType.StringType })
        ) { backStackEntry ->
            val projectId = backStackEntry.arguments?.getString("projectId") ?: return@composable
            FeedbackScreen(
                projectId = projectId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(
            "report_issue/{projectId}",
            arguments = listOf(navArgument("projectId") { type = NavType.StringType })
        ) { backStackEntry ->
            val projectId = backStackEntry.arguments?.getString("projectId") ?: return@composable
            ReportIssueScreen(
                projectId = projectId,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // Admin Flow
        composable("admin_dashboard") {
            AdminDashboardScreen(
                onNavigateToManageProjects = { navController.navigate("manage_projects") },
                onNavigateToManageFeedbacks = { navController.navigate("manage_feedbacks") },
                onNavigateToManageIssues = { navController.navigate("manage_issues") },
                onNavigateToSettings = { navController.navigate("settings") }
            )
        }
        composable("manage_projects") {
            ManageProjectsScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToAddProject = { navController.navigate("add_edit_project") },
                onNavigateToEditProject = { id -> navController.navigate("add_edit_project?projectId=$id") }
            )
        }
        composable(
            "add_edit_project?projectId={projectId}",
            arguments = listOf(navArgument("projectId") { 
                type = NavType.StringType 
                nullable = true
                defaultValue = null
            })
        ) { backStackEntry ->
            val projectId = backStackEntry.arguments?.getString("projectId")
            AddEditProjectScreen(
                projectId = projectId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable("manage_feedbacks") {
            FeedbackManagementScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable("manage_issues") {
            IssueManagementScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // Shared
        composable("settings") {
            SettingsScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToLogin = {
                    navController.navigate("login") {
                        popUpTo(0) // Clear all back stack
                    }
                }
            )
        }
    }
}
