package org.sopt.seminar.presentation.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.seminar.domain.models.FollowerInfo
import org.sopt.seminar.domain.models.RepoInfo
import org.sopt.seminar.domain.repositories.GithubRepository
import javax.inject.Inject

@HiltViewModel
class RepoViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : ViewModel() {

    private var repository = MutableLiveData<List<RepoInfo>?>()

    fun repoList() {
        viewModelScope.launch {
            runCatching { githubRepository.repoList() }
                .onSuccess {
                    repository.value = it
                }.onFailure { Log.e("수빈,", "안됩니다RepoViewModel", it) }
        }
    }

    fun getRepo(): LiveData<List<RepoInfo>?> = repository

}
