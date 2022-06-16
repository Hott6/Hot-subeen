package org.sopt.seminar.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.seminar.data.services.GithubApiService
import org.sopt.seminar.data.services.SoptService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideGithubService(@GithubRetrofit retrofit: Retrofit): GithubApiService =
        retrofit.create(GithubApiService::class.java)

    @Provides
    @Singleton
    fun provideSoptService(@SoptRetrofit retrofit: Retrofit): SoptService =
        retrofit.create(SoptService::class.java)
}