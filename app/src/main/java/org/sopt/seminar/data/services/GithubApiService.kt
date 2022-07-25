package org.sopt.seminar.data.services

import org.sopt.seminar.data.response.ResponseRepo
import org.sopt.seminar.data.response.ResponseUser
import retrofit2.http.GET

interface GithubApiService {
    @GET("users/papajj06/repos")
    suspend fun getRepoInfo(): List<ResponseRepo>

    @GET("users/papajj06")
    suspend fun getUserInfo(): ResponseUser

    @GET("users/papajj06/followers")
    suspend fun getFollowingInfo(): List<ResponseUser>
}
