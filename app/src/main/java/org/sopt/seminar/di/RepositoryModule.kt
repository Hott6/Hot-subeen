package org.sopt.seminar.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.seminar.data.repositories.GithubRepositoryImpl
import org.sopt.seminar.domain.repositories.GithubRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideGithubRepository(githubRepository: GithubRepositoryImpl) : GithubRepository = githubRepository
}