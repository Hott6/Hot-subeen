package org.sopt.seminar.data.services

import org.sopt.seminar.RequestSignIn
import org.sopt.seminar.RequestSignUp
import org.sopt.seminar.ResponseSignIn
import org.sopt.seminar.ResponseSignUp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptService {
    @POST("auth/signin")
    fun postLogin(
        @Body body: RequestSignIn
    ): Call<ResponseSignIn>

    @POST("auth/signup")
    fun postSignUp(
        @Body body: RequestSignUp
    ): Call<ResponseSignUp>

}