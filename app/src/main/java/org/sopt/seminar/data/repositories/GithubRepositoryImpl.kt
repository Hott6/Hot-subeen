package org.sopt.seminar.data.repositories

import org.sopt.seminar.data.services.GithubApiService
import org.sopt.seminar.domain.models.FollowerInfo
import org.sopt.seminar.domain.models.RepoInfo
import org.sopt.seminar.domain.repositories.GithubRepository
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubService: GithubApiService
) : GithubRepository {
    override suspend fun followList(): List<FollowerInfo>? {
        return runCatching {
            githubService.getFollowingInfo()
        }.fold({
            it.map { following -> following.toUserInfo() }
        }, { null })
    }

    override suspend fun repoList(): List<RepoInfo>? {
        return runCatching {
            githubService.getRepoInfo()
        }.fold({
            it.map { repository -> repository.toRepoInfo() }
        }, { null })
    }
}