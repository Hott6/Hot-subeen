package org.sopt.seminar.data.response

import org.sopt.seminar.domain.models.FollowerInfo

data class ResponseUser(
    val avatar_url: String,
    val login: String,
    val name: String,
    val bio: String
) {
    fun toUserInfo(): FollowerInfo {
        return FollowerInfo(name, avatar_url, login, bio)
    }
}