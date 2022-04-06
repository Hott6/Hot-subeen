package org.sopt.seminar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.sopt.seminar.databinding.ActivitySignInBinding
import org.sopt.seminar.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}