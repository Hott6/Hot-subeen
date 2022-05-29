package org.sopt.seminar.presentation.profile

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import org.sopt.seminar.*
import org.sopt.seminar.databinding.FragmentProfileBinding
import org.sopt.seminar.presentation.follower.FollowerFragment
import org.sopt.seminar.presentation.repo.RepoFragment
import retrofit2.Response

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initImage()
        initTransactionEvent()
    }

    private fun initImage() {
        val call = ServiceCreator.githubApiService.getUserInfo()
        call.enqueueUtil(
            onSuccess = {
                Glide.with(this).load(it.avatar_url).circleCrop().into(binding.imgProfile)
                binding.user = it
            }
        )
    }

    private fun initTransactionEvent() {
        val followerFragment = FollowerFragment()
        val repositoryFragment = RepoFragment()

        childFragmentManager.beginTransaction().add(R.id.fragment_profile, followerFragment)
            .commit()
        binding.btnFollower.isSelected = true //처음 화면 보여질 시에
        binding.btnFollower.setTextColor(Color.BLACK)

        binding.btnFollower.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.fragment_profile, followerFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
            binding.btnFollower.isSelected = true;
            binding.btnRepo.isSelected = false;
            binding.btnRepo.setTextColor(Color.GRAY)
            binding.btnFollower.setTextColor(Color.BLACK)
        }
        binding.btnRepo.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.fragment_profile, repositoryFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
            binding.btnRepo.isSelected = true
            binding.btnFollower.isSelected = false
            binding.btnFollower.setTextColor(Color.GRAY)
            binding.btnRepo.setTextColor(Color.BLACK)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}