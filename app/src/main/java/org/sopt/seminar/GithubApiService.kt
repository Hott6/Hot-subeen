package org.sopt.seminar

import retrofit2.Call
import retrofit2.http.GET

interface GithubApiService {
    @GET("users/papajj06/repos")
    fun getRepoInfo(): Call<List<ResponseRepo>>

    @GET("users/papajj06")
    fun getUserInfo(): Call<ResponseUserInfo>

    @GET("users/papajj06/followers")
    fun getFollowingInfo(): Call<List<ResponseUserInfo>>
}
