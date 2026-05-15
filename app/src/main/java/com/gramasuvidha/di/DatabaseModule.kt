package com.gramasuvidha.di

import android.content.Context
import androidx.room.Room
import com.gramasuvidha.data.local.dao.FeedbackDao
import com.gramasuvidha.data.local.dao.IssueDao
import com.gramasuvidha.data.local.dao.ProjectDao
import com.gramasuvidha.data.local.dao.UserDao
import com.gramasuvidha.data.local.database.GramaSuvidhaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): GramaSuvidhaDatabase {
        return Room.databaseBuilder(
            context,
            GramaSuvidhaDatabase::class.java,
            "grama_suvidha_db"
        ).fallbackToDestructiveMigration()
         .build()
    }

    @Provides
    fun provideUserDao(database: GramaSuvidhaDatabase): UserDao = database.userDao()

    @Provides
    fun provideProjectDao(database: GramaSuvidhaDatabase): ProjectDao = database.projectDao()

    @Provides
    fun provideFeedbackDao(database: GramaSuvidhaDatabase): FeedbackDao = database.feedbackDao()

    @Provides
    fun provideIssueDao(database: GramaSuvidhaDatabase): IssueDao = database.issueDao()
}
