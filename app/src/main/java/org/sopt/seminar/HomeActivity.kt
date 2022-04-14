package org.sopt.seminar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
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
            when (position) {
                FOLLOWER_POSITION -> {
                    transaction.replace(R.id.fragment_home, repoFragment)
                    position = REPO_POSITION
                }
                REPO_POSITION -> {
                    transaction.replace(R.id.fragment_home, followerFragment)
                    position = FOLLOWER_POSITION
                }
            }
            transaction.commit()
        }
    }

    companion object {
        const val FOLLOWER_POSITION = 1
        const val REPO_POSITION = 2
    }
}