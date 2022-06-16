package org.sopt.seminar.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FollowerInfo(
    val name: String?,
    val avatar_url: String,
    val login: String,
    val bio: String?
) : Parcelable