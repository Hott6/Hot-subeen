package org.sopt.seminar.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.sopt.seminar.ServiceCreator
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GithubRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SoptRetrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "http://13.124.62.236/"
    private const val GITHUB_URL = "https://api.github.com/"


    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: AppInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .run {
            addInterceptor(interceptor)
            build()
        }

    class AppInterceptor @Inject constructor() : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
            val newRequest = request().newBuilder()
                .addHeader("accept", "application/vnd.github.v3+json")
                .build()
            proceed(newRequest)
        }
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    @GithubRetrofit
    fun provideGithubRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(GITHUB_URL)
            .client(provideOkHttpClient(AppInterceptor()))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    @SoptRetrofit
    fun provideSoptRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}