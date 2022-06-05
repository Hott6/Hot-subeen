package org.sopt.seminar

data class ResponseSignUp(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val id: Int
    )
}