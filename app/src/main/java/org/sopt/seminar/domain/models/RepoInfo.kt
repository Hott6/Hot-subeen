package org.sopt.seminar.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepoInfo(
    val name: String,
    val description: String? = null,
    val language: String? = "언어 없음"
) : Parcelable
