package org.sopt.seminar.presentation.follower

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.seminar.domain.models.FollowerInfo
import org.sopt.seminar.domain.repositories.GithubRepository
import javax.inject.Inject

@HiltViewModel
class FollowerViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : ViewModel() {

    private var followers = MutableLiveData<List<FollowerInfo>?>()

    fun followerList() {
        viewModelScope.launch {
            runCatching { githubRepository.followList() }
                .onSuccess {
                    followers.value = it
                }.onFailure { Log.e("수빈,", "안됩니다FollowerViewModel", it) }
        }
    }

    fun getFollower(): LiveData<List<FollowerInfo>?> = followers

}
