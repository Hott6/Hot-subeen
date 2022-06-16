package org.sopt.seminar.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import org.sopt.seminar.R
import org.sopt.seminar.User
import org.sopt.seminar.databinding.ActivityHomeBinding
import org.sopt.seminar.presentation.follower.FollowerFragment
import org.sopt.seminar.presentation.repo.RepoFragment

class HomeActivity : AppCompatActivity() {

    private var position = FOLLOWER_POSITION
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.user = User("김수빈", "23", "ENFJ")

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragment_home, FollowerFragment())
            }
        }
        inintTransactionEvent()
    }

    private fun inintTransactionEvent() {
        binding.btnFollower.setOnClickListener {
            if (position == REPO_POSITION) {
                supportFragmentManager.commit {
                    replace(R.id.fragment_home, FollowerFragment())
                    position = FOLLOWER_POSITION
                }
            }
        }
        binding.btnRepo.setOnClickListener {
            if (position == FOLLOWER_POSITION) {
                supportFragmentManager.commit {
                    replace(R.id.fragment_home, RepoFragment())
                    position = REPO_POSITION
                }
            }
        }
    }

    companion object {
        const val FOLLOWER_POSITION = 1
        const val REPO_POSITION = 2
    }
}