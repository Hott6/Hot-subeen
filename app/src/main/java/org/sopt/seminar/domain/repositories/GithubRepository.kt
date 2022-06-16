package org.sopt.seminar.domain.repositories

import org.sopt.seminar.domain.models.FollowerInfo
import org.sopt.seminar.domain.models.RepoInfo

interface GithubRepository {
    suspend fun followList(): List<FollowerInfo>?
    suspend fun repoList(): List<RepoInfo>?
}