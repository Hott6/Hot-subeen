package org.sopt.seminar.data.response

import com.google.gson.annotations.SerializedName
import org.sopt.seminar.domain.models.FollowerInfo

data class ResponseUser(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val login: String,
    val name: String,
    val bio: String
) {
    fun toUserInfo(): FollowerInfo = FollowerInfo(name, avatarUrl, login, bio)
}


//avatar_url 은 코틀린 파일내에서 코딩 컨벤션 안맞아서 언더바를 없애야함,
// But, avatarUrl로 하고, serializedName으로 서버 이름으로 한다.