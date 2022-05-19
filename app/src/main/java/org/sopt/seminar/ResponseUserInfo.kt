package org.sopt.seminar

import com.google.gson.annotations.SerializedName

data class ResponseUserInfo(

    @SerializedName("avatar_url")
    val image: String,
    @SerializedName("login")
    val id: String,
    val name: String,
    @SerializedName("bio")
    val introduce: String

)
