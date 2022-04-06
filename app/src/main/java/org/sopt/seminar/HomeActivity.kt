package org.sopt.seminar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.sopt.seminar.databinding.ActivityHomeBinding
import org.sopt.seminar.databinding.ActivitySignInBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}