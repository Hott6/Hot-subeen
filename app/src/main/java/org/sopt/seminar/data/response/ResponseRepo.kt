package org.sopt.seminar.data.response

import org.sopt.seminar.domain.models.RepoInfo

data class ResponseRepo(
    val name: String,
    val description: String?,
    val language: String?
) {
    fun toRepoInfo(): RepoInfo = RepoInfo(name, description, language)
}
