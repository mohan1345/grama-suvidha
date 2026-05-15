package com.gramasuvidha.data.local

import com.gramasuvidha.data.local.entity.ProjectEntity
import com.gramasuvidha.data.repository.ProjectRepository
import com.gramasuvidha.data.local.entity.UserEntity
import com.gramasuvidha.data.repository.AuthRepository
import kotlinx.coroutines.flow.first

object InitialData {
    suspend fun seedDatabase(projectRepository: ProjectRepository, authRepository: AuthRepository) {
        val count = projectRepository.getTotalProjectsCount().first()
        if (count == 0) {
            val mockProjects = listOf(
                ProjectEntity(
                    id = "1",
                    title = "Village Road Construction",
                    description = "Constructing a new concrete road connecting main highway to the village center.",
                    budget = "15,00,000",
                    progressPercentage = 60,
                    status = "Ongoing",
                    startDate = "01/01/2026",
                    endDate = "31/12/2026",
                    beforeImage = "https://images.unsplash.com/photo-1515162816999-a0c47dc192f7?auto=format&fit=crop&w=800&q=80",
                    afterImage = "https://images.unsplash.com/photo-1584464457692-282c0a96ea0f?auto=format&fit=crop&w=800&q=80"
                ),
                ProjectEntity(
                    id = "2",
                    title = "Primary School Renovation",
                    description = "Repairing roof, painting walls, and installing new benches for the primary school.",
                    budget = "5,00,000",
                    progressPercentage = 100,
                    status = "Completed",
                    startDate = "15/02/2025",
                    endDate = "10/08/2025",
                    beforeImage = "https://images.unsplash.com/photo-1580582932707-520aed937b7b?auto=format&fit=crop&w=800&q=80",
                    afterImage = "https://images.unsplash.com/photo-1503676260728-1c00da094a0b?auto=format&fit=crop&w=800&q=80"
                ),
                ProjectEntity(
                    id = "3",
                    title = "Water Supply System",
                    description = "Installing a new overhead water tank and laying pipelines for household connections.",
                    budget = "25,00,000",
                    progressPercentage = 15,
                    status = "Ongoing",
                    startDate = "10/03/2026",
                    endDate = "20/11/2026",
                    beforeImage = "https://images.unsplash.com/photo-1542013936693-884638332954?auto=format&fit=crop&w=800&q=80",
                    afterImage = "https://images.unsplash.com/photo-1527622830825-998dc7cfa8e8?auto=format&fit=crop&w=800&q=80"
                )
            )
            mockProjects.forEach { projectRepository.insertProject(it) }

            // Create default admin user
            authRepository.register(
                UserEntity(
                    name = "Admin",
                    email = "admin@gramasuvidha.gov",
                    passwordHash = "admin123",
                    role = "ADMIN"
                )
            )

            // Create default citizen user
            authRepository.register(
                UserEntity(
                    name = "Citizen",
                    email = "citizen@test.com",
                    passwordHash = "citizen123",
                    role = "USER"
                )
            )
        }
    }
}
