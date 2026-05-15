package com.gramasuvidha.data.repository

import com.gramasuvidha.data.local.dao.UserDao
import com.gramasuvidha.data.local.entity.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun register(user: UserEntity) {
        userDao.insertUser(user)
    }

    suspend fun login(email: String, passwordHash: String): UserEntity? {
        val user = userDao.getUserByEmail(email)
        if (user != null && user.passwordHash == passwordHash) {
            return user
        }
        return null
    }

    fun getUserById(userId: Int) = userDao.getUserById(userId)
}
