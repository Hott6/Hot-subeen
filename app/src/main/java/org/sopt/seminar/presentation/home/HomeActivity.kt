package org.sopt.seminar.presentation.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.sopt.seminar.presentation.follower.FollowerFragment
import org.sopt.seminar.R
import org.sopt.seminar.presentation.repo.RepoFragment
import org.sopt.seminar.User
import org.sopt.seminar.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private var position = FOLLOWER_POSITION
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.user = User("김수빈", "23", "ENFJ")

        inintTransactionEvent()
    }

    private fun inintTransactionEvent() {
        val followerFragment = FollowerFragment()
        val repoFragment = RepoFragment()

        supportFragmentManager.beginTransaction().add(R.id.fragment_home, followerFragment).commit()

        binding.btnFollower.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            if (position == REPO_POSITION) {
                transaction.replace(R.id.fragment_home, followerFragment).commit()
                position = FOLLOWER_POSITION
            }
        }
        binding.btnRepo.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            if (position == FOLLOWER_POSITION) {
                transaction.replace(R.id.fragment_home, repoFragment).commit()
                position = REPO_POSITION
            }
        }
    }

    companion object {
        const val FOLLOWER_POSITION = 1
        const val REPO_POSITION = 2
    }
}