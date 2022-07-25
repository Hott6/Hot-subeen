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
// 코루틴 사용 시 suspend fun() 으로 선언할 때는 Call로 하지 않고 Response불러와도 됨
// suspend fun postSignup( ... ): ResponseSignUp