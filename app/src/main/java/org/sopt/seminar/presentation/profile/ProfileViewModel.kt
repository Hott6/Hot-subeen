package org.sopt.seminar.presentation.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.seminar.data.response.ResponseUser
import org.sopt.seminar.data.services.GithubApiService
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val service: GithubApiService
) : ViewModel() {
    private val _profileImg = MutableLiveData<String>()
    val profileImg: LiveData<String> get() = _profileImg

    private val userInfo = MutableLiveData<ResponseUser>()

    fun getUser() {
        viewModelScope.launch {
            runCatching { service.getUserInfo() }
                .onSuccess {
                    _profileImg.value = it.avatarUrl
                    userInfo.value = it
                }
                .onFailure { Log.e("viewmodel", "viewmodel실패") }
        }
    }

    fun getUserInfo(): LiveData<ResponseUser> = userInfo
}